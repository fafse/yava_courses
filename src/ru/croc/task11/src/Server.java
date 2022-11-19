package ru.croc.task11.src;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void func() throws IOException {
        try{
                ServerSocket serverSocket = new ServerSocket(2022); // 0 auto port
            System.out.println("Жду коннекта...");
                Socket client = serverSocket.accept(); // ожидание соединения
                System.out.println("Connected");

                DataOutputStream w = new DataOutputStream(client.getOutputStream());//канал записи в сокет от клиента
                System.out.println("Канал записи в сокет от клиента создан");

                DataInputStream in = new DataInputStream(client.getInputStream());//канал чтения из сокета
                System.out.println("Канал чтения из сокета от клиента создан");

                while(!client.isClosed()) {//пока клиент не отключился обрабатываем запросы
                    System.out.println("I try read....");
                    String entry = in.readUTF();//читаем чё пишут
                    System.out.println("I read "+entry);
            if(entry.equalsIgnoreCase("quit")){//проверка на выход клиента из чатика
                System.out.println("Client initialize connections suicide ...");
                w.writeUTF("Server reply - "+entry + " - OK");
                w.flush();
                break;
            }
            w.writeUTF("Server checked it");
            w.flush();
                    }


                //если вышли, освобождаем
            w.close();
                in.close();
                System.out.println("Работа завершена");
                client.close();
                System.out.println("Работа с клиентом завершена");
                //освободили
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
