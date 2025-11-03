package lotto;

import lotto.domain.entity.lotto.Lotto;
import lotto.global.error.exception.LottoIllegalArgumentException;
import lotto.global.error.exception.LottoNullPointerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다")
    @Test
    void createLottoWithMoreThanSixNumbers() {
        // Given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7);

        // When, Then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(LottoIllegalArgumentException.class);
    }

    @DisplayName("로또 번호의 개수가 6개 미만이면 예외가 발생한다")
    @Test
    void createLottoWithLessThanSixNumbers() {
        // Given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        // When, Then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(LottoIllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다")
    @Test
    void createLottoWithDuplicateNumbers() {
        // Given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

        // When, Then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(LottoIllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 1보다 작으면 예외가 발생한다")
    @Test
    void createLottoWithNumberLessThanOne() {
        // Given
        List<Integer> numbers = List.of(0, 2, 3, 4, 5, 6);

        // When, Then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(LottoIllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 45보다 크면 예외가 발생한다")
    @Test
    void createLottoWithNumberGreaterThanFortyFive() {
        // Given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 46);

        // When, Then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(LottoIllegalArgumentException.class);
    }

    @DisplayName("로또 번호 리스트가 null이면 예외가 발생한다")
    @Test
    void createLottoWithNullList() {
        // Given
        List<Integer> numbers = null;

        // When, Then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(LottoNullPointerException.class);
    }

    @DisplayName("로또 번호에 null 값이 포함되면 예외가 발생한다")
    @Test
    void createLottoWithNullElement() {
        // Given
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(null);
        numbers.add(5);
        numbers.add(6);

        // When, Then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(LottoNullPointerException.class);
    }

    @DisplayName("유효한 로또 번호로 로또 객체가 생성된다")
    @Test
    void createValidLotto() {
        // Given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        // When
        Lotto lotto = new Lotto(numbers);

        // Then
        assertThat(lotto.getNumbers()).containsExactlyInAnyOrderElementsOf(numbers);
        assertThat(lotto.getNumbers()).hasSize(6);
    }
}