package lotto.domain.presentation.view.parser;

import lotto.global.error.exception.LottoNumberFormatException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoInputParserTest {

    @DisplayName("유효한 구매 금액 입력을 파싱할 수 있다")
    @Test
    void parseValidPurchaseAmount() {
        // Given
        String input = "8000";

        // When
        int amount = LottoInputParser.parsePurchaseAmount(input);

        // Then
        assertThat(amount).isEqualTo(8000);
    }

    @DisplayName("공백이 포함된 구매 금액 입력을 파싱할 수 있다")
    @Test
    void parseValidPurchaseAmountWithSpace() {
        // Given
        String input = "  8000  ";

        // When
        int amount = LottoInputParser.parsePurchaseAmount(input);

        // Then
        assertThat(amount).isEqualTo(8000);
    }

    @DisplayName("숫자가 아닌 구매 금액 입력은 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "1000원", "1,000", "천원", " "})
    void parseInvalidPurchaseAmount(String input) {
        // When, Then
        assertThatThrownBy(() -> LottoInputParser.parsePurchaseAmount(input))
                .isInstanceOf(LottoNumberFormatException.class);
    }

    @DisplayName("유효한 당첨 번호 입력을 파싱할 수 있다")
    @Test
    void parseValidWinningNumbers() {
        // Given
        String input = "1,2,3,4,5,6";

        // When
        List<Integer> numbers = LottoInputParser.parseWinningNumbers(input);

        // Then
        assertThat(numbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("공백이 포함된 당첨 번호 입력을 파싱할 수 있다")
    @Test
    void parseValidWinningNumbersWithSpace() {
        // Given
        String input = "1, 2, 3, 4, 5, 6";

        // When
        List<Integer> numbers = LottoInputParser.parseWinningNumbers(input);

        // Then
        assertThat(numbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("유효하지 않은 당첨 번호 입력은 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"a,b,c,d,e,f", "1;2;3;4;5;6", "one,two,three,four,five,six"})
    void parseInvalidWinningNumbers(String input) {
        // When, Then
        assertThatThrownBy(() -> LottoInputParser.parseWinningNumbers(input))
                .isInstanceOf(LottoNumberFormatException.class);
    }

    @DisplayName("유효한 보너스 번호 입력을 파싱할 수 있다")
    @Test
    void parseValidBonusNumber() {
        // Given
        String input = "7";

        // When
        int bonusNumber = LottoInputParser.parseBonusNumber(input);

        // Then
        assertThat(bonusNumber).isEqualTo(7);
    }

    @DisplayName("공백이 포함된 보너스 번호 입력을 파싱할 수 있다")
    @Test
    void parseValidBonusNumberWithSpace() {
        // Given
        String input = "  7  ";

        // When
        int bonusNumber = LottoInputParser.parseBonusNumber(input);

        // Then
        assertThat(bonusNumber).isEqualTo(7);
    }

    @DisplayName("숫자가 아닌 보너스 번호 입력은 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"a", "7번", "일곱", " "})
    void parseInvalidBonusNumber(String input) {
        // When, Then
        assertThatThrownBy(() -> LottoInputParser.parseBonusNumber(input))
                .isInstanceOf(LottoNumberFormatException.class);
    }
}