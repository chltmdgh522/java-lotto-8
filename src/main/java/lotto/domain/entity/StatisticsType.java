package lotto.domain.entity;

public enum StatisticsType {

    MATCH_3(3, false, 5_000),
    MATCH_4(4, false, 50_000),
    MATCH_5(5, false, 1_500_000),
    MATCH_5_BONUS(5, true, 30_000_000),
    MATCH_6(6, false, 2_000_000_000);

    private final int matchCount;
    private final boolean bonusMatch;
    private final int prize;

    StatisticsType(int matchCount, boolean bonusMatch, int prize) {
        this.matchCount = matchCount;
        this.bonusMatch = bonusMatch;
        this.prize = prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusMatch() {
        return bonusMatch;
    }

    public int getPrize() {
        return prize;
    }

    /**
     * 일치 개수와 보너스 여부를 기반으로 적절한 당첨 등수를 반환한다.
     */
    public static StatisticsType of(int matchCount, boolean bonusMatch) {
        if (matchCount == 6) return MATCH_6;
        if (matchCount == 5 && bonusMatch) return MATCH_5_BONUS;
        if (matchCount == 5) return MATCH_5;
        if (matchCount == 4) return MATCH_4;
        if (matchCount == 3) return MATCH_3;
        return null;
    }
}
