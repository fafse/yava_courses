package ru.croc.task11.src;

import java.io.*;
import java.net.Socket;
public class Client {
    private static Socket clientSocket; //сокет для общения
    private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет
    private String received = "";
    public void sendSmth(String hostname, int port, String name) throws IOException {
        try {
            try {
                // адрес - локальный хост, порт - 4004, такой же как у сервера
                clientSocket = new Socket(hostname, port); // этой строкой мы запрашиваем
                //  у сервера доступ на соединение
                reader = new BufferedReader(new InputStreamReader(System.in));
                // читать соообщения с сервера
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // писать туда же
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                out.write(name+"\n");
                out.flush();
                while(true){
                    System.out.println("Wait for received");
                    received = in.readLine();
                    System.out.println("received");
                    System.out.println(received);
                    System.out.print(name+">:");
                    String word = reader.readLine();
                    out.write(word+"\n"); // отправляем сообщение на сервер
                    out.flush();

                    if(word.equalsIgnoreCase("quit")||word.equalsIgnoreCase("quit\n"))
                    {
                        System.out.println("waiting server");
                        String serverWord = in.readLine(); // ждём, что скажет сервер
                        System.out.println("s");
                        System.out.println(serverWord); // получив - выводим на экран
                        break;
                    }
                }
                System.out.println("Exited");
            } finally { // в любом случае необходимо закрыть сокет и потоки
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
