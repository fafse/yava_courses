package ru.croc.task3.src;

import java.util.Scanner;
import java.math.RoundingMode;

public class Task3 {

    static class Point
    {
        double x,y;
    }

    static double square(Point a,Point b,Point c)
    {
        return Math.abs(0.5*((a.x-c.x)*(b.y-c.y)-(a.y-c.y)*(b.x-c.x)));
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Point a = new Point();
        Point b = new Point();
        Point c = new Point();
        if(args.length==6)
        {
            a.x=Double.parseDouble(args[0]);
            a.y=Double.parseDouble(args[1]);
            b.x=Double.parseDouble(args[2]);
            b.y=Double.parseDouble(args[3]);
            c.x=Double.parseDouble(args[4]);
            c.y=Double.parseDouble(args[5]);
        }else
        {
            a.x=scanner.nextDouble();
            a.y=scanner.nextDouble();
            b.x=scanner.nextDouble();
            b.y=scanner.nextDouble();
            c.x=scanner.nextDouble();
            c.y=scanner.nextDouble();
        }
        System.out.println("Площадь треугольника = "+square(a,b,c));
    }
}
