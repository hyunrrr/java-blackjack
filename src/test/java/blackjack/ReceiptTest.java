package blackjack;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReceiptTest {

    @DisplayName("최소배팅금액 보다 적은 돈이면 예외발생하는지 테스트")
    @Test
    void generateTest() {
        assertThatThrownBy(() -> Receipt.generate(500)).isInstanceOf(IllegalArgumentException.class);
    }

}