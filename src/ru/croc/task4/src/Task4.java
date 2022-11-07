package ru.croc.task4.src;

import java.io.PrintStream;
import java.lang.module.Configuration;
import java.util.Objects;
import java.util.Scanner;

public class Task4 {
    public static Scanner scanner = new Scanner(System.in);
    public abstract static class Figure
    {
        protected double x1,y1;
    }

    public static class Rectangle extends Figure
    {
        protected double x2,y2;
        protected Rectangle(double...points)
        {
            super.x1=points[0];
            super.y1=points[1];
            x2=points[2];
            y2=points[3];
        }
        @Override
        public String toString()
        {
            return "Rectangle "+ x1+","+y1+"), "+"("+ x2+","+ y2+"): ";
        }
    }

    public static class Circle extends Figure
    {
        protected double radius;
        Circle(double...data)
        {
            super.x1=data[0];
            super.y1=data[1];
            this.radius=data[2];
        }
        @Override
        public String toString()
        {
            return "Circle ("+x1+","+y1+"), "+radius+": ";
        }
    }

    public static class Annotation
    {
        private Figure figure;
        private String sign;
        public static Annotation createObj(int idFigure, String sign,double...data)
        {
            Figure tmpFigure;
            if(idFigure<0||idFigure>1)
            {
                System.out.println("Unavailable to find this class id");
                return null;
            }
            if(idFigure==0&&
                    data.length==4) {
                if(data[0]>data[2]||
                        data[1]>data[3])
                {
                    System.out.println("Wrong points. " +
                            "\nFirst point should be the lower left corner." +
                            "\nSecond point should be the upper right corner");
                    return null;
                }
                tmpFigure = new Rectangle(data);
            }
            else if(idFigure==1&&
                    data.length==3)
            {
                if(data[2]<0) {
                    System.out.println("Wrong radius(<0). Radius must be >=0");
                    return null;
                }
                tmpFigure = new Circle(data);
            }
            else {
                System.out.println("Wrong number of arguments(data)");
                return null;
            }
            return new Annotation(tmpFigure,
                    sign);
        }

        private Annotation(Figure figure,
                           String sign)
        {
                this.figure = figure;

            if(sign!=null&&
                    sign!="")
            {
                this.sign=sign;
            }else {
                this.sign="Default text";
            }
        }
        @Override
        public String toString()
        {
            String name="Unavailable to understand class of current obj";
                name= figure.toString()+sign;
            return name;
        }


    }

    static class AnnotatedImage {

        private final String imagePath;

        private final Annotation[] annotations;



        public AnnotatedImage(String imagePath,
                              Annotation... annotations) {
            this.imagePath = imagePath;
            this.annotations = annotations;
        }

        public String getImagePath() {
            return this.imagePath;
        }

        public Annotation[] getAnnotations() {
            return this.annotations;
        }
    }


    public static void main(String[] args) {
        double[] circleData = new double[]
                {1,2,1};
        double[] rectangleData = new double[]
                {1,2,3,4};
        String textNull="";
        Annotation nullFigure= null;
        Annotation circle = Annotation.createObj(1,
                "Circle Text",
                circleData);
        Annotation rectangle = Annotation.createObj(0,
                "rectangle sign",
                rectangleData);
        System.out.println(circle);
        System.out.println(rectangle);
        System.out.println(nullFigure);
    }
}