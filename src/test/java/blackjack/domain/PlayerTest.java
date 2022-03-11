package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("카드 받는 기능 테스트")
    @Test
    void addCard() {
        Player player = new Player("pobi");
        player.addCard(new Card("3다이아몬드", 3));
        int playerCardSize = player.getMyCards().size();
        assertThat(playerCardSize).isEqualTo(1);
    }

    @DisplayName("버스트 테스트_버스트")
    @Test
    void burst() {
        Player player = new Player("pobi");
        player.addCard(new Card("10다이아몬드", 10));
        player.addCard(new Card("10다이아몬드", 10));
        player.addCard(new Card("10다이아몬드", 10));
        assertThat(player.isBurst()).isTrue();
    }

    @DisplayName("버스트 테스트_버스트아님")
    @Test
    void burst2() {
        Player player = new Player("pobi");
        player.addCard(new Card("10다이아몬드", 10));
        assertThat(player.isBurst()).isFalse();
    }

    @DisplayName("이름 같은지 확인하는 테스트_같음")
    @Test
    void isSameNameTest() {
        Player player = new Player("pobi");
        assertThat(player.isSameName("pobi")).isEqualTo(true);
    }

    @DisplayName("이름 같은지 확인하는 테스트_다름")
    @Test
    void isSameNameTest2() {
        Player player = new Player("pobi");
        assertThat(player.isSameName("jason")).isEqualTo(false);
    }

    @DisplayName("카드 보유 확인 기능 테스트_있음")
    @Test
    void containCardTest() {
        Player player = new Player("pobi");
        Card card = new Card("3다이아몬드", 3);
        player.addCard(card);

        assertThat(player.containCard(card)).isTrue();
    }

    @DisplayName("카드 보유 확인 기능 테스트_없음")
    @Test
    void containCardTest2() {
        Player player = new Player("pobi");
        Card card = new Card("3다이아몬드", 3);
        player.addCard(card);

        assertThat(player.containCard(new Card("2다이아몬드", 2))).isFalse();
    }
}
