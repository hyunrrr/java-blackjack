package blackjack.controller;

import blackjack.domain.Blackjack;
import blackjack.domain.Person;
import blackjack.domain.Player;
import blackjack.domain.RandomNumberGenerator;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private void controlAdditionalCardForPlayer(Blackjack blackjack, Person person) {
        while (InputView.askAdditionalCard(person)) {
            blackjack.distributeAdditionalCardPlayer(new RandomNumberGenerator(), person);
            OutputView.printCards(person);
        }
    }

    public void run() {
        List<String> playerNames = InputView.getPlayerNames();
        Blackjack blackjack = new Blackjack(playerNames);
        blackjack.distributeInitialCards(new RandomNumberGenerator());

        OutputView.printInitStatus(blackjack.getDealer(), blackjack.getPlayers());

        List<Player> people = blackjack.getPlayers();
        for (Player person : people) {
            controlAdditionalCardForPlayer(blackjack, person);
        }

        blackjack.distributeAdditionalCardDealer(new RandomNumberGenerator());
        OutputView.printDealerAdditionalCard();

        OutputView.printCardsAndResult(blackjack.getDealer(), blackjack.getPlayers());
        OutputView.printResult(blackjack.result());
    }
}