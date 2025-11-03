package lotto.domain.entity.statistics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WinningStatisticsTest {

    @DisplayName("당첨 통계 객체가 올바르게 생성된다")
    @Test
    void createWinningStatistics() {
        // Given
        StatisticsType type = StatisticsType.MATCH_3;
        Integer count = 5;

        // When
        WinningStatistics statistics = new WinningStatistics(type, count);

        // Then
        assertThat(statistics.getStatisticsType()).isEqualTo(type);
        assertThat(statistics.getLuckyCount()).isEqualTo(count);
    }

    @DisplayName("당첨 통계 객체에서 통계 타입을 올바르게 조회할 수 있다")
    @Test
    void getStatisticsType() {
        // Given
        WinningStatistics statistics1 = new WinningStatistics(StatisticsType.MATCH_3, 1);
        WinningStatistics statistics2 = new WinningStatistics(StatisticsType.MATCH_4, 2);
        WinningStatistics statistics3 = new WinningStatistics(StatisticsType.MATCH_5, 3);
        WinningStatistics statistics4 = new WinningStatistics(StatisticsType.MATCH_5_BONUS, 4);
        WinningStatistics statistics5 = new WinningStatistics(StatisticsType.MATCH_6, 5);

        // Then
        assertThat(statistics1.getStatisticsType()).isEqualTo(StatisticsType.MATCH_3);
        assertThat(statistics2.getStatisticsType()).isEqualTo(StatisticsType.MATCH_4);
        assertThat(statistics3.getStatisticsType()).isEqualTo(StatisticsType.MATCH_5);
        assertThat(statistics4.getStatisticsType()).isEqualTo(StatisticsType.MATCH_5_BONUS);
        assertThat(statistics5.getStatisticsType()).isEqualTo(StatisticsType.MATCH_6);
    }

    @DisplayName("당첨 통계 객체에서 당첨 개수를 올바르게 조회할 수 있다")
    @Test
    void getLuckyCount() {
        // Given
        WinningStatistics statistics = new WinningStatistics(StatisticsType.MATCH_3, 5);

        // Then
        assertThat(statistics.getLuckyCount()).isEqualTo(5);
    }

    @DisplayName("여러 종류의 당첨 통계 객체를 올바르게 생성할 수 있다")
    @Test
    void createMultipleWinningStatistics() {
        // When
        WinningStatistics match3 = new WinningStatistics(StatisticsType.MATCH_3, 10);
        WinningStatistics match4 = new WinningStatistics(StatisticsType.MATCH_4, 5);
        WinningStatistics match5 = new WinningStatistics(StatisticsType.MATCH_5, 2);
        WinningStatistics match5Bonus = new WinningStatistics(StatisticsType.MATCH_5_BONUS, 1);
        WinningStatistics match6 = new WinningStatistics(StatisticsType.MATCH_6, 0);

        // Then
        assertThat(match3.getStatisticsType()).isEqualTo(StatisticsType.MATCH_3);
        assertThat(match3.getLuckyCount()).isEqualTo(10);

        assertThat(match4.getStatisticsType()).isEqualTo(StatisticsType.MATCH_4);
        assertThat(match4.getLuckyCount()).isEqualTo(5);

        assertThat(match5.getStatisticsType()).isEqualTo(StatisticsType.MATCH_5);
        assertThat(match5.getLuckyCount()).isEqualTo(2);

        assertThat(match5Bonus.getStatisticsType()).isEqualTo(StatisticsType.MATCH_5_BONUS);
        assertThat(match5Bonus.getLuckyCount()).isEqualTo(1);

        assertThat(match6.getStatisticsType()).isEqualTo(StatisticsType.MATCH_6);
        assertThat(match6.getLuckyCount()).isEqualTo(0);
    }

    @DisplayName("당첨 통계 타입을 통해 해당 등수의 상금을 확인할 수 있다")
    @Test
    void checkPrizeThroughStatisticsType() {
        // Given
        WinningStatistics match3 = new WinningStatistics(StatisticsType.MATCH_3, 1);
        WinningStatistics match4 = new WinningStatistics(StatisticsType.MATCH_4, 1);
        WinningStatistics match5 = new WinningStatistics(StatisticsType.MATCH_5, 1);
        WinningStatistics match5Bonus = new WinningStatistics(StatisticsType.MATCH_5_BONUS, 1);
        WinningStatistics match6 = new WinningStatistics(StatisticsType.MATCH_6, 1);

        // Then
        assertThat(match3.getStatisticsType().getPrize()).isEqualTo(5_000);
        assertThat(match4.getStatisticsType().getPrize()).isEqualTo(50_000);
        assertThat(match5.getStatisticsType().getPrize()).isEqualTo(1_500_000);
        assertThat(match5Bonus.getStatisticsType().getPrize()).isEqualTo(30_000_000);
        assertThat(match6.getStatisticsType().getPrize()).isEqualTo(2_000_000_000);
    }
}