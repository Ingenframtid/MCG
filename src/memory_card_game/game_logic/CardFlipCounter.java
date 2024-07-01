package memory_card_game.game_logic;

import javax.swing.*;
import java.awt.*;

/**
 * The CardFlipCounter class is responsible for counting the number of card flips
 * in the Matching Card Game and displaying the count on a JLabel.
 */
public class CardFlipCounter
{
    private int flipCount = 0;
    private JLabel label;

    /**
     * Constructor that calls the reset() method to initialize the flip count to 0
     * then updates the text of the JLabel.
     */
    public CardFlipCounter() {
	flipCount++;
	label = new JLabel("" + flipCount);
    }

    /**
     * Updates the text of the JLabel to reflect the current flip count.
     */
    private void update() {
	label.setText("" + flipCount);
    }

    /**
     * Returns the current flip count.
     * @return the current flip count
     */
    public int getFlipCount() {
	return flipCount;
    }

    /**
     * Method to increase the flipcount and update the text shown of "JLabel"
     */
    public void addToCount(){
	flipCount++;
	update();
    }

    /**
     * Method to reset the counter and update the text shown of "JLabel"
     */
    public void reset() {
	this.flipCount = 0;
	update();
    }

    /**
     * Returns the JLabel component displaying the flip count.
     * @return the JLabel component
     */
    public Component getComponent() {
	return label;
    }
}
