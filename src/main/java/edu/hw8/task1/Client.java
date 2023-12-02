package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private final int port;
    private static final String EXIT_COMMAND = "пока";

    public Client(int port) {
        this.port = port;
    }

    public String sendRequest(String input) {
        String response = "";
        try (
            Socket socket = new Socket("localhost", port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            String request = input;
            while (!request.equals(EXIT_COMMAND)) {
                out.println(request);
                response = in.readLine();
                request = EXIT_COMMAND;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
