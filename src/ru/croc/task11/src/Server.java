package ru.croc.task11.src;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Server {
    int count;
    BlockingQueue<Connection> connectionsArrayList = new LinkedBlockingQueue<Connection>();

    public void run() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(60);
            System.out.println("Server started");

            do {
                Socket newSocket = serverSocket.accept();
                Connection connection = new Connection(newSocket);
                Thread thread = new Thread(connection);
                thread.start();
                connectionsArrayList.add(connection);

            } while (!connectionsArrayList.isEmpty());
            

        } catch (IOException e) {
            System.out.println("Server start ERROR");
        }finally {
            serverSocket.close();
        }

    }

    class Connection implements Runnable {

        Socket socket;
        Writer writer;
        String name;

        public Connection(Socket socket) {
            this.socket = socket;

        }

        @Override
        public void run() {

            try (InputStream inputStream = socket.getInputStream()) {

                Scanner scanner = new Scanner(inputStream, "utf-8");
                String message;
                this.name = scanner.nextLine();
                message = this.name+" connected";
                sendMessage(message);

                while (socket.isConnected()) {
                    message = scanner.nextLine();
                    if (message.equals("quit")) {
                        System.out.println(this.name + " disconnected");
                        message =  this.name + " disconnected.";
                        sendMessage(message);
                        connectionsArrayList.remove(this);
                        break;
                    }
                    System.out.println(message);
                    sendMessage(message);

                }

            } catch (IOException e) {
                System.out.println(Thread.currentThread().toString() + " not initialized");
            }

        }

        private void sendMessage(String message) throws IOException {
            for (Connection connection : connectionsArrayList) {

                if (connection.equals(this)) continue;

                if (connection.socket.isConnected()) {
                    Writer writer = new OutputStreamWriter(connection.socket.getOutputStream(), "utf-8");
                    System.out.println(this.name+">:\n"+message);
                    writer.write(message + "\n");
                    writer.flush();
                } else {
                    System.out.println("Client " + connection.name + " unavailable");
                }

            }
        }
    }
}