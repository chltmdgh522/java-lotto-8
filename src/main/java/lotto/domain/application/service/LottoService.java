package lotto.domain.application.service;

import java.util.List;
import lotto.domain.entity.lotto.Lotto;
import lotto.domain.entity.statistics.WinningStatistics;

public interface LottoService {
    List<Lotto> purchasedLottoTicket(int count);

    List<WinningStatistics> compareLottoTicket(List<Lotto> purchasedLottos, Lotto winningLotto, int bonusNumber);

    Float calculateProfitRate(int personalMoney, List<WinningStatistics> winningStatistics);

}
