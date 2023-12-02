package edu.hw8.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PasswordMiner {
    private final Map<String, String> md5ToUserMap;
    private final int passwordMaxSize;
    private final ConcurrentHashMap<String, String> userToPotentialPasswordMap;

    public PasswordMiner(Map<String, String> md5ToUserMap, int passwordMaxSize) {
        this.md5ToUserMap = md5ToUserMap;
        this.passwordMaxSize = passwordMaxSize;
        userToPotentialPasswordMap = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<String, String> singleThreadMining() {
        for (int i = 0; i < passwordMaxSize; i++) {
            var generator = new PasswordGenerator(passwordMaxSize - i);
            while (generator.hasNextPassword()) {
                String password = generator.generate();
                String hash = createMd5Hash(password);

                if (md5ToUserMap.containsKey(hash)) {
                    String login = md5ToUserMap.get(hash);
                    userToPotentialPasswordMap.put(login, password);
                }
            }
        }
        return userToPotentialPasswordMap;
    }

    @SuppressWarnings("MagicNumber")
    public ConcurrentHashMap<String, String> multiThreadMining(int threadCount) {
        if (threadCount == 0) {
            throw new IllegalStateException("Thread count must be more than zero!");
        }
        if (threadCount == 1) {
            return singleThreadMining();
        }
        try (ExecutorService threadPool = Executors.newFixedThreadPool(threadCount)) {
            for (int i = 0; i < passwordMaxSize; i++) {
                var currentPasswordSize = passwordMaxSize - i;
                int alphabetSize = 36;
                int passwordPerThread = (int) Math.pow(alphabetSize, currentPasswordSize) / threadCount;
                List<Runnable> tasks = new ArrayList<>();
                for (int j = 0; j < threadCount - 1; j++) {
                    int finalJ = j;
                    tasks.add(() -> {
                            var generator = new PasswordGenerator(
                                currentPasswordSize,
                                finalJ * passwordPerThread,
                                passwordPerThread
                            );
                            for (int k = 0; k < passwordPerThread; k++) {
                                String password = generator.generate();
                                String hash = createMd5Hash(password);

                                if (md5ToUserMap.containsKey(hash)) {
                                    String login = md5ToUserMap.get(hash);
                                    userToPotentialPasswordMap.put(login, password);
                                }
                            }
                        }
                    );
                }
                int remainder = (int) Math.pow(alphabetSize, currentPasswordSize) % threadCount;
                tasks.add(() -> {
                    var generator = new PasswordGenerator(
                        currentPasswordSize,
                        (threadCount - 1) * passwordPerThread,
                        passwordPerThread + remainder
                    );
                    while (generator.hasNextPassword()) {
                        String password = generator.generate();
                        String hash = createMd5Hash(password);

                        if (md5ToUserMap.containsKey(hash)) {
                            String login = md5ToUserMap.get(hash);
                            userToPotentialPasswordMap.put(login, password);
                        }
                    }
                });
                for (Runnable task : tasks) {
                    threadPool.execute(task);
                }
            }
        }
        return userToPotentialPasswordMap;
    }

    @SuppressWarnings("MagicNumber")
    private static String createMd5Hash(String input) {
        try {
            var md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }

                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
