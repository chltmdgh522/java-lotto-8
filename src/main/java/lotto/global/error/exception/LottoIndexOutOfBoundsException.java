package lotto.global.error.exception;

import lotto.global.error.ErrorCode;

/**
 * 인덱스가 범위를 벗어날 때 발생하는 예외
 * 예시: 존재하지 않는 로또 번호 인덱스 접근, 빈 리스트에 접근 등
 */
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