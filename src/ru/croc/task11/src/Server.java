//package ru.croc.task11.src;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket= null;

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

                ClientHandler clientHandler
                        = new ClientHandler(client);
                new Thread(clientHandler).start();

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
