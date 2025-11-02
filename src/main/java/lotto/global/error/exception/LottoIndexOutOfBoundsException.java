package lotto.global.error.exception;

import lotto.global.error.ErrorCode;

public class LottoIndexOutOfBoundsException extends IndexOutOfBoundsException {
    private final ErrorCode errorCode;

    public LottoIndexOutOfBoundsException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}