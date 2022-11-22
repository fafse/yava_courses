package ru.croc.task11.src;
import java.io.IOException;
import java.util.Scanner;

public class Task11 {

    public static void main(String[] args) throws IOException {
        int menu=0;
            Scanner cin = new Scanner(System.in);
            System.out.println("Кто я?\n1. Сервер\n2. Клиент\n0. Выход");
            int port = 2022;
            menu = cin.nextInt();
            cin.nextLine();
            switch (menu)
            {
                case 1:
                {
                    try {
                        new Server().startServer(port);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case 2:
                {
                    new Client(port).run();
                    break;
                }
                default:
                {
                    break;
                }
            }
    }
}
