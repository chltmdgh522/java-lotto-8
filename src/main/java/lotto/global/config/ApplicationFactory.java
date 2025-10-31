package lotto.global.config;

import lotto.domain.application.service.LottoService;
import lotto.domain.application.service.impl.LottoServiceImpl;
import lotto.domain.presentation.controller.LottoController;


public class ApplicationFactory {

    private ApplicationFactory(){

    }

    public static ApplicationRunner createApplicationRunner() {
        LottoService lottoService = createLottoService();
        LottoController lottoController = createLottoController(lottoService);
        return new ApplicationRunner(lottoController);
    }

    /**
     * 컨트롤러 생성
     *
     * @param lottoService 서비스 구현체
     * @return 구성된 LottoController 객체
     */
    private static LottoController createLottoController(LottoService lottoService) {
        return new LottoController(lottoService);
    }

    /**
     * 서비스 구현체 생성
     *
     * @return 구성된 LottoService 객체
     */
    private static LottoService createLottoService() {
        return new LottoServiceImpl();
    }

}