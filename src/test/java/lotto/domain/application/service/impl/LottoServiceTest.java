package lotto.domain.application.service.impl;

import lotto.domain.application.service.LottoService;
import lotto.domain.entity.lotto.Lotto;
import lotto.domain.entity.statistics.StatisticsType;
import lotto.domain.entity.statistics.WinningStatistics;
import lotto.global.error.exception.LottoIllegalArgumentException;
import lotto.global.error.exception.LottoNullPointerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoServiceTest {

    private final LottoService lottoService = new LottoServiceImpl();

    @DisplayName("구입 금액에 맞게 로또 티켓을 발행한다")
    @Test
    void purchaseLottoTickets() {
        // Given
        int count = 5;

        // When
        List<Lotto> lottos = lottoService.purchasedLottoTicket(count);

        // Then
        assertThat(lottos).hasSize(count);
        assertThat(lottos).allMatch(lotto -> lotto.getNumbers().size() == 6);
        assertThat(lottos).allMatch(lotto ->
                lotto.getNumbers().stream().allMatch(number -> number >= 1 && number <= 45));
    }

    @DisplayName("구입 금액이 0 이하일 경우 예외가 발생한다")
    @Test
    void purchaseLottoTicketsWithInvalidAmount() {
        // Given
        int invalidCount = 0;

        // When, Then
        assertThatThrownBy(() -> lottoService.purchasedLottoTicket(invalidCount))
                .isInstanceOf(LottoIllegalArgumentException.class);
    }

    @DisplayName("로또 번호를 비교하여 3개 일치 결과를 정확하게 반환한다")
    @Test
    void compareThreeMatchingNumbers() {
        // Given
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto winningLotto = new Lotto(winningNumbers);
        int bonusNumber = 7;

        List<Integer> purchasedNumbers = Arrays.asList(1, 2, 3, 10, 11, 12);
        List<Lotto> purchasedLottos = List.of(new Lotto(purchasedNumbers));

        // When
        List<WinningStatistics> statistics = lottoService.compareLottoTicket(purchasedLottos, winningLotto, bonusNumber);

        // Then
        assertThat(statistics.size()).isEqualTo(5); // 5개 등급 전체 통계 반환
        assertThat(statistics.get(0).getStatisticsType()).isEqualTo(StatisticsType.MATCH_3);
        assertThat(statistics.get(0).getLuckyCount()).isEqualTo(1); // 3개 일치 1장
        assertThat(statistics.stream()
                .filter(stat -> stat.getStatisticsType() != StatisticsType.MATCH_3)
                .allMatch(stat -> stat.getLuckyCount() == 0)).isTrue(); // 다른 등급은 모두 0장
    }

    @DisplayName("로또 번호를 비교하여 4개 일치 결과를 정확하게 반환한다")
    @Test
    void compareFourMatchingNumbers() {
        // Given
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto winningLotto = new Lotto(winningNumbers);
        int bonusNumber = 7;

        List<Integer> purchasedNumbers = Arrays.asList(1, 2, 3, 4, 11, 12);
        List<Lotto> purchasedLottos = List.of(new Lotto(purchasedNumbers));

        // When
        List<WinningStatistics> statistics = lottoService.compareLottoTicket(purchasedLottos, winningLotto, bonusNumber);

        // Then
        assertThat(statistics.get(1).getStatisticsType()).isEqualTo(StatisticsType.MATCH_4);
        assertThat(statistics.get(1).getLuckyCount()).isEqualTo(1); // 4개 일치 1장
    }

    @DisplayName("로또 번호를 비교하여 5개 일치 결과를 정확하게 반환한다")
    @Test
    void compareFiveMatchingNumbers() {
        // Given
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto winningLotto = new Lotto(winningNumbers);
        int bonusNumber = 7;

        List<Integer> purchasedNumbers = Arrays.asList(1, 2, 3, 4, 5, 12);
        List<Lotto> purchasedLottos = List.of(new Lotto(purchasedNumbers));

        // When
        List<WinningStatistics> statistics = lottoService.compareLottoTicket(purchasedLottos, winningLotto, bonusNumber);

        // Then
        assertThat(statistics.get(2).getStatisticsType()).isEqualTo(StatisticsType.MATCH_5);
        assertThat(statistics.get(2).getLuckyCount()).isEqualTo(1); // 5개 일치 1장
    }

    @DisplayName("로또 번호를 비교하여 5개+보너스 일치 결과를 정확하게 반환한다")
    @Test
    void compareFiveMatchingNumbersWithBonus() {
        // Given
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto winningLotto = new Lotto(winningNumbers);
        int bonusNumber = 7;

        List<Integer> purchasedNumbers = Arrays.asList(1, 2, 3, 4, 5, 7);
        List<Lotto> purchasedLottos = List.of(new Lotto(purchasedNumbers));

        // When
        List<WinningStatistics> statistics = lottoService.compareLottoTicket(purchasedLottos, winningLotto, bonusNumber);

        // Then
        assertThat(statistics.get(3).getStatisticsType()).isEqualTo(StatisticsType.MATCH_5_BONUS);
        assertThat(statistics.get(3).getLuckyCount()).isEqualTo(1); // 5개+보너스 일치 1장
    }

    @DisplayName("로또 번호를 비교하여 6개 일치(1등) 결과를 정확하게 반환한다")
    @Test
    void compareSixMatchingNumbers() {
        // Given
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto winningLotto = new Lotto(winningNumbers);
        int bonusNumber = 7;

        List<Integer> purchasedNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Lotto> purchasedLottos = List.of(new Lotto(purchasedNumbers));

        // When
        List<WinningStatistics> statistics = lottoService.compareLottoTicket(purchasedLottos, winningLotto, bonusNumber);

        // Then
        assertThat(statistics.get(4).getStatisticsType()).isEqualTo(StatisticsType.MATCH_6);
        assertThat(statistics.get(4).getLuckyCount()).isEqualTo(1); // 6개 일치 1장
    }

    @DisplayName("여러 개의 로또를 비교하여 결과를 정확하게 집계한다")
    @Test
    void compareMultipleLottos() {
        // Given
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto winningLotto = new Lotto(winningNumbers);
        int bonusNumber = 7;

        List<Lotto> purchasedLottos = new ArrayList<>();
        purchasedLottos.add(new Lotto(Arrays.asList(1, 2, 3, 10, 11, 12))); // 3개 일치
        purchasedLottos.add(new Lotto(Arrays.asList(1, 2, 3, 4, 11, 12))); // 4개 일치
        purchasedLottos.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 12))); // 5개 일치
        purchasedLottos.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7))); // 5개+보너스 일치
        purchasedLottos.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6))); // 6개 일치
        purchasedLottos.add(new Lotto(Arrays.asList(11, 12, 13, 14, 15, 16))); // 0개 일치

        // When
        List<WinningStatistics> statistics = lottoService.compareLottoTicket(purchasedLottos, winningLotto, bonusNumber);

        // Then
        assertThat(statistics.get(0).getStatisticsType()).isEqualTo(StatisticsType.MATCH_3);
        assertThat(statistics.get(0).getLuckyCount()).isEqualTo(1); // 3개 일치 1장

        assertThat(statistics.get(1).getStatisticsType()).isEqualTo(StatisticsType.MATCH_4);
        assertThat(statistics.get(1).getLuckyCount()).isEqualTo(1); // 4개 일치 1장

        assertThat(statistics.get(2).getStatisticsType()).isEqualTo(StatisticsType.MATCH_5);
        assertThat(statistics.get(2).getLuckyCount()).isEqualTo(1); // 5개 일치 1장

        assertThat(statistics.get(3).getStatisticsType()).isEqualTo(StatisticsType.MATCH_5_BONUS);
        assertThat(statistics.get(3).getLuckyCount()).isEqualTo(1); // 5개+보너스 일치 1장

        assertThat(statistics.get(4).getStatisticsType()).isEqualTo(StatisticsType.MATCH_6);
        assertThat(statistics.get(4).getLuckyCount()).isEqualTo(1); // 6개 일치 1장
    }

    @DisplayName("수익률이 정확하게 계산된다")
    @Test
    void calculateProfitRateCorrectly() {
        // Given
        int purchaseAmount = 6000; // 6000원 구매

        List<WinningStatistics> statistics = new ArrayList<>();
        statistics.add(new WinningStatistics(StatisticsType.MATCH_3, 1)); // 5,000원 1개
        statistics.add(new WinningStatistics(StatisticsType.MATCH_4, 0));
        statistics.add(new WinningStatistics(StatisticsType.MATCH_5, 0));
        statistics.add(new WinningStatistics(StatisticsType.MATCH_5_BONUS, 0));
        statistics.add(new WinningStatistics(StatisticsType.MATCH_6, 0));

        // When
        float profitRate = lottoService.calculateProfitRate(purchaseAmount, statistics);

        // Then
        // 구매액: 6000원, 당첨금: 5000원, 수익률: (5000/6000) * 100 = 83.33...%
        assertThat(profitRate).isEqualTo(83.33f);
    }

    @DisplayName("수익률 계산시 여러 당첨 결과가 정확하게 합산된다")
    @Test
    void calculateProfitRateWithMultipleWins() {
        // Given
        int purchaseAmount = 10000; // 10000원 구매

        List<WinningStatistics> statistics = new ArrayList<>();
        statistics.add(new WinningStatistics(StatisticsType.MATCH_3, 1)); // 5,000원 1개
        statistics.add(new WinningStatistics(StatisticsType.MATCH_4, 1)); // 50,000원 1개
        statistics.add(new WinningStatistics(StatisticsType.MATCH_5, 0));
        statistics.add(new WinningStatistics(StatisticsType.MATCH_5_BONUS, 0));
        statistics.add(new WinningStatistics(StatisticsType.MATCH_6, 0));

        // When
        float profitRate = lottoService.calculateProfitRate(purchaseAmount, statistics);

        // Then
        // 구매액: 10000원, 당첨금: 5000원 + 50000원 = 55000원, 수익률: (55000/10000) * 100 = 550%
        assertThat(profitRate).isEqualTo(550.0f);
    }

    @DisplayName("구매 금액이 없을 경우 수익률 계산시 예외가 발생한다")
    @Test
    void calculateProfitRateWithZeroPurchaseAmount() {
        // Given
        int purchaseAmount = 0;
        List<WinningStatistics> statistics = new ArrayList<>();

        // When, Then
        assertThatThrownBy(() -> lottoService.calculateProfitRate(purchaseAmount, statistics))
                .isInstanceOf(LottoIllegalArgumentException.class);
    }

    @DisplayName("당첨 통계가 null일 경우 수익률 계산시 예외가 발생한다")
    @Test
    void calculateProfitRateWithNullStatistics() {
        // Given
        int purchaseAmount = 1000;

        // When, Then
        assertThatThrownBy(() -> lottoService.calculateProfitRate(purchaseAmount, null))
                .isInstanceOf(LottoNullPointerException.class);
    }

    @DisplayName("당첨 로또가 null일 경우 로또 비교시 예외가 발생한다")
    @Test
    void compareWithNullWinningLotto() {
        // Given
        List<Lotto> purchasedLottos = new ArrayList<>();
        purchasedLottos.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));

        // When, Then
        assertThatThrownBy(() -> lottoService.compareLottoTicket(purchasedLottos, null, 7))
                .isInstanceOf(LottoNullPointerException.class);
    }

    @DisplayName("구매 로또 목록이 null일 경우 로또 비교시 예외가 발생한다")
    @Test
    void compareWithNullPurchasedLottos() {
        // Given
        Lotto winningLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));

        // When, Then
        assertThatThrownBy(() -> lottoService.compareLottoTicket(null, winningLotto, 7))
                .isInstanceOf(LottoNullPointerException.class);
    }

    @DisplayName("보너스 번호가 범위를 벗어날 경우 로또 비교시 예외가 발생한다")
    @Test
    void compareWithInvalidBonusNumber() {
        // Given
        List<Lotto> purchasedLottos = new ArrayList<>();
        purchasedLottos.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        Lotto winningLotto = new Lotto(Arrays.asList(11, 12, 13, 14, 15, 16));

        // When, Then
        assertThatThrownBy(() -> lottoService.compareLottoTicket(purchasedLottos, winningLotto, 46))
                .isInstanceOf(LottoIllegalArgumentException.class);
    }
}