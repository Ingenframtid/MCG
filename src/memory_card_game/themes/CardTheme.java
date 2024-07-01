package memory_card_game.themes;

import javax.swing.*;

public interface CardTheme {

    /**
     * Returns the total number of cards in the theme.
     * @return the number of cards
     */
    int getNumberOfCards();

    /**
     * Returns the image path for the card at the specified index.
     * @param cardIndex the index of the card
     * @return the image path for the card
     */
    String getCardImagePath(int cardIndex);

    /**
     * Loads and returns an array of ImageIcon objects representing the card images for the theme.
     * @return an array of ImageIcon objects
     */
    ImageIcon[] loadCardImageIcons();

    /**
     * Returns the name of the theme.
     * @return the theme name
     */
    String getThemeName();
}
