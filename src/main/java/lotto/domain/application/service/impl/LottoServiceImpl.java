package lotto.domain.application.service.impl;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.domain.application.service.LottoService;
import lotto.domain.entity.lotto.Lotto;
import lotto.domain.entity.statistics.StatisticsType;
import lotto.domain.entity.statistics.WinningStatistics;
import lotto.global.error.ErrorCode;
import lotto.global.error.ExceptionFactory;

public class LottoServiceImpl implements LottoService {

    // 구입 로또 저장
    @Override
    public List<Lotto> purchasedLottoTicket(int count) {
        if (count <= 0) {
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_PURCHASE_AMOUNT);
        }

        List<Lotto> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> purchasedNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            if (purchasedNumbers == null) {
                throw ExceptionFactory.nullPointer(ErrorCode.NULL_LOTTO_NUMBERS);
            }

            Collections.sort(purchasedNumbers);
            Lotto purchasedLotto = new Lotto(purchasedNumbers);
            result.add(purchasedLotto);
        }
        return result;
    }

    // 로또 비교

    /**
     * 구매한 모든 티켓을 당첨 번호와 비교하여 1~5등 등수별 당첨 건수를 집계해 반환한다.
     *
     * @return 등수별 당첨 건수 리스트 (1~5등 모두 포함, 건수 0일 수 있음)
     */
    @Override
    public List<WinningStatistics> compareLottoTicket(List<Lotto> purchasedLottos, Lotto winningLotto,
                                                      int bonusNumber) {
        if (purchasedLottos == null) {
            throw ExceptionFactory.nullPointer(ErrorCode.NULL_PURCHASED_LOTTOS);
        }

        if (winningLotto == null) {
            throw ExceptionFactory.nullPointer(ErrorCode.NULL_WINNING_LOTTO);
        }

        if (bonusNumber < 1 || bonusNumber > 45) {
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_BONUS_NUMBER_RANGE);
        }

        Set<Integer> winningSet = toSet(winningLotto.getNumbers());

        // 등수별 카운트 초기화 (1~5등 전부 0으로 시작)
        EnumMap<StatisticsType, Integer> counts = new EnumMap<>(StatisticsType.class);
        counts.put(StatisticsType.MATCH_6, 0);
        counts.put(StatisticsType.MATCH_5_BONUS, 0);
        counts.put(StatisticsType.MATCH_5, 0);
        counts.put(StatisticsType.MATCH_4, 0);
        counts.put(StatisticsType.MATCH_3, 0);

        for (Lotto purchasedLotto : purchasedLottos) {
            if (purchasedLotto == null) {
                throw ExceptionFactory.nullPointer(ErrorCode.NULL_LOTTO_NUMBERS);
            }

            StatisticsType type = evaluateTicket(winningSet, bonusNumber, purchasedLotto);
            if (type != null) {
                counts.put(type, counts.get(type) + 1);
            }
        }

        // 등수 순서대로 결과 리스트 구성
        return Arrays.asList(
                new WinningStatistics(StatisticsType.MATCH_3, counts.get(StatisticsType.MATCH_3)),
                new WinningStatistics(StatisticsType.MATCH_4, counts.get(StatisticsType.MATCH_4)),
                new WinningStatistics(StatisticsType.MATCH_5, counts.get(StatisticsType.MATCH_5)),
                new WinningStatistics(StatisticsType.MATCH_5_BONUS, counts.get(StatisticsType.MATCH_5_BONUS)),
                new WinningStatistics(StatisticsType.MATCH_6, counts.get(StatisticsType.MATCH_6))
        );
    }


    @Override
    public Float calculateProfitRate(int personalMoney, List<WinningStatistics> winningStatistics) {
        if (personalMoney <= 0) {
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_PURCHASE_AMOUNT);
        }

        if (winningStatistics == null) {
            throw ExceptionFactory.nullPointer(ErrorCode.NULL_WINNING_STATISTICS);
        }

        if (winningStatistics.isEmpty()) {
            return 0f;
        }

        long totalPrize = 0;

        // 등수별 상금 × 당첨 개수 합산
        for (WinningStatistics stat : winningStatistics) {
            if (stat == null) {
                throw ExceptionFactory.nullPointer(ErrorCode.NULL_WINNING_STATISTICS);
            }

            StatisticsType type = stat.getStatisticsType();
            if (type == null) {
                throw ExceptionFactory.nullPointer(ErrorCode.NULL_WINNING_STATISTICS);
            }

            int count = stat.getLuckyCount();
            totalPrize += (long) type.getPrize() * count;
        }

        // 0으로 나누기 방지
        if (personalMoney == 0) {
            throw ExceptionFactory.illegalArgument(ErrorCode.DIVIDE_BY_ZERO);
        }

        // 수익률 계산 (소수점 둘째 자리까지)
        float profitRate = ((float) totalPrize / personalMoney) * 100;
        return Math.round(profitRate * 100) / 100.0f;
    }


    // 계산 빠르게 하기 위해서
    private Set<Integer> toSet(List<Integer> numbers) {
        if (numbers == null) {
            throw ExceptionFactory.nullPointer(ErrorCode.NULL_LOTTO_NUMBERS);
        }

        return new HashSet<>(numbers);
    }

    // 보너스 일치 여부
    private boolean matchesBonus(int bonusNumber, List<Integer> purchasedNumbers) {
        if (purchasedNumbers == null) {
            throw ExceptionFactory.nullPointer(ErrorCode.NULL_LOTTO_NUMBERS);
        }

        return purchasedNumbers.contains(bonusNumber);
    }

    // 단일 로또 평가 → 등수 결정
    private StatisticsType evaluateTicket(Set<Integer> winningSet, int bonusNumber, Lotto purchased) {
        if (winningSet == null) {
            throw ExceptionFactory.nullPointer(ErrorCode.NULL_WINNING_LOTTO);
        }

        if (purchased == null) {
            throw ExceptionFactory.nullPointer(ErrorCode.NULL_LOTTO_NUMBERS);
        }

        List<Integer> nums = purchased.getNumbers();
        if (nums == null) {
            throw ExceptionFactory.nullPointer(ErrorCode.NULL_LOTTO_NUMBERS);
        }

        int matchCount = 0;
        for (Integer n : nums) {
            if (n == null) {
                throw ExceptionFactory.nullPointer(ErrorCode.NULL_LOTTO_NUMBER);
            }

            if (winningSet.contains(n)) {
                matchCount++;
            }
        }

        boolean bonusMatch = (matchCount == 5) && matchesBonus(bonusNumber, nums);
        // StatisticsType.of(matchCount, bonusMatch)는 3미만이면 null 반환
        return StatisticsType.of(matchCount, bonusMatch);
    }
}