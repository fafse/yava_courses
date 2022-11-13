package ru.croc.task7.src;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Math.abs;
/*
Формат ввода из командной строки g6 e8 a7
 */
public class Task7 {

    public static int numObj = 0;
    public static class IllegalPositionException extends Exception {
        public IllegalPositionException(String message)
        {
            super(message);

        }
    }
    public static class IllegalMoveException extends Exception {
        public IllegalMoveException(Chess prevObj,Chess curObj)
        {
            super("Конь так не ходит:" + prevObj + "=>"+ curObj);
        }
    }
    public static class Chess extends Exception{
        protected int x,y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public static Chess CreateChess(String pos) throws IllegalPositionException {
            if (pos.length() < 2) {
                throw new IllegalPositionException("Wrong position:" + pos);
            }
            int x = pos.charAt(0) - 'a';
            int y = pos.charAt(1) - '0' - 1;
                if (x < 0 || y < 0 || x > 7 || y > 7) {
                    IllegalPositionException e = new IllegalPositionException("Wrong position:" + pos);
                    System.out.println(e.getMessage());
                    return null;

                }
            return new Chess(x,y);
        }

        private Chess(int x,int y)
        {
            this.x = x;
            this.y=y;
        }
        @Override
        public String toString()
        {
            char x = (char) ('a'+this.x);
            String positions= ""+x+(1+this.y);
            return positions;
        }
    }
    public static Boolean CheckKnight(Chess[] arrayIn) throws IllegalMoveException
    {
        if(numObj==0) {
            System.out.println("Not enough objects");
            return false;
        }
        Chess prevPiece= arrayIn[0];
        for(int i = 1;i<numObj;i++)
        {
            if(!((abs(arrayIn[i].getX()-prevPiece.getX())==2&&abs(arrayIn[i].getY()-prevPiece.getY())==1)||(abs(arrayIn[i].getY()-prevPiece.getY())==2&&abs(arrayIn[i].getX()-prevPiece.getX())==1)))
            {
                throw new IllegalMoveException(prevPiece,arrayIn[i]);
            }
            prevPiece= arrayIn[i];
        }
        return true;
    }
    public static void PrintArray(Chess[] arrayIn)
    {
        for(int i = 0;i<arrayIn.length;i++)
        {
            if(arrayIn[i]==null)
            {
                break;
            }
            System.out.println(arrayIn[i]);
        }
    }
    public static void main(String[] args){
        Chess[] arrayPieces = new Chess[15];
        String pos="";
        int menu = 0;
        if(args!=null)
        {
            for(int i = 0;i<args.length;i++)
            {
                try {
                    arrayPieces[numObj++]= Chess.CreateChess(args[i]);
                } catch (IllegalPositionException e) {
                    System.out.println(e);
                }
            }
        }
        do{
            try {
                Scanner cin = new Scanner(System.in);
                System.out.println("Menu:");
                System.out.println("1. Add new object");
                System.out.println("2. Check current array in CheckKnight");
                System.out.println("3. Print objects");
                System.out.println("0. Exit");
                menu = cin.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(e);
                System.out.println("You entered wrong int.");
                menu = -1;
            }
            switch (menu) {
                case 1: {
                    System.out.println("Enter position of obj");
                    Scanner cin = new Scanner(System.in);
                    try {
                        if (!((arrayPieces[numObj] = Chess.CreateChess(cin.nextLine())) == null)) {
                            numObj++;
                        }
                    } catch (IllegalPositionException e) {
                        System.out.println(e);
                    }
                    break;
                }
                case 2: {
                    try {
                        if(CheckKnight(arrayPieces))
                        {
                            System.out.println("OK");
                        }
                        break;
                    } catch (IllegalMoveException e) {
                        System.out.println(e);
                    }
                }
                case 3:
                {
                    PrintArray(arrayPieces);
                    break;
                }
                case 0: {
                    System.out.println("Exiting...");
                    break;
                }
                default: {
                    break;
                }
            }
        }while(menu!=0);
    }
}
/*
Определить класс, описывающий позицию на шахматной доске 8x8. Данные класса: компоненты x и y, отсчитываемые от левого нижнего угла (x = 0, y = 0 - левая нижняя клетка).

Все методы, позволяющие установить координаты, в том числе и конструкторы, должны проверять корректность аргументов и генерировать IllegalPositionException (необходимо определить это исключение самостоятельно) в случае ошибочных значений.

Переопределить метод toString(), выводящий координаты позиции в формате <номер колонки в виде буквы от 'a' до 'h'><номер строки, начиная с 1>. Например, позиция с координатами (1, 1) имеет строковое представление "b2".

Реализовать "фабричный метод" конструирования объекта позиции из строкового представления ("b2" -> объект):

static ChessPosition parse(String position) {
  // ...
}

В виде массива строк задана некоторая последовательность позиций на шахматной доске 8x8. Например, "b1", "a3", "c4", "d6". Реализовать метод, проверяющий, что последовательность может быть пройдена фигурой конь в соответствии с правилами хода этой фигуры (буквой "Г"). На вход метод принимает массив объектов класса, определенных в текущей задаче.

Определить новый класс IllegalMoveException обрабатываемого исключения, которое генерируется методом проверки в случае ошибки. Класс должен содержать информацию о неправильном ходе: из какой в какую позиции ход запрещен. При вызове метода проверки это исключение должно обрабатываться, а неправильный ход выводиться на экран. Последовательность ходов для проверки задается в аргументах командной строки программы.

public class IllegalMoveException extends Exception {
  // ...
}
Тестовые примеры
[in]  "g8", "e7", "e6"
[out] "конь так не ходит: e7 -> e6"

[in]  "g8", "e7", "c8"
[out] "OK"
*/