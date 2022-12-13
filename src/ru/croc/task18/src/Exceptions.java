package ru.croc.task18.src;

public class Exceptions {
    public static class ProductExistsCodeException extends Exception {
        public ProductExistsCodeException(String message) {
            super(message);
        }
    }
    public static class ProductDontExists extends Exception
    {
        public ProductDontExists(String message)
        {
            super(message);
        }
    }
}
