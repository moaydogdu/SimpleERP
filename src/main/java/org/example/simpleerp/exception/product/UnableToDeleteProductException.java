package org.example.simpleerp.exception.product;

import java.io.Serial;

public class UnableToDeleteProductException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -1897175206769762571L;

    private static final String DEFAULT_MESSAGE =
            "Unable to delete Product!";

    public UnableToDeleteProductException()
    {
        super(DEFAULT_MESSAGE);
    }

    public UnableToDeleteProductException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
