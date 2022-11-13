package ru.croc.task7.src;

import java.util.Scanner;

public class Task7 {

    public static class IllegalPositionException extends Exception {
        public IllegalPositionException(String message)
        {
            super(message);

        }
    }
    public static class IllegalMoveException extends Exception {
        public IllegalMoveException(Chess prevObj,Chess curObj)
        {
            super("Конь так не ходит:" + prevObj + curObj);
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
            if(pos.length()<2)
                throw new IllegalPositionException("Wrong position:"+pos);
            int x = pos.charAt(0)-'a';
            int y = pos.charAt(1)-'0'-1;
            if(x<0||y<0||x>7||y>7)
            {
                    throw new IllegalPositionException("Wrong position:"+pos);
            }else {
                return new Chess(x,y);
            }
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
        Chess prevPiece= arrayIn[0];
        for(int i = 1;i<arrayIn.length;i++)
        {
            if(!(arrayIn[i].getX()-prevPiece.getX()==2&&arrayIn[i].getY()-prevPiece.getY()==1||arrayIn[i].getY()-prevPiece.getY()==2&&arrayIn[i].getX()-prevPiece.getX()==1))
            {
                throw new IllegalMoveException(prevPiece,arrayIn[i]);
            }
            prevPiece= arrayIn[i];
        }
        return true;
    }
    public static void main(String[] args){
        Scanner cin = new Scanner(System.in);
        Chess kon = null;
        try {
            kon = Chess.CreateChess("a8");
        } catch (IllegalPositionException e) {
            System.out.println(e.getMessage());
            System.out.println("Проверяем координаты. Первая должна быть от 'a' до 'h'. Вторая от 1 до 8. Пример ввода:a5");
        }
        Chess[] arrayPieces = new Chess[15];
        String pos="";
        int i = 0;
        pos = cin.nextLine();
        while(pos!="");
        {

            try {
                arrayPieces[i] = Chess.CreateChess(pos);
            } catch (IllegalPositionException ex) {
                throw new RuntimeException(ex);
            }
            i++;
            pos = cin.nextLine();
            System.out.println("I read:"+pos);
        }
        try {
            CheckKnight(arrayPieces);
        } catch (IllegalMoveException e) {
            throw new RuntimeException(e);
        }
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