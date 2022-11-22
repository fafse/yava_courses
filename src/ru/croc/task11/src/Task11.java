package ru.croc.task11.src;
import java.io.IOException;
import java.util.Scanner;

public class Task11 {

    public static void main(String[] args) throws IOException {
        int menu=0;
            Scanner cin = new Scanner(System.in);
            System.out.println("Who am I?\n1. Server\n2. Client\n");
            menu = cin.nextInt();
            cin.nextLine();
            switch (menu)
            {
                case 1:
                {
                    try {
                        new Server().run();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case 2:
                {
                    new Client().run();
                    break;
                }
                default:
                {
                    break;
                }
            }
    }
}