package lotto.domain.presentation.view;

import lotto.domain.entity.lotto.Lotto;
import lotto.domain.entity.statistics.StatisticsType;
import lotto.domain.entity.statistics.WinningStatistics;
import lotto.global.message.MessageCode;

import java.util.List;

public class OutputView {

    // 에러 메시지 출력
    public static void printError(String message) {
        System.out.println("[ERROR] " + message);
    }

    // 구매한 로또 수량 출력
    public static void printPurchasedLottoCount(int count) {
        System.out.println(String.format(
                MessageCode.PURCHASE_LOTTO_COUNT.getMessage(),
                count
        ));
    }

    // 구매한 로또 번호 목록 출력
    public static void printPurchasedLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    // 당첨 통계 출력
    public static void printWinningStatistics(List<WinningStatistics> statistics) {
        System.out.println(MessageCode.WINNING_STATISTICS_TITLE.getMessage());

        for (WinningStatistics stat : statistics) {
            printSingleStatistic(stat);
        }
    }

    // 단일 당첨 통계 출력
    private static void printSingleStatistic(WinningStatistics stat) {
        StatisticsType type = stat.getStatisticsType();
        String bonusText = getBonusText(type);

        System.out.println(String.format(
                MessageCode.WINNING_STATISTICS_FORMAT.getMessage(),
                type.getMatchCount(),
                bonusText,
                type.getPrize(),
                stat.getLuckyCount()
        ));
    }

    // 보너스 볼 문구 반환
    private static String getBonusText(StatisticsType type) {
        if (type.isBonusMatch()) {
            return MessageCode.WINNING_STATISTICS_BONUS.getMessage();
        }
        return "";
    }

    // 수익률 출력
    public static void printProfitRate(float profitRate) {
        System.out.println(String.format(
                MessageCode.PROFIT_RATE_FORMAT.getMessage(),
                profitRate
        ));
    }
}