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
            {
                if(br.ready())
                {
                    String clientCommand = br.readLine();
                    oos.writeUTF(clientCommand);
                    oos.flush();
                    System.out.println("Sent:"+clientCommand+" to server");
                    if(clientCommand.equalsIgnoreCase("quit"))
                    {
                        System.out.println("Kill connections");
                        if(ois.read() > -1)     {
                            System.out.println("reading...");
                            String in = ois.readUTF();
                            System.out.println(in);
                        }
                        break;
                    }

                }
            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
