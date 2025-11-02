package lotto.global.error.exception;

import lotto.global.error.ErrorCode;

public class LottoIllegalStateException extends IllegalStateException {
    private final ErrorCode errorCode;

    public LottoIllegalStateException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}