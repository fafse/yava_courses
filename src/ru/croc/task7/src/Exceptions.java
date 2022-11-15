package ru.croc.task7.src;

public class Exceptions {
    public static class IllegalPositionException extends Exception {
        public IllegalPositionException(String message)
        {
            super(message);

        }
    }
    public static class IllegalMoveException extends Exception {
        public IllegalMoveException(Chess prevObj, Chess curObj)
        {
            super("Конь так не ходит:" + prevObj + "=>"+ curObj);
        }
    }
}
