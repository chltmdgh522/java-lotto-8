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

public class LottoServiceImpl implements LottoService {


    // 구입 로또 저장
    @Override
    public List<Lotto> purchasedLottoTicket(int count) {
        List<Lotto> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> purchasedNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
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

        Set<Integer> winningSet = toSet(winningLotto.getNumbers());

        // 등수별 카운트 초기화 (1~5등 전부 0으로 시작)
        EnumMap<StatisticsType, Integer> counts = new EnumMap<>(StatisticsType.class);
        counts.put(StatisticsType.MATCH_6, 0);
        counts.put(StatisticsType.MATCH_5_BONUS, 0);
        counts.put(StatisticsType.MATCH_5, 0);
        counts.put(StatisticsType.MATCH_4, 0);
        counts.put(StatisticsType.MATCH_3, 0);

        for (Lotto purchasedLotto : purchasedLottos) {
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
        if (personalMoney <= 0 || winningStatistics == null || winningStatistics.isEmpty()) {
            return 0f;
        }

        long totalPrize = 0;

        // 등수별 상금 × 당첨 개수 합산
        for (WinningStatistics stat : winningStatistics) {
            StatisticsType type = stat.getStatisticsType();
            int count = stat.getLuckyCount();
            totalPrize += (long) type.getPrize() * count;
        }

        // 수익률 계산 (소수점 둘째 자리까지)
        float profitRate = ((float) totalPrize / personalMoney) * 100;
        return Math.round(profitRate * 100) / 100.0f;
    }


    // 계산 빠르게 하기 위해서
    private Set<Integer> toSet(List<Integer> numbers) {
        return new HashSet<>(numbers);
    }

    // 보너스 일치 여부
    private boolean matchesBonus(int bonusNumber, List<Integer> purchasedNumbers) {
        return purchasedNumbers.contains(bonusNumber);
    }

    // 단일 로또 평가 → 등수 결정
    private StatisticsType evaluateTicket(Set<Integer> winningSet, int bonusNumber, Lotto purchased) {
        List<Integer> nums = purchased.getNumbers();

        int matchCount = 0;
        for (Integer n : nums) {
            if (winningSet.contains(n)) {
                matchCount++;
            }
        }

        boolean bonusMatch = (matchCount == 5) && matchesBonus(bonusNumber, nums);
        // StatisticsType.of(matchCount, bonusMatch)는 3미만이면 null 반환
        return StatisticsType.of(matchCount, bonusMatch);
    }


}
