package io.github.arbing.dy.common.error;

public class DyException extends RuntimeException {
    private final DyError error;

    private static final Long DEFAULT_ERROR_CODE = 99L;

    public DyException(String message) {
        this(DyError.builder().errorCode(DEFAULT_ERROR_CODE).description(message).build());
    }

    public DyException(DyError error) {
        super(error.toString());
        this.error = error;
    }

    public DyException(DyError error, Throwable cause) {
        super(error.toString(), cause);
        this.error = error;
    }

    public DyException(Throwable cause) {
        super(cause.getMessage(), cause);
        this.error = DyError.builder().errorCode(DEFAULT_ERROR_CODE).description(cause.getMessage()).build();
    }

    public DyError getError() {
        return this.error;
    }
}
