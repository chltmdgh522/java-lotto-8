package lotto.domain.presentation.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.global.message.MessageCode;


public class InputView {
    public static String readPurchaseAmount() {
        System.out.println(MessageCode.PURCHASE_AMOUNT_INPUT.getMessage());
        return Console.readLine();
    }

    public static String readWinningNumbers() {
        System.out.println(MessageCode.WINNING_NUMBERS_INPUT.getMessage());
        return Console.readLine();
    }

    public static String readBonusNumber() {
        System.out.println(MessageCode.BONUS_NUMBER_INPUT.getMessage());
        return Console.readLine();
    }
}
