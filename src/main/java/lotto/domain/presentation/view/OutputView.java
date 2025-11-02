package lotto.domain.presentation.view;

import lotto.domain.entity.lotto.Lotto;
import lotto.domain.entity.statistics.StatisticsType;
import lotto.domain.entity.statistics.WinningStatistics;
import lotto.global.message.MessageCode;
import java.util.List;

public class OutputView {

    public static void printError(String message) {
        System.out.println("[ERROR] " + message);
    }

    public static void printPurchasedLottoCount(int count) {
        System.out.println(String.format(
                MessageCode.PURCHASE_LOTTO_COUNT.getMessage(),
                count
        ));
    }

    public static void printPurchasedLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public static void printWinningStatistics(List<WinningStatistics> statistics) {
        System.out.println(MessageCode.WINNING_STATISTICS_TITLE.getMessage());
        for (WinningStatistics stat : statistics) {
            printSingleStatistic(stat);
        }
    }

    private static void printSingleStatistic(WinningStatistics stat) {
        StatisticsType type = stat.getStatisticsType();
        System.out.println(String.format(
                MessageCode.WINNING_STATISTICS_FORMAT.getMessage(),
                type.getMatchCount(),
                getBonusText(type),
                type.getPrize(),
                stat.getLuckyCount()
        ));
    }

    private static String getBonusText(StatisticsType type) {
        if (type.isBonusMatch()) {
            return MessageCode.WINNING_STATISTICS_BONUS.getMessage();
        }
        return "";
    }

    public static void printProfitRate(float profitRate) {
        System.out.println(String.format(
                MessageCode.PROFIT_RATE_FORMAT.getMessage(),
                profitRate
        ));
    }
}
