package org.example.simpleerp.exception.statistics;

import java.io.Serial;

public class UnableToCalculateStatisticsException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 4586360374817715643L;

    private static final String DEFAULT_MESSAGE =
            "Unable To Calculate Statistics!";

    public UnableToCalculateStatisticsException()
    {
        super(DEFAULT_MESSAGE);
    }

    public UnableToCalculateStatisticsException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
