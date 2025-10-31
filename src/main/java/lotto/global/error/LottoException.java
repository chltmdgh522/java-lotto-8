package lotto.global.error;

public class LottoException extends Exception {
    private final ErrorCode errorCode;

    public LottoException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
