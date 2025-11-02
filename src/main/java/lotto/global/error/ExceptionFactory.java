package lotto.global.error;

import lotto.global.error.exception.LottoIllegalArgumentException;
import lotto.global.error.exception.LottoIllegalStateException;
import lotto.global.error.exception.LottoIndexOutOfBoundsException;
import lotto.global.error.exception.LottoNullPointerException;
import lotto.global.error.exception.LottoNumberFormatException;

public class ExceptionFactory {

    private ExceptionFactory() {}

    public static LottoIllegalArgumentException illegalArgument(ErrorCode errorCode) {
        return new LottoIllegalArgumentException(errorCode);
    }

    public static LottoIllegalStateException illegalState(ErrorCode errorCode) {
        return new LottoIllegalStateException(errorCode);
    }

    public static LottoNullPointerException nullPointer(ErrorCode errorCode) {
        return new LottoNullPointerException(errorCode);
    }

    public static LottoNumberFormatException numberFormat(ErrorCode errorCode) {
        return new LottoNumberFormatException(errorCode);
    }

    public static LottoIndexOutOfBoundsException indexOutOfBounds(ErrorCode errorCode) {
        return new LottoIndexOutOfBoundsException(errorCode);
    }
}
