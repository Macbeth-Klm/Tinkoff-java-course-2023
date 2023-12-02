package edu.hw8.task1;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;

public class ClientServerTest {

    @Test
    void shouldReturnCorrectPhraseToOneClient() throws InterruptedException {
        Client client = new Client(1337);
        Server server = new Server(1337);
        AtomicReference<String> response = new AtomicReference<>("");
        Thread clientThread = new Thread(() -> response.set(client.sendRequest("личности")));
        Thread listenThread = new Thread(server::listen);
        Thread closeThread = new Thread(server::close);
        listenThread.start();
        sleep(1000);
        clientThread.start();
        sleep(2000);
        closeThread.start();
        clientThread.join();
        listenThread.join();
        closeThread.join();

        assertThat(response.get())
            .isEqualTo("Не переходи на личности там, где их нет.");
    }

    @Test
    void shouldReturnCorrectPhraseToSeveralClients() throws InterruptedException {
        var responses = Collections.synchronizedList(new ArrayList<>());
        Server server = new Server(4444);
        Thread listenThread = new Thread(server::listen);
        List<Runnable> tasks = List.of(
            () -> responses.add(new Client(4444).sendRequest("личности")),
            () -> responses.add(new Client(4444).sendRequest("оскорбления")),
            () -> responses.add(new Client(4444).sendRequest("глупый")),
            () -> responses.add(new Client(4444).sendRequest("интеллект")),
            () -> responses.add(new Client(4444).sendRequest("бебебе")),
            () -> {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                server.close();
            }
        );

        listenThread.start();
        sleep(1000);
        try (ExecutorService executorService = Executors.newFixedThreadPool(5)) {
            for (Runnable task : tasks) {
                executorService.submit(task);
            }
        }

        assertThat(responses)
            .containsExactlyInAnyOrder(
                "Не переходи на личности там, где их нет.",
                "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами.",
                "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
                "Чем ниже интеллект, тем громче оскорбления.",
                "Я не понимаю, что ты сейчас говоришь: я не говорю на абсурдном языке."
            );
    }
}
