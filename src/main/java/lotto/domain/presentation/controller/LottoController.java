package lotto.domain.presentation.controller;

import java.util.List;
import lotto.domain.application.service.LottoService;
import lotto.domain.entity.lotto.Lotto;
import lotto.domain.entity.statistics.WinningStatistics;
import lotto.domain.presentation.view.InputView;
import lotto.domain.presentation.view.OutputView;
import lotto.domain.presentation.view.validator.LottoValidator;
import lotto.domain.presentation.view.parser.LottoInputParser;

public class LottoController {
    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void lottoRun() {
        int purchaseAmount = readAndValidatePurchaseAmount();
        List<Lotto> purchasedLottos = processPurchase(purchaseAmount);
        Lotto winningLotto = readAndValidateWinningLotto();
        int bonusNumber = readAndValidateBonusNumber(winningLotto);
        processResult(purchaseAmount, purchasedLottos, winningLotto, bonusNumber);
    }

    private List<Lotto> processPurchase(int purchaseAmount) {
        int lottoCount = calculateLottoCount(purchaseAmount);
        List<Lotto> lottos = lottoService.purchasedLottoTicket(lottoCount);
        OutputView.printPurchasedLottoCount(lottoCount);
        OutputView.printPurchasedLottos(lottos);
        return lottos;
    }

    private void processResult(int purchaseAmount, List<Lotto> purchasedLottos, Lotto winningLotto, int bonusNumber) {
        List<WinningStatistics> stats = lottoService.compareLottoTicket(purchasedLottos, winningLotto, bonusNumber);
        OutputView.printWinningStatistics(stats);
        float profitRate = lottoService.calculateProfitRate(purchaseAmount, stats);
        OutputView.printProfitRate(profitRate);
    }

    private int calculateLottoCount(int purchaseAmount) {
        return purchaseAmount / 1000;
    }

    private int readAndValidatePurchaseAmount() {
        while (true) {
            try {
                String input = InputView.readPurchaseAmount();
                int amount = LottoInputParser.parsePurchaseAmount(input);
                LottoValidator.validatePurchaseAmount(amount);
                return amount;
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    private Lotto readAndValidateWinningLotto() {
        while (true) {
            try {
                String input = InputView.readWinningNumbers();
                List<Integer> numbers = LottoInputParser.parseWinningNumbers(input);
                LottoValidator.validateWinningNumbers(numbers);
                return new Lotto(numbers);
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    private int readAndValidateBonusNumber(Lotto winningLotto) {
        while (true) {
            try {
                String input = InputView.readBonusNumber();
                int bonus = LottoInputParser.parseBonusNumber(input);
                LottoValidator.validateBonusNumber(bonus, winningLotto.getNumbers());
                return bonus;
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
