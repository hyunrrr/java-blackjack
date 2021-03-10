package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {
    @DisplayName("플레이어는 이름을 받아 생성된다.")
    @Test
    void playerCreationTest() {
        Player player = new Player("youngE");
        assertThat(player).isInstanceOf(Player.class);
    }

    @DisplayName("플레이어 이름에 공백이 입력되는 경우 예외를 발생시킨다.")
    @Test
    void playerCreationErrorTest() {
        assertThatThrownBy(() -> {
            new Player(" ");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("히트 - 플레이어는 카드를 받는다.")
    @Test
    void hitCard() {
        Player player = new Player("Player");
        CardDeck cardDeck = CardDeck.createDeck();
        player.addCard(cardDeck.drawCard());
        assertThat(player.getCards().cards()).hasSize(1);
    }

    @DisplayName("isBust:true - 플레이어 카드의 총합이 21을 초과하는 경우 자동 중단")
    @Test
    void isBustTrueWhenOver21Score() {
        Player player = new Player("Player");

        Card card1 = new Card(Suit.CLUB, Denomination.JACK);
        Card card2 = new Card(Suit.CLUB, Denomination.SEVEN);
        Card card3 = new Card(Suit.CLUB, Denomination.SIX);

        player.addCard(card1);
        player.addCard(card2);
        player.addCard(card3);

        assertTrue(player.isBust());
    }
}