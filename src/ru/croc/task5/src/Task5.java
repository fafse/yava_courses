package ru.croc.task5.src;

import java.lang.module.Configuration;
import java.util.Scanner;

public class Task5 {
    public static Scanner scanner = new Scanner(System.in);
    public interface Movable {

        void move(int dx, int dy);
    }
    public abstract static class Figure implements Movable
    {
        protected double x1,y1;

        public double getX1() {
            return x1;
        }

        public double getY1() {
            return y1;
        }
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

        public void move(int dx, int dy)
        {
            super.x1+=dx;
            super.y1+=dy;
            x2+=dx;
            y2+=dy;
        }
        public double getY2() {
            return y2;
        }

        public double getX2() {
            return x2;
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
        public void move(int dx, int dy)
        {
            super.x1+=dx;
            super.y1+=dy;
        }
        public double getRadius() {
            return radius;
        }
    }

    public static class Annotation
    {
        private Figure figure;
        private String sign;
        public static Annotation createObj(int idFigure, String sign,double...data)
        {
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
            }
            else if(idFigure==1&&
                    data.length==3)
            {
                if(data[2]<0) {
                    System.out.println("Wrong radius(<0). Radius must be >=0");
                    return null;
                }
            }
            else {
                System.out.println("Wrong number of arguments(data)");
                return null;
            }
            return new Annotation(idFigure,
                    sign,
                    data);
        }

        private Annotation(int idFigure,
                           String sign,
                           double...data)
        {
            if(idFigure==0)
            {
                figure = new Rectangle(data);
            }else if(idFigure==1)
            {
                figure = new Circle(data);
            }
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
            String name="";
            if(figure instanceof Rectangle)
            {
                name="Rectangle ("+figure.x1+","+figure.y1+"), "+"("+((Rectangle) figure).x2+","+ ((Rectangle) figure).y2+"): "+sign;
            }else if(figure instanceof Circle)
            {
                name="Circle ("+figure.x1+","+figure.y1+"), "+((Circle)(figure)).radius+": "+sign;
            }
            return name;
        }

        public Double[] getData() {
            if(figure instanceof Rectangle)
            {
                Double[] data = new Double[4];
                data[0]=figure.getX1();
                data[1]=figure.getY1();
                data[2]=((Rectangle) figure).getX2();
                data[3]=((Rectangle) figure).getY2();
                return data;
            }else if(figure instanceof Circle)
            {
                Double[] data = new Double[3];
                data[0]=figure.getX1();
                data[1]=figure.getY1();
                data[2]=((Circle) figure).getRadius();
                return data;
            }
            return null;
        }

        public Figure getFigure() {
            return figure;
        }

        public String getSign() {
            return sign;
        }

        public void move(int dx,int dy)
        {
            figure.move(dx,dy);
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

        public Annotation findByPoint(int x, int y)
        {
            Double[] tmpData = new Double[4];
            for(Annotation annotation: annotations)
            {
                tmpData= annotation.getData();
                if(tmpData.length==3)
                {
                    if((x-tmpData[0])*(x-tmpData[0])+
                            (y-tmpData[1])*(y-tmpData[1])
                            <=tmpData[2]*tmpData[2])
                    {
                        return annotation;
                    }
                } else if (tmpData.length ==4) {
                    if(x<=tmpData[2]&&x>=tmpData[0]&&y<=tmpData[3]&&y>=tmpData[1])
                    {
                        return annotation;
                    }
                }
            }
            return null;
        }
        public Annotation findByLabel(String label)
        {
            for(Annotation annotation: annotations)
            {
                if(annotation.getSign().contains(label))
                {
                    return annotation;
                }
            }
            return null;
        }

    }

    public static void main(String[] args) {
        double[] circleData = new double[]
                {0,0,3};
        double[] rectangleData = new double[]
                {17,17,19,19};
        String textNull="";
        Annotation circle = Annotation.createObj(1,
                "Circle Text",
                circleData);
        Annotation rectangle = Annotation.createObj(0,
                "rectangle sign",
                rectangleData);
        System.out.println(circle.toString());
        System.out.println(rectangle.toString());
        AnnotatedImage check = new AnnotatedImage("path",circle,rectangle);
        System.out.println(
        check.findByPoint(1,2).toString());
        check.findByPoint(1,2).move(15,15);
        System.out.println(
                check.findByPoint(16,17).toString());
        System.out.println(
                check.findByLabel("Te").toString()
        );
    }
}