package lotto.domain.presentation.controller;

import lotto.domain.application.service.LottoService;

public class LottoController {
    private final LottoService lottoService;

    public LottoController(LottoService lottoService){
        this.lottoService = lottoService;
    }
}
