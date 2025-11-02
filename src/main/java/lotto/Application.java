package lotto;

import lotto.global.config.ApplicationFactory;
import lotto.global.config.ApplicationRunner;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        ApplicationRunner applicationRunner = ApplicationFactory.createApplicationRunner();
        applicationRunner.run();
    }
}
