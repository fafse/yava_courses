package ru.croc.task11.src;

import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {
    String name;
    Scanner scanner=new Scanner(System.in, "cp866");
    String address;
    OutputStreamWriter writer;


    public void run() throws IOException {
        System.out.print("Введите адрес сервера");
        System.out.println();
        address = scanner.nextLine();

        ChatListener chatListener = null;
        try (Socket socket = new Socket(address, 60)) {


            writer = new OutputStreamWriter(socket.getOutputStream(), "utf-8");

            chatListener = new ChatListener(socket);
            Thread thread = new Thread(chatListener);

            thread.start();

            System.out.print("Введите имя пользователя");
            System.out.println();
            this.name = scanner.nextLine();
            writer.write(this.name + "\n");
            writer.flush();

            String message;

            System.out.println("Для  завершения работы введите 'quit'");
            while (true) {
                message = scanner.nextLine();
                if (message.toLowerCase().equals("quit")) {
                    writer.write(message + "\n");
                    writer.flush();
                    chatListener.isWork = false;
                    break;
                } else {
                    writer.write(message + "\n");
                    writer.flush();
                }

            }
        } catch (IOException e) {
            System.out.println("Ошибка подключения");
            this.run();

        } finally {
            writer.close();
            scanner.close();

        }

    }

    class ChatListener implements Runnable {

        boolean isWork = true;

        Socket socket;

        public ChatListener(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (Scanner scanner = new Scanner(socket.getInputStream(), "utf-8")) {

                while (true) {

                    if(isWork) {
                        try {
                            System.out.println(scanner.nextLine());
                        } catch (NoSuchElementException e){
                            System.out.println("До встречи");
                            break;
                        }

                    } else {
                        scanner.close();
                        socket.close();
                        break;
                    }

                }

            } catch (IOException e) {
                System.out.println(e);
            }

        }
    }
}