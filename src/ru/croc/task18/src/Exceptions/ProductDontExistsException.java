package Exceptions;

public class ProductDontExistsException extends Exception
{
    public ProductDontExistsException(String message)
    {
        super(message);
    }
}
