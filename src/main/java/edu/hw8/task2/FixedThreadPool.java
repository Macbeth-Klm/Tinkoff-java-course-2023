package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;

public class FixedThreadPool implements ThreadPool {
    private final int nThreads;
    private final Thread[] threads;
    private final BlockingQueue<Runnable> taskQueue;
    private boolean isShutdown;

    public FixedThreadPool(int nThreads) {
        this.nThreads = nThreads;
        this.threads = new Thread[nThreads];
        this.taskQueue = new LinkedBlockingQueue<>();
        isShutdown = false;
    }

    @Override
    public void start() {
        for (int i = 0; i < nThreads; i++) {
            threads[i] = new Thread(() -> {
                while (!(isShutdown && taskQueue.isEmpty())) {
                    try {
                        Runnable task = taskQueue.take();
                        task.run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (isShutdown) {
            throw new RejectedExecutionException();
        }
        try {
            taskQueue.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    @SuppressWarnings("MagicNumber")
    public void shutdown() throws InterruptedException {
        isShutdown = true;
        for (Thread thread : threads) {
            thread.join(2000);
        }
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    @Override
    public void close() throws InterruptedException {
        shutdown();
    }
}
