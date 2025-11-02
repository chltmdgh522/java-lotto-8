package lotto.global.config;

import lotto.domain.presentation.controller.LottoController;
import lotto.domain.presentation.view.OutputView;
import lotto.global.error.LottoException;

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
     * 예상치 못한 예외만 여기서 처리 (예상된 LottoException은 컨트롤러에서 이미 처리됨)
     */
    public void run() {
        try {
            lottoController.lottoRun();
        } catch (LottoException e) {
            OutputView.printError(e.getMessage());
        }
    }

}