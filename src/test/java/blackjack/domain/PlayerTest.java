package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("카드 받는 기능 테스트")
    @Test
    void addCard() {
        Player player = new Player("pobi");
        player.addCard(Card.generateCard(BlackjackCardType.DIAMOND_3));
        int playerCardSize = player.getMyCards().size();
        assertThat(playerCardSize).isEqualTo(1);
    }

    @DisplayName("버스트 테스트_버스트")
    @Test
    void burst() {
        Player player = new Player("pobi");
        player.addCard(Card.generateCard(BlackjackCardType.DIAMOND_10));
        player.addCard(Card.generateCard(BlackjackCardType.DIAMOND_10));
        player.addCard(Card.generateCard(BlackjackCardType.DIAMOND_10));
        assertThat(player.isBurst()).isTrue();
    }

    @DisplayName("버스트 테스트_버스트아님")
    @Test
    void burst2() {
        Player player = new Player("pobi");
        player.addCard(Card.generateCard(BlackjackCardType.DIAMOND_10));
        assertThat(player.isBurst()).isFalse();
    }

    @DisplayName("이름 같은지 확인하는 테스트_같음")
    @Test
    void isSameNameTest() {
        Player player = new Player("pobi");
        assertThat(player.getName().equals("pobi")).isEqualTo(true);
    }

    @DisplayName("이름 같은지 확인하는 테스트_다름")
    @Test
    void isSameNameTest2() {
        Player player = new Player("pobi");
        assertThat(player.getName().equals("jason")).isEqualTo(false);
    }
}
