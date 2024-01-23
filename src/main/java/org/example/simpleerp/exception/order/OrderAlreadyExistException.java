package org.example.simpleerp.exception.order;

import java.io.Serial;

public class OrderAlreadyExistException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 4948033234140650536L;

    private static final String DEFAULT_MESSAGE =
            "Order is already exist!";

    public OrderAlreadyExistException()
    {
        super(DEFAULT_MESSAGE);
    }

    public OrderAlreadyExistException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
