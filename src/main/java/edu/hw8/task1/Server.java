package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ServerSocket serverSocket;
    private boolean isRunning;
    private final int port;
    private static final String OFF_SERVER_COMMAND = "//OFF_SERVER";

    public Server(int port) {
        this.port = port;
        isRunning = true;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("MagicNumber")
    public void listen() {
        try (serverSocket;
             ExecutorService threadPool = Executors.newFixedThreadPool(3)) {
            LOGGER.info("Server started...");
            while (isRunning) {
                Socket socket = serverSocket.accept();
                LOGGER.info("Client connected!");
                threadPool.execute(new ClientHandler(socket));
            }
            threadPool.shutdown();
            threadPool.awaitTermination(2000, TimeUnit.MILLISECONDS);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        isRunning = false;
        new Client(port).sendRequest(OFF_SERVER_COMMAND);
    }

    private record ClientHandler(Socket socket) implements Runnable {
        private static final Map<String, String> REQUEST_TO_RESPONSE = Map.of(
            "личности",
            "Не переходи на личности там, где их нет.",
            "оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами.",
            "глупый",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
            "интеллект",
            "Чем ниже интеллект, тем громче оскорбления."
        );
        private static final String DEFAULT_RESPONSE =
            "Я не понимаю, что ты сейчас говоришь: я не говорю на абсурдном языке.";

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                String request;
                while (((request = in.readLine()) != null && !request.equals(OFF_SERVER_COMMAND))) {
                    var response = REQUEST_TO_RESPONSE.getOrDefault(request, DEFAULT_RESPONSE);
                    out.println(response);
                }
                LOGGER.info("Client left!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
