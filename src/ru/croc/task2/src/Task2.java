package ru.croc.task2.src;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        double startEl,raznProgr, sum = 0;
        int numMembers;
        if(args.length==3)
        {
            startEl=Double.parseDouble(args[0]);
            raznProgr=Double.parseDouble(args[1]);
            numMembers=Integer.parseInt(args[2]);
        }else
        {
            startEl= scanner.nextDouble();
            raznProgr= scanner.nextDouble();
            numMembers=scanner.nextInt();
        }

        for(int i = 0;i<numMembers;i++)
        {
            sum+=startEl;
            startEl+=raznProgr;
        }
        System.out.println("Sum:"+sum);
    }
}
