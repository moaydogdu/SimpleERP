package org.example.simpleerp.exception.order_product;

import java.io.Serial;

public class OrderProductNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -395457523408604559L;

    private static final String DEFAULT_MESSAGE =
            "OrderProduct Not Found";

    public OrderProductNotFoundException(){
        super(DEFAULT_MESSAGE);
    }

    public OrderProductNotFoundException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
