package ru.croc.task11.src;


import java.io.*;
import java.net.Socket;

import static ru.croc.task11.src.Server.clientArr;

public class ClientHandler implements Runnable{
    private Socket client=null;
    private String name = null;
    private BufferedWriter out=null;
    private BufferedReader in=null;
    private String entry="";


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
            name = in.readLine();
            synchronized (ru.croc.task11.src.Server.phrase) {
                while (!client.isClosed()) {//пока клиент не отключился обрабатываем запросы
                    /*if (ru.croc.task11.src.Server.phrase != "" && ru.croc.task11.src.Server.phrase != entry) {
                        (ru.croc.task11.src.Server.phrase);
                    }*/


                    if (entry.equalsIgnoreCase(name + "\n" + "quit") || entry.equalsIgnoreCase("quit\n")) {//проверка на выход клиента из чатика
                        System.out.println("Client initialize connections suicide ...");
                        out.write("Server reply - " + entry + " - OK");
                        System.out.println("Replyied");
                        out.flush();
                        break;
                    }
                    entry = name + "\n";
                    entry += in.readLine();
                    for(var mc : clientArr)
                    {
                        if(mc != this)
                        {
                            mc.out.write(entry);
                        }
                    }
                    ru.croc.task11.src.Server.phrase = entry;
                    System.out.println(entry);//выводим то, что прочитали
                }
            }
            System.out.println("finished");


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
