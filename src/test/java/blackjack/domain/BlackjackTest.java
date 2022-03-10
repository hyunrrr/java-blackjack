package blackjack.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackTest {
    private Blackjack blackjack;
    private IntendedNumberGenerator intendedNumberGenerator;

    @BeforeEach
    void setUp() {
        List<String> playerNames = List.of("pobi", "jason");
        blackjack = new Blackjack(playerNames);
        intendedNumberGenerator = new IntendedNumberGenerator(List.of(1, 2, 3, 4, 5, 6));

        blackjack.distributeInitialCards(intendedNumberGenerator);
    }

    @DisplayName("플레이어, 딜러 카드 두장 분배 테스트")
    @Test
    void distributeInit() {
        Player dealer = blackjack.getDealer();
        List<Player> players = blackjack.getPlayers();

        Set<Integer> checker = new HashSet<>();
        int dealerCardSize = dealer.getMyCards().size();
        checker.add(dealerCardSize);

        players.forEach(player -> checker.add(player.getMyCards().size()));

        assertThat(checker.size() == 1 && checker.contains(2)).isTrue();
    }

    @DisplayName("카드 한장 더 분배 테스트_딜러 hit")
    @Test
    void distributeOneMoreCardDealer() {
        NumberGenerator numberGenerator = new IntendedNumberGenerator(List.of(12));
        blackjack.distributeAdditionalCardDealer(numberGenerator);
        assertThat(blackjack.getDealer().getMyCards().size()).isEqualTo(3);
    }

    @DisplayName("카드 한장 더 분배 테스트_딜러 stay")
    @Test
    void distributeOneMoreCardDealer2() {
        NumberGenerator numberGenerator = new IntendedNumberGenerator(List.of(12, 9));
        blackjack.distributeAdditionalCardDealer(numberGenerator);
        assertThat(blackjack.getDealer().getMyCards().size()).isEqualTo(3);
    }

    @DisplayName("최종 승패 기능 테스트")
    @Test
    void result() {
       assertDoesNotThrow(() -> blackjack.result());
    }
}
