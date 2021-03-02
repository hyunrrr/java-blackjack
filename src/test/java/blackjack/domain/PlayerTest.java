package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    @Test
    @DisplayName("이름에 공백 입력 경우 예외 처리")
    void playerNameSplitException() {
        String input = "pobi, jason";
        assertThatThrownBy(() -> {
            for (String name : input.split(",")) {
                Player.create(name);
            }
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
