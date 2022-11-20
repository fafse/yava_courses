//package ru.croc.task11.src;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket client=null;
    private BufferedWriter out=null;
    private BufferedReader in=null;
    private String entry;

    public ClientHandler(Socket client) {
        this.client=client;
    }

    @Override
    public void run() {
        try {
            out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));//канал записи в сокет от клиента
            System.out.println("Канал записи в сокет от клиента создан");

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));//канал чтения из сокета
            System.out.println("Канал чтения из сокета от клиента создан");

            while (!client.isClosed()) {//пока клиент не отключился обрабатываем запросы
                entry = in.readLine();
                System.out.println(entry);//выводим то, что прочитали
                if (entry.equalsIgnoreCase("quitserver")) {//проверка на запрос отключения сервера
                    System.out.println("Client initialize connections suicide ...");
                    out.write("Server reply - " + entry + " - OK");
                    out.flush();
                    break;
                }
                if (entry.equalsIgnoreCase("quit")) {//проверка на выход клиента из чатика
                    System.out.println("Client initialize connections suicide ...");
                    out.write("Server reply - " + entry + " - OK");
                    out.flush();
                    break;
                }

            }


        }catch( IOException e)
        {
            e.printStackTrace();
        }finally {
            //если вышли, освобождаем
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if(!client.isClosed()) {
                try {
                    client.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Работа с клиентом завершена");
            }
        }
    }
}
