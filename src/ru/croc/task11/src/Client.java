package ru.croc.task11.src;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public void sendSmth(String hostname, int port) throws IOException {
        try(Socket socket = new Socket(hostname,port);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            DataInputStream ois = new DataInputStream(socket.getInputStream());)
        {
            while(!socket.isOutputShutdown())

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
