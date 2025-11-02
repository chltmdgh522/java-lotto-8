package lotto.domain.entity.statistics;

public class WinningStatistics {
    private final StatisticsType statisticsType;
    private Integer luckyCount;

    public WinningStatistics(StatisticsType statisticsType, Integer luckyCount) {
        this.statisticsType = statisticsType;
        this.luckyCount = luckyCount;
    }

    public Integer getLuckyCount() {
        return luckyCount;
    }

    public StatisticsType getStatisticsType(){
        return statisticsType;
    }
}
