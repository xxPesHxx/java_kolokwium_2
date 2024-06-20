package com.example.client_kolos_2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.time.LocalTime;

public class Client{
    private Socket socket;
    private BufferedReader bufferedReader;
    private HelloController controller;
    private LocalTime time;

    public Client(HelloController controller) throws IOException {
        this.controller = controller;
    }

    public void connect()
    {

        new Thread(()-> {
            try {
                socket = new Socket("localhost", 5000);
                System.out.println("Connected to the server");

                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message;
                while ((message = bufferedReader.readLine()) != null) {
                    time = LocalTime.now();
                    final String finalMessage = time.getHour() + ":" + time.getMinute() + ":" + time.getSecond() + " " + message;
                    System.out.println("received: " + message);
                    javafx.application.Platform.runLater(() -> controller.addWord(finalMessage));
                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }

}
