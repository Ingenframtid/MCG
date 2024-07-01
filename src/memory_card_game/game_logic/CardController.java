package memory_card_game.game_logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The CardController class manages the logic for handling card flips and matches in the Game.
 * It interacts with CardFlipCounter to count flips, uses a Timer for card flip timing, and communicates
 * with the MatchingCardGame to manage game state.
 */
public class CardController implements ActionListener
{
    //Class variables declaration
    private CardFlipCounter counter;
    private List<Card> turnedCards;
    private Timer turnDownTimer = null;
    private int matchesFound = 0;
    private int totalMatches;
    private MatchingCardGame game;

    /**
     * Constructs a CardController with a CardFlipCounter, totalMatches, and MatchingCardGame instance.
     * Initializes turnedCards list and sets up turnDownTimer for card flip timing.
     * @param cardFlipCounter the CardFlipCounter instance to count flips
     * @param totalMatches    the total number of matches needed to end the game
     * @param game            the MatchingCardGame instance for game management
     */
    public CardController(CardFlipCounter cardFlipCounter, int totalMatches, MatchingCardGame game) {
	this.counter = cardFlipCounter;
	this.turnedCards = new ArrayList<>(2);
	this.totalMatches = totalMatches;
	this.game = game;
	setupTimer();
    }

    /**
     * Sets up the turnDownTimer with a delay of 2000 milliseconds and non-repeating behavior.
     */
    public void setupTimer() {
	int turnDownDelay = 2000;
	this.turnDownTimer = new Timer(turnDownDelay, this);
	this.turnDownTimer.setRepeats(false);
    }


    /**
     * Adds a card to the turnedCards list. If two cards are turned, checks for a match.
     * If matched, clears the turnedCards; otherwise, starts the turnDownTimer to flip them back.
     * Checks if all matches are found to end the game.
     * @param card the Card object to add to turnedCards
     * @return true if the card is successfully added, false otherwise
     */
    private boolean addCard(final Card card) {
	// Adds cards to turned cards vector
	this.turnedCards.add(card);

	// If there are already 2 cards in the vector, increase the counter and check if they match
	if (this.turnedCards.size() == 2) {
	    this.counter.addToCount();
	    Card otherCard = this.turnedCards.get(0);
	    if (otherCard.getNum() == card.getNum()) {
		// If the cards match clear the vector
		this.turnedCards.clear();
		matchesFound++;
	    } else {
		// If the cards don't match, start a timer and flip them down after the timer is done
		this.turnDownTimer.start();
	    }
	}
	// Check if all matches are found
	if (matchesFound == totalMatches) {
	    SwingUtilities.invokeLater(() -> game.updateHighScore()); // Ensures Gui update for last card
	}
	return true;
    }

    /**
     * Attempts to turn a card up by adding it to the turnedCards list if less than two cards are already turned.
     * @param card the Card object to turn up
     * @return true if the card is successfully turned up, false if two cards are already turned
     */
    public boolean turnCardUp(Card card) {
	//if there are already 2 cards in the turned cards vector, do nothing
	if (this.turnedCards.size() < 2)
	    //otherwise, add the card to the turned cards vector
	    return addCard(card);
	return false;
    }

    /**
     * method is called when the turnDownTimer fires.
     * It flips down all the cards in the turnedCards vector and then clears the vector.
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
	//turn down all cards in the turnedCards list
	for (Card card : this.turnedCards) {
	    card.turnDown();
	}
	//clear the turnedCards list
	this.turnedCards.clear();
    }
}
