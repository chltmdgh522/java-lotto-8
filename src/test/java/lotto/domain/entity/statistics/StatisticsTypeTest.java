package lotto.domain.entity.statistics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class StatisticsTypeTest {

    @DisplayName("일치하는 번호 개수와 보너스 번호 일치 여부에 따라 올바른 통계 타입을 반환한다")
    @ParameterizedTest
    @CsvSource({
            "3, false, MATCH_3",
            "4, false, MATCH_4",
            "5, false, MATCH_5",
            "5, true, MATCH_5_BONUS",
            "6, false, MATCH_6"
    })
    void returnCorrectStatisticsType(int matchCount, boolean bonusMatch, StatisticsType expected) {
        // When
        StatisticsType actual = StatisticsType.of(matchCount, bonusMatch);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("3개 미만의 일치 번호 개수에 대해서는 null을 반환한다")
    @ParameterizedTest
    @CsvSource({
            "0, false",
            "1, false",
            "2, false"
    })
    void returnNullForLessThanThreeMatches(int matchCount, boolean bonusMatch) {
        // When
        StatisticsType actual = StatisticsType.of(matchCount, bonusMatch);

        // Then
        assertThat(actual).isNull();
    }

    @DisplayName("6개 일치(1등)는 보너스 번호 일치 여부와 관계없이 MATCH_6을 반환한다")
    @Test
    void returnMatch6ForSixMatchesRegardlessOfBonus() {
        // When
        StatisticsType withBonus = StatisticsType.of(6, true);
        StatisticsType withoutBonus = StatisticsType.of(6, false);

        // Then
        assertThat(withBonus).isEqualTo(StatisticsType.MATCH_6);
        assertThat(withoutBonus).isEqualTo(StatisticsType.MATCH_6);
    }

    @DisplayName("각 등수별로 올바른 상금을 반환한다")
    @Test
    void returnCorrectPrizeForEachRank() {
        // Then
        assertThat(StatisticsType.MATCH_3.getPrize()).isEqualTo(5_000);
        assertThat(StatisticsType.MATCH_4.getPrize()).isEqualTo(50_000);
        assertThat(StatisticsType.MATCH_5.getPrize()).isEqualTo(1_500_000);
        assertThat(StatisticsType.MATCH_5_BONUS.getPrize()).isEqualTo(30_000_000);
        assertThat(StatisticsType.MATCH_6.getPrize()).isEqualTo(2_000_000_000);
    }

    @DisplayName("각 등수별로 올바른 일치 번호 개수를 반환한다")
    @Test
    void returnCorrectMatchCountForEachRank() {
        // Then
        assertThat(StatisticsType.MATCH_3.getMatchCount()).isEqualTo(3);
        assertThat(StatisticsType.MATCH_4.getMatchCount()).isEqualTo(4);
        assertThat(StatisticsType.MATCH_5.getMatchCount()).isEqualTo(5);
        assertThat(StatisticsType.MATCH_5_BONUS.getMatchCount()).isEqualTo(5);
        assertThat(StatisticsType.MATCH_6.getMatchCount()).isEqualTo(6);
    }

    @DisplayName("보너스 번호 일치 여부를 올바르게 반환한다")
    @Test
    void returnCorrectBonusMatchForEachRank() {
        // Then
        assertThat(StatisticsType.MATCH_3.isBonusMatch()).isFalse();
        assertThat(StatisticsType.MATCH_4.isBonusMatch()).isFalse();
        assertThat(StatisticsType.MATCH_5.isBonusMatch()).isFalse();
        assertThat(StatisticsType.MATCH_5_BONUS.isBonusMatch()).isTrue();
        assertThat(StatisticsType.MATCH_6.isBonusMatch()).isFalse();
    }
}