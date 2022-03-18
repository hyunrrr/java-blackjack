package blackjack.domain;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Blackjack {
    private static final int INITIAL_CARD_NUMBER = 2;

    private final Dealer dealer;
    private final Players players;
    private Player turnPlayer;

    public Blackjack(List<String> playerNames) {
        this.dealer = new Dealer();
        this.players = new Players(playerNames);
        this.turnPlayer = players.firstPlayer();
    }

    public void updateTurnPlayer() {
        turnPlayer = players.nextPlayer(turnPlayer);
    }

    public boolean cycleIsOver() {
        if (Objects.isNull(turnPlayer)) {
            turnPlayer = players.firstPlayer();
            return true;
        }
        return false;
    }

    public Player turnPlayer() {
        return turnPlayer;
    }

    public void addtionalCardToPlayer(NumberGenerator numberGenerator, Player player, boolean addCondition) {
        if (!addCondition) {
            updateTurnPlayer();
            return;
        }
        player.addCard(dealer.handOutCard(numberGenerator));

        if (player.isBurst()) {
            updateTurnPlayer();
        }
    }

    public Players getPlayers() {
        return players;
    }

    public void distributeInitialCards(NumberGenerator numberGenerator) {
        for (int i = 0; i < INITIAL_CARD_NUMBER; ++i) {
            distributeCardToPlayers(numberGenerator);
            additionalCardToDealer(numberGenerator);
        }
    }

    private void distributeCardToPlayers(NumberGenerator numberGenerator) {
        while (!cycleIsOver()) {
            addtionalCardToPlayer(numberGenerator, turnPlayer(), true);
            updateTurnPlayer();
        }
    }

    public boolean additionalCardToDealer(NumberGenerator numberGenerator) {
        if (dealer.isHit()) {
            dealer.addCard(dealer.handOutCard(numberGenerator));
            return true;
        }
        return false;
    }

    public Map<Participant, Cards> openDealerOneCard() {
        return Map.of(dealer, dealer.pickOpenCardsInInitCards());
    }

    public Map<Participant, Cards> openTurnPlayerInitCards() {
        return Map.of(turnPlayer, turnPlayer.pickOpenCardsInInitCards());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Map<String, Result> results(List<Player> players) {
        return zipToMap(players.stream()
                        .map(Player::getName)
                        .collect(Collectors.toList())
                , players.stream()
                        .map(dealer::isWin)
                        .collect(Collectors.toList()));
    }

    private <K, V> Map<K, V> zipToMap(List<K> keys, List<V> values) {
        return IntStream.range(0, keys.size()).boxed()
                .collect(Collectors.toMap(keys::get, values::get));
    }
}
