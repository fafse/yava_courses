package ru.croc.task11.src;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket= null;
    Boolean is_end = false;
    public void func(int port) throws IOException {
        BufferedWriter out=null;
        BufferedReader in=null;
        Socket client=null;
        do {
            try {
                if (serverSocket == null)
                    serverSocket = new ServerSocket(port); // 0 auto port
                is_end = false;
                System.out.println("Жду коннекта...");
                client = serverSocket.accept(); // ожидание соединения
                System.out.println("Connected");

                out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));//канал записи в сокет от клиента
                System.out.println("Канал записи в сокет от клиента создан");

                in = new BufferedReader(new InputStreamReader(client.getInputStream()));//канал чтения из сокета
                System.out.println("Канал чтения из сокета от клиента создан");

                while (!client.isClosed()) {//пока клиент не отключился обрабатываем запросы
                    String entry = in.readLine();
                    System.out.println(entry);//выводим то, что прочитали
                    if (entry.equalsIgnoreCase("quit")) {//проверка на выход клиента из чатика
                        System.out.println("Client initialize connections suicide ...");
                        out.write("Server reply - " + entry + " - OK");
                        out.flush();
                        break;
                    }
                    if (entry.equalsIgnoreCase("quitserver")) {//проверка на запрос отключения сервера
                        System.out.println("Client initialize connections suicide ...");
                        is_end = true;
                        out.write("Server reply - " + entry + " - OK");
                        out.flush();
                        break;
                    }
                    out.write("Server checked it");
                    out.flush();
                }





            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                //если вышли, освобождаем
                out.close();
                in.close();
                System.out.println("Работа завершена");
                serverSocket.close();
                client.close();
                System.out.println("Работа с клиентом завершена");
                //освободили
            }
        }while(!is_end);
    }
}
