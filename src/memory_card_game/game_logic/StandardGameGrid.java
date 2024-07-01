package memory_card_game.game_logic;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * This class implements the GameGrid interface to create a grid of cards
 * Where the cards are arranged in a grid layout with pairs of cards in
 * random order.
 */
public class StandardGameGrid implements GameGrid {

    private ImageIcon[] cardIcons;
    private final static Random RANDOM = new Random();

    /**
     * Constructs a StandardGameGrid with the specified number of rows, columns, and card icons.
     */
    public StandardGameGrid(ImageIcon[] cardIcons) {

	this.cardIcons = cardIcons;
    }
    /**
     * Creates a JPanel containing a grid of cards.
     * The grid is populated with pairs of cards in random order.
     */
    @Override
    public JPanel createGrid(CardController controller, int rows, int columns) {
	JPanel panel = new JPanel(new GridLayout(rows, columns));
	int[] cardsToAdd = new int[rows * columns];

	// Adjust the logic for adding cards based on rows and columns
	for (int i = 0; i < cardsToAdd.length / 2; i++) {
	    cardsToAdd[2 * i] = i;
	    cardsToAdd[2 * i + 1] = i;
	}

	// Randomize the cards
	randomizeCardArray(cardsToAdd);

	// Create cards based on the randomized order
	for (int num : cardsToAdd) {
	    Card newCard = new Card(controller, cardIcons[num], cardIcons[8], num); // cardIcons[8] is the backIcon
	    panel.add(newCard.getLabel());
	}
	return panel;
    }

    /**
     * Randomizes the order of elements in the provided array.
     */
    private void randomizeCardArray(final int[] b) {
	for (int i = 0; i < b.length; i++) {
	    int c = RANDOM.nextInt(b.length);
	    //swap
	    int d = b[c];
	    b[c] = b[i];
	    b[i] = d;
	}
    }
}