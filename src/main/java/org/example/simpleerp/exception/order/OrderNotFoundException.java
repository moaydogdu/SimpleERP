package org.example.simpleerp.exception.order;

import java.io.Serial;

public class OrderNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 2128839079595095302L;

    private static final String DEFAULT_MESSAGE =
            "Order not found!";

    public OrderNotFoundException()
    {
        super(DEFAULT_MESSAGE);
    }

    public OrderNotFoundException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
