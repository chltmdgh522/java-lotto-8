package lotto.domain.application.service;

import java.util.List;
import lotto.domain.entity.lotto.Lotto;
import lotto.domain.entity.statistics.WinningStatistics;

public interface LottoService {




    // 구입 로또 배분 후 저장
    List<Lotto> purchasedLottoTicket(Lotto winningLotto, int bonusNumber, int count);

    // 담청 일치 계산
    List<WinningStatistics> compareLottoTicket(List<Lotto> purchasedLottos, Lotto winningLotto,
                                               int bonusNumber);


    // 수익률 계산 후 저장
    Float calculateProfitRate(int personalMoney, List<WinningStatistics> winningStatistics);

}
