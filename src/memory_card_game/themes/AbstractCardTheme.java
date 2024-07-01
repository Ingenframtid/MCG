package memory_card_game.themes;

import javax.swing.*;
import java.awt.*;

/**
 * THis is an abstract class that implements the CardTheme interface
 * and provides common functionality for loading card image icons based on a theme.
 */
public abstract class AbstractCardTheme implements CardTheme {
    protected JFrame mainFrame;

    /**
     * Constructs an AbstractCardTheme instance with the specified main JFrame.
     * @param mainFrame The main JFrame used for displaying error messages.
     */
    protected AbstractCardTheme(JFrame mainFrame) {
	this.mainFrame = mainFrame;
    }

    /**
     * Returns the path to the card image for the specified card index.
     * @param cardIndex The index of the card for which to retrieve the image path.
     * @return A String representing the path to the card image.
     */
    @Override
    public String getCardImagePath(int cardIndex) {
	return "resources/images/" + getThemeName() + "/card" + cardIndex + ".jpg";
    }

    /**
     * Loads the card image icons for the theme.
     * @return An array of ImageIcon objects representing the card images.
     */
    public ImageIcon[] loadCardImageIcons() {
	ImageIcon[] icons = new ImageIcon[getNumberOfCards()];
	for (int i = 0; i < icons.length; i++) {
	    String fileName = getCardImagePath(i);
	    icons[i] = new ImageIcon(fileName);
	    if (icons[i].getImageLoadStatus() == MediaTracker.ERRORED) {
		JOptionPane.showMessageDialog(mainFrame, "Error: The images from " + fileName + " couldn't be loaded. " +
							 "Please make sure the images location is correct.",
					      "Error", JOptionPane.ERROR_MESSAGE);
		System.exit(1);
	    }
	}
	return icons;
    }
}