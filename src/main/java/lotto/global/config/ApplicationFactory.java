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

    private static LottoController createLottoController(LottoService lottoService) {
        return new LottoController(lottoService);
    }

    private static LottoService createLottoService() {
        return new LottoServiceImpl();
    }

}