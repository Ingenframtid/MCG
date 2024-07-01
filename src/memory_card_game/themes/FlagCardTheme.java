package memory_card_game.themes;

import javax.swing.*;

public class FlagCardTheme extends AbstractCardTheme
{
    private static final int NUMBER_OF_CARDS = 9; // Same as num of images

    public FlagCardTheme(JFrame mainFrame) {
	super(mainFrame);
    }

    @Override
    public int getNumberOfCards() {
	return NUMBER_OF_CARDS;
    }

    @Override
    public String getThemeName() {
	return "flag";
    }
}
