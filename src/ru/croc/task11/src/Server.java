package ru.croc.task11.src;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static String phrase="";//то, что должно быть написано всем
    private ServerSocket serverSocket= null;
    private int clientCurNum = 0;
    public static ArrayList<ru.croc.task11.src.ClientHandler> clientArr = new ArrayList<ru.croc.task11.src.ClientHandler>();
    public ArrayList<Thread> threadArr = new ArrayList<Thread>();

    private Boolean is_end = false;

    public void startServer(int port) throws IOException {

        Socket client=null;
        do {
            try {
                if (serverSocket == null)
                    serverSocket = new ServerSocket(port); // 0 auto port
                serverSocket.setReuseAddress(true);
                is_end = false;
                System.out.println("Жду коннекта...");
                client = serverSocket.accept(); // ожидание соединения
                System.out.println("Connected");
                clientArr.add(new ru.croc.task11.src.ClientHandler(client));
                threadArr.add(new Thread(clientArr.get(clientCurNum)));
                threadArr.get(clientCurNum).start();
                clientCurNum++;

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                System.out.println("Работа завершена");
                if(serverSocket!=null) {
                    serverSocket.close();
                    serverSocket = null;
                }
                //освободили
            }
        }while(!is_end);
    }
}
