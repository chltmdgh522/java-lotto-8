package lotto.domain.presentation.controller;

import java.util.List;
import lotto.domain.application.service.LottoService;
import lotto.domain.entity.lotto.Lotto;
import lotto.domain.entity.statistics.WinningStatistics;
import lotto.domain.presentation.view.InputView;
import lotto.domain.presentation.view.OutputView;
import lotto.global.error.exception.LottoIllegalArgumentException;
import lotto.global.error.exception.LottoIllegalStateException;
import lotto.global.error.exception.LottoIndexOutOfBoundsException;
import lotto.global.error.exception.LottoNullPointerException;
import lotto.global.error.exception.LottoNumberFormatException;

public class LottoController {
    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    // 로또 게임 실행
    public void lottoRun() {
        // 구매 금액 입력 및 로또 생성
        int purchaseAmount = getPurchaseAmount();
        int lottoCount = purchaseAmount / 1000;

        // 구매한 로또 티켓 생성 및 출력
        List<Lotto> purchasedLottos = createPurchasedLottos(lottoCount);
        printPurchasedLottos(lottoCount, purchasedLottos);

        // 당첨 번호 및 보너스 번호 입력
        Lotto winningLotto = createWinningLotto();
        int bonusNumber = getBonusNumber(winningLotto.getNumbers());

        // 당첨 결과 계산 및 출력
        processWinningResult(purchaseAmount, purchasedLottos, winningLotto, bonusNumber);
    }

    // 구매 금액 입력 처리
    private int getPurchaseAmount() {
        while (true) {
            try {
                return InputView.readPurchaseAmount();
            } catch (LottoIllegalArgumentException | LottoNumberFormatException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    // 구매한 로또 생성
    private List<Lotto> createPurchasedLottos(int lottoCount) {
        try {
            return lottoService.purchasedLottoTicket(lottoCount);
        } catch (LottoNullPointerException | LottoIllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return createPurchasedLottos(lottoCount); // 재시도
        }
    }

    // 구매한 로또 출력
    private void printPurchasedLottos(int lottoCount, List<Lotto> purchasedLottos) {
        try {
            OutputView.printPurchasedLottoCount(lottoCount);
            OutputView.printPurchasedLottos(purchasedLottos);
        } catch (LottoNullPointerException e) {
            OutputView.printError(e.getMessage());
        }
    }

    // 당첨 로또 생성
    private Lotto createWinningLotto() {
        while (true) {
            try {
                List<Integer> winningNumbers = InputView.readWinningNumbers();
                return new Lotto(winningNumbers);
            } catch (LottoIllegalArgumentException | LottoNullPointerException |
                     LottoNumberFormatException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    // 보너스 번호 입력 처리
    private int getBonusNumber(List<Integer> winningNumbers) {
        while (true) {
            try {
                return InputView.readBonusNumber(winningNumbers);
            } catch (LottoIllegalArgumentException | LottoNullPointerException |
                     LottoNumberFormatException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    // 당첨 결과 처리
    private void processWinningResult(int purchaseAmount, List<Lotto> purchasedLottos,
                                      Lotto winningLotto, int bonusNumber) {
        try {
            // 당첨 통계 계산
            List<WinningStatistics> winningStatistics =
                    lottoService.compareLottoTicket(purchasedLottos, winningLotto, bonusNumber);

            // 당첨 통계 출력
            OutputView.printWinningStatistics(winningStatistics);

            // 수익률 계산 및 출력
            float profitRate = lottoService.calculateProfitRate(purchaseAmount, winningStatistics);
            OutputView.printProfitRate(profitRate);
        } catch (LottoIllegalArgumentException | LottoNullPointerException |
                 LottoIllegalStateException | LottoIndexOutOfBoundsException e) {
            OutputView.printError(e.getMessage());
        }
    }
}