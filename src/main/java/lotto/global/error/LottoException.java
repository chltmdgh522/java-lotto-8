package lotto.global.error;

public class LottoException extends RuntimeException {
    private final ErrorCode errorCode;

    public LottoException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}