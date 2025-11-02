package lotto.global.config;

import lotto.domain.presentation.controller.LottoController;
import lotto.domain.presentation.view.OutputView;
import lotto.global.error.exception.LottoIllegalArgumentException;
import lotto.global.error.exception.LottoIllegalStateException;
import lotto.global.error.exception.LottoIndexOutOfBoundsException;
import lotto.global.error.exception.LottoNullPointerException;
import lotto.global.error.exception.LottoNumberFormatException;

/**
 * 애플리케이션의 실행과 예외 처리를 담당하는 클래스
 * 컨트롤러를 실행하고 발생하는 예외를 중앙에서 처리
 */
public class ApplicationRunner {

    private final LottoController lottoController;

    /**
     * 생성자를 통한 의존성 주입
     *
     * @param lottoController 컨트롤러
     */
    public ApplicationRunner(LottoController lottoController) {
        this.lottoController = lottoController;
    }

    /**
     * 애플리케이션 실행
     * 예외 처리를 중앙에서 관리하여 애플리케이션의 안정성 확보
     */
    public void run() {
        try {
            lottoController.lottoRun();
        } catch (LottoIllegalArgumentException | LottoNullPointerException |
                 LottoNumberFormatException | LottoIllegalStateException |
                 LottoIndexOutOfBoundsException e) {
            // 로또 관련 예외는 메시지 출력
            OutputView.printError(e.getMessage());
        } catch (Exception e) {
            // 예상치 못한 예외 처리
            OutputView.printError("예상치 못한 오류가 발생했습니다: " + e.getMessage());
        }
    }

}