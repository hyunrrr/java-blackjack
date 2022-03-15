package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @DisplayName("중복된 플레이어 이름이 입력되면 예외 발생하는지 테스트")
    @Test
    void noDuplicatedNameTest() {
        assertThatThrownBy(() -> new Players(List.of("pobi", "jason", "pobi")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("맨 처음 순서 플레이어 가져오는 기능 테스트")
    @Test
    void firstPlayerTest() {
        Players players = new Players(List.of("pobi", "jason"));
        assertThat(players.firstPlayer().getName().equals("pobi")).isTrue();
    }

    @DisplayName("다음차례 플레이어 가져오는 기능 테스트")
    @Test
    void nextPlayerTest() {
        Players players = new Players(List.of("pobi", "jason"));
        assertThat(players.nextPlayer(players.firstPlayer()).getName().equals("jason")).isTrue();
    }

    @DisplayName("다음차례 플레이어 없을때 다음차례 플레이어 가져오는 기능이 호출되면 Null 리턴하는지 테스트")
    @Test
    void nextPlayerTest2() {
        Players players = new Players(List.of("pobi", "jason"));
        Player player = players.nextPlayer(players.firstPlayer());
        assertThat(players.nextPlayer(player)).isNull();
    }

    @DisplayName("다음차례 플레이어가 있는지 확인하는 기능에서 다음차례 플레이어 있으면 true리턴하는지 테스트")
    @Test
    void hasNextPlayerTest() {
        Players players = new Players(List.of("pobi", "jason"));
        assertThat(players.hasNextPlayer(players.firstPlayer())).isTrue();
    }

    @DisplayName("다음차례 플레이어가 있는지 확인하는 기능에서 다음차례 플레이어 없으면 false리턴하는지 테스트")
    @Test
    void hasNextPlayerTest2() {
        Players players = new Players(List.of("pobi", "jason"));
        Player player = players.nextPlayer(players.firstPlayer());
        assertThat(players.hasNextPlayer(player)).isFalse();
    }
}
