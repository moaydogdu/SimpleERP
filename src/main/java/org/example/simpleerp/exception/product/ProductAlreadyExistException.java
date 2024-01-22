package org.example.simpleerp.exception.product;

import java.io.Serial;

public class ProductAlreadyExistException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 7259337464852527647L;

    private static final String DEFAULT_MESSAGE =
            "Product is already exist!";

    public ProductAlreadyExistException(){
        super(DEFAULT_MESSAGE);
    }

    public ProductAlreadyExistException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
