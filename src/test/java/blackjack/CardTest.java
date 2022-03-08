package blackjack;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    @DisplayName("카드 생성자 테스트")
    @Test
    void construct() {
        assertDoesNotThrow(() -> new Card("3다이아몬드"));
    }

}