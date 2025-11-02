package lotto.domain.application.service.impl;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.*;
import lotto.domain.application.service.LottoService;
import lotto.domain.entity.lotto.Lotto;
import lotto.domain.entity.statistics.StatisticsType;
import lotto.domain.entity.statistics.WinningStatistics;
import lotto.global.error.ErrorCode;
import lotto.global.error.ExceptionFactory;

public class LottoServiceImpl implements LottoService {

    @Override
    public List<Lotto> purchasedLottoTicket(int count) {
        validateCount(count);
        List<Lotto> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(generateLotto());
        }
        return result;
    }

    private void validateCount(int count) {
        if (count <= 0) {
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_PURCHASE_AMOUNT);
        }
    }

    private Lotto generateLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        if (numbers == null) {
            throw ExceptionFactory.nullPointer(ErrorCode.NULL_LOTTO_NUMBERS);
        }
        Collections.sort(new ArrayList<>(numbers));
        return new Lotto(numbers);
    }

    @Override
    public List<WinningStatistics> compareLottoTicket(List<Lotto> purchasedLottos, Lotto winningLotto, int bonusNumber) {
        validateCompareInputs(purchasedLottos, winningLotto, bonusNumber);
        EnumMap<StatisticsType, Integer> counts = initCountMap();
        Set<Integer> winningSet = new HashSet<>(winningLotto.getNumbers());

        for (Lotto purchased : purchasedLottos) {
            StatisticsType type = evaluateTicket(winningSet, bonusNumber, purchased);
            if (type != null) counts.put(type, counts.get(type) + 1);
        }
        return buildStatistics(counts);
    }

    private void validateCompareInputs(List<Lotto> purchased, Lotto winning, int bonusNumber) {
        if (purchased == null) throw ExceptionFactory.nullPointer(ErrorCode.NULL_PURCHASED_LOTTOS);
        if (winning == null) throw ExceptionFactory.nullPointer(ErrorCode.NULL_WINNING_LOTTO);
        if (bonusNumber < 1 || bonusNumber > 45)
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_BONUS_NUMBER_RANGE);
    }

    private EnumMap<StatisticsType, Integer> initCountMap() {
        EnumMap<StatisticsType, Integer> counts = new EnumMap<>(StatisticsType.class);
        for (StatisticsType type : StatisticsType.values()) counts.put(type, 0);
        return counts;
    }

    private List<WinningStatistics> buildStatistics(EnumMap<StatisticsType, Integer> counts) {
        return Arrays.asList(
                new WinningStatistics(StatisticsType.MATCH_3, counts.get(StatisticsType.MATCH_3)),
                new WinningStatistics(StatisticsType.MATCH_4, counts.get(StatisticsType.MATCH_4)),
                new WinningStatistics(StatisticsType.MATCH_5, counts.get(StatisticsType.MATCH_5)),
                new WinningStatistics(StatisticsType.MATCH_5_BONUS, counts.get(StatisticsType.MATCH_5_BONUS)),
                new WinningStatistics(StatisticsType.MATCH_6, counts.get(StatisticsType.MATCH_6))
        );
    }

    @Override
    public Float calculateProfitRate(int personalMoney, List<WinningStatistics> stats) {
        validateProfitInputs(personalMoney, stats);
        long totalPrize = calculateTotalPrize(stats);
        return calculateRate(personalMoney, totalPrize);
    }

    private void validateProfitInputs(int personalMoney, List<WinningStatistics> stats) {
        if (personalMoney <= 0)
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_PURCHASE_AMOUNT);
        if (stats == null)
            throw ExceptionFactory.nullPointer(ErrorCode.NULL_WINNING_STATISTICS);
    }

    private long calculateTotalPrize(List<WinningStatistics> stats) {
        long total = 0;
        for (WinningStatistics s : stats) {
            if (s == null || s.getStatisticsType() == null)
                throw ExceptionFactory.nullPointer(ErrorCode.NULL_WINNING_STATISTICS);
            total += (long) s.getStatisticsType().getPrize() * s.getLuckyCount();
        }
        return total;
    }

    private float calculateRate(int personalMoney, long totalPrize) {
        if (personalMoney == 0)
            throw ExceptionFactory.illegalArgument(ErrorCode.DIVIDE_BY_ZERO);
        float rate = ((float) totalPrize / personalMoney) * 100;
        return Math.round(rate * 100) / 100.0f;
    }

    private StatisticsType evaluateTicket(Set<Integer> winningSet, int bonusNumber, Lotto purchased) {
        validateTicketInputs(winningSet, purchased);
        List<Integer> numbers = purchased.getNumbers();
        int matchCount = countMatches(winningSet, numbers);
        boolean bonusMatch = (matchCount == 5) && numbers.contains(bonusNumber);
        return StatisticsType.of(matchCount, bonusMatch);
    }

    private void validateTicketInputs(Set<Integer> winningSet, Lotto purchased) {
        if (winningSet == null)
            throw ExceptionFactory.nullPointer(ErrorCode.NULL_WINNING_LOTTO);
        if (purchased == null || purchased.getNumbers() == null)
            throw ExceptionFactory.nullPointer(ErrorCode.NULL_LOTTO_NUMBERS);
    }

    private int countMatches(Set<Integer> winningSet, List<Integer> numbers) {
        int match = 0;
        for (Integer n : numbers) {
            if (n == null)
                throw ExceptionFactory.nullPointer(ErrorCode.NULL_LOTTO_NUMBER);
            if (winningSet.contains(n)) match++;
        }
        return match;
    }
}
