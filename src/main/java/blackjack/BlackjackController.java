package blackjack;

import blackjack.user.Dealer;
import blackjack.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    private void distributeInitCards(Dealer dealer, Players players, Deck deck) {
        dealer.drawAdditionalCard(deck);
        players.drawAdditionalCard(deck);
    }

    private void openInitialCards(Dealer dealer, Players players) {
        OutputView.printInitialDistributionEndMessage(dealer.getName(), players.getNames());
        OutputView.printDealerCards(dealer.getName(), dealer.pickOpenCards());
        for (Object player : players) {
            OutputView.printCards(((Player)player).getName(), ((Player)player).pickOpenCards(), true);
        }
    }

    private void distributeAdditionCardsToPlayer(Player player, Deck deck) {
        while (player.isHit() && InputView.askToGetAdditionCard(player.getName())) {
            player.drawAdditionalCard(deck);
            OutputView.printCards(player.getName(), player.getCards(), true);
        }
        player.setStateStayIfSatisfied(true);
    }

    private void distributeAdditionCardsToDealer(Dealer dealer, Deck deck) {
        while (dealer.isHit()) {
            dealer.drawAdditionalCard(deck);
            OutputView.printDealerAdditionalCardMessage();
        }
    }

    private void distributeAdditionCardsToAllParticipant(Dealer dealer, Players players, Deck deck) {
        for (Object player : players) {
            distributeAdditionCardsToPlayer((Player)player, deck);
        }
        distributeAdditionCardsToDealer(dealer, deck);
    }

    private void openCardsWithScore(Dealer dealer, Players players) {
        OutputView.printCards(dealer.getName(), dealer.getCards(), false);
        OutputView.printScore(dealer.score());
        for (Object obj : players) {
            Player player = (Player) obj;
            OutputView.printCards(player.getName(), player.getCards(), false);
            OutputView.printScore(player.score());
        }
    }

    public void run() {
        Deck deck = Deck.makeRandomShuffledDeck();
        Dealer dealer = Dealer.generate();
        Players players = Players.generateWithNames(InputView.enterPlayerNames());

        distributeInitCards(dealer, players, deck);
        openInitialCards(dealer, players);
        distributeAdditionCardsToAllParticipant(dealer, players, deck);

        openCardsWithScore(dealer, players);
    }
}
