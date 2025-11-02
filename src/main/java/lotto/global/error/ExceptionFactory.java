package lotto.global.error;

import lotto.global.error.exception.LottoIllegalArgumentException;
import lotto.global.error.exception.LottoIllegalStateException;
import lotto.global.error.exception.LottoIndexOutOfBoundsException;
import lotto.global.error.exception.LottoNullPointerException;
import lotto.global.error.exception.LottoNumberFormatException;

/**
 * 로또 애플리케이션에서 사용되는 예외 객체들을 생성하는 팩토리 클래스
 * 예외 생성을 중앙화하여 일관된 예외 처리를 가능하게 함
 */
public class ExceptionFactory {

    private ExceptionFactory() {
        // 인스턴스화 방지를 위한 private 생성자
    }

    /**
     * 인자 관련 예외를 생성합니다.
     * 메서드에 유효하지 않은 인자가 전달되었을 때 사용
     */
    public static LottoIllegalArgumentException illegalArgument(ErrorCode errorCode) {
        return new LottoIllegalArgumentException(errorCode);
    }

    /**
     * 상태 관련 예외를 생성합니다.
     * 객체의 현재 상태가 메서드 호출에 적합하지 않을 때 사용
     */
    public static LottoIllegalStateException illegalState(ErrorCode errorCode) {
        return new LottoIllegalStateException(errorCode);
    }

    /**
     * Null 포인터 관련 예외를 생성합니다.
     * null 참조에 접근하려고 할 때 사용
     */
    public static LottoNullPointerException nullPointer(ErrorCode errorCode) {
        return new LottoNullPointerException(errorCode);
    }

    /**
     * 숫자 형식 관련 예외를 생성합니다.
     * 문자열을 숫자로 변환할 수 없을 때 사용
     */
    public static LottoNumberFormatException numberFormat(ErrorCode errorCode) {
        return new LottoNumberFormatException(errorCode);
    }

    /**
     * 인덱스 범위 관련 예외를 생성합니다.
     * 인덱스가 범위를 벗어날 때 사용
     */
    public static LottoIndexOutOfBoundsException indexOutOfBounds(ErrorCode errorCode) {
        return new LottoIndexOutOfBoundsException(errorCode);
    }
}