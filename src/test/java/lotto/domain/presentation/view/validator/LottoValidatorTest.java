package lotto.domain.presentation.view.validator;

import lotto.global.error.exception.LottoIllegalArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoValidatorTest {

    @DisplayName("1,000원 단위의 유효한 구매 금액은 검증을 통과한다")
    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 5000, 10000, 100000})
    void validateValidPurchaseAmount(int amount) {
        // When, Then
        assertThatCode(() -> LottoValidator.validatePurchaseAmount(amount))
                .doesNotThrowAnyException();
    }

    @DisplayName("1,000원 단위가 아닌 구매 금액은 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {0, -1000, 500, 1500, 999})
    void validateInvalidPurchaseAmount(int amount) {
        // When, Then
        assertThatThrownBy(() -> LottoValidator.validatePurchaseAmount(amount))
                .isInstanceOf(LottoIllegalArgumentException.class);
    }

    @DisplayName("유효한 당첨 번호는 검증을 통과한다")
    @Test
    void validateValidWinningNumbers() {
        // Given
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        // When, Then
        assertThatCode(() -> LottoValidator.validateWinningNumbers(numbers))
                .doesNotThrowAnyException();
    }

    @DisplayName("null인 당첨 번호 리스트는 예외가 발생한다")
    @Test
    void validateNullWinningNumbers() {
        // When, Then
        assertThatThrownBy(() -> LottoValidator.validateWinningNumbers(null))
                .isInstanceOf(LottoIllegalArgumentException.class);
    }

    @DisplayName("6개가 아닌 당첨 번호 리스트는 예외가 발생한다")
    @Test
    void validateWinningNumbersWithInvalidSize() {
        // Given
        List<Integer> tooFew = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> tooMany = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        // When, Then
        assertThatThrownBy(() -> LottoValidator.validateWinningNumbers(tooFew))
                .isInstanceOf(LottoIllegalArgumentException.class);
        assertThatThrownBy(() -> LottoValidator.validateWinningNumbers(tooMany))
                .isInstanceOf(LottoIllegalArgumentException.class);
    }

    @DisplayName("범위를 벗어난 당첨 번호는 예외가 발생한다")
    @Test
    void validateWinningNumbersOutOfRange() {
        // Given
        List<Integer> belowRange = Arrays.asList(0, 2, 3, 4, 5, 6);
        List<Integer> aboveRange = Arrays.asList(1, 2, 3, 4, 5, 46);

        // When, Then
        assertThatThrownBy(() -> LottoValidator.validateWinningNumbers(belowRange))
                .isInstanceOf(LottoIllegalArgumentException.class);
        assertThatThrownBy(() -> LottoValidator.validateWinningNumbers(aboveRange))
                .isInstanceOf(LottoIllegalArgumentException.class);
    }

    @DisplayName("중복된 당첨 번호는 예외가 발생한다")
    @Test
    void validateDuplicateWinningNumbers() {
        // Given
        List<Integer> duplicates = Arrays.asList(1, 2, 3, 4, 5, 5);

        // When, Then
        assertThatThrownBy(() -> LottoValidator.validateWinningNumbers(duplicates))
                .isInstanceOf(LottoIllegalArgumentException.class);
    }

    @DisplayName("유효한 보너스 번호는 검증을 통과한다")
    @Test
    void validateValidBonusNumber() {
        // Given
        int bonusNumber = 7;
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        // When, Then
        assertThatCode(() -> LottoValidator.validateBonusNumber(bonusNumber, winningNumbers))
                .doesNotThrowAnyException();
    }

    @DisplayName("범위를 벗어난 보너스 번호는 예외가 발생한다")
    @Test
    void validateBonusNumberOutOfRange() {
        // Given
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int belowRange = 0;
        int aboveRange = 46;

        // When, Then
        assertThatThrownBy(() -> LottoValidator.validateBonusNumber(belowRange, winningNumbers))
                .isInstanceOf(LottoIllegalArgumentException.class);
        assertThatThrownBy(() -> LottoValidator.validateBonusNumber(aboveRange, winningNumbers))
                .isInstanceOf(LottoIllegalArgumentException.class);
    }

    @DisplayName("당첨 번호와 중복된 보너스 번호는 예외가 발생한다")
    @Test
    void validateDuplicateBonusNumber() {
        // Given
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int duplicateBonusNumber = 3; // 당첨 번호 중 하나와 동일

        // When, Then
        assertThatThrownBy(() -> LottoValidator.validateBonusNumber(duplicateBonusNumber, winningNumbers))
                .isInstanceOf(LottoIllegalArgumentException.class);
    }
}