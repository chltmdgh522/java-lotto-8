package lotto.global.error.exception;

import lotto.global.error.ErrorCode;

public class LottoIllegalArgumentException extends IllegalArgumentException {
    private final ErrorCode errorCode;

    public LottoIllegalArgumentException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}