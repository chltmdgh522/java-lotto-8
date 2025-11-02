package lotto.global.config;

import lotto.domain.presentation.controller.LottoController;
import lotto.domain.presentation.view.OutputView;
import lotto.global.error.exception.LottoIllegalArgumentException;
import lotto.global.error.exception.LottoIllegalStateException;
import lotto.global.error.exception.LottoIndexOutOfBoundsException;
import lotto.global.error.exception.LottoNullPointerException;
import lotto.global.error.exception.LottoNumberFormatException;


public class ApplicationRunner {

    private final LottoController lottoController;

    public ApplicationRunner(LottoController lottoController) {
        this.lottoController = lottoController;
    }


    public void run() {
        try {
            lottoController.lottoRun();
        } catch (RuntimeException e) {
            OutputView.printError(e.getMessage());
        } catch (Exception e) {
            OutputView.printError("예상치 못한 오류가 발생했습니다: " + e.getMessage());
        }
    }

}