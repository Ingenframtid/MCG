package memory_card_game.gameutils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The GameMenu class is responsible for setting up the game menu bar
 * with various options like starting a new game, viewing high scores,
 * changing themes, adjusting difficulty, and controlling sound.
 */
public class GameMenu {

    /**
     * Sets up the game menu bar and adds it to the main game frame.
     * @param mainFrame the main frame of the game
     * @param listener  the action listener for menu item events
     */
    public static void setupGameMenu(JFrame mainFrame, ActionListener listener) {
	JMenuBar menuBar = new JMenuBar();
	mainFrame.setJMenuBar(menuBar);

	JMenu gameMenu = new JMenu("GAME");
	menuBar.add(gameMenu);
	addMenuItem("NEW", gameMenu, listener);
	addMenuItem("HIGHSCORES", gameMenu, listener);
	addMenuItem("RULES", gameMenu, listener);
	addMenuItem("EXIT", gameMenu, listener);


	JMenu themeMenu = new JMenu("THEME");
	menuBar.add(themeMenu);
	addMenuItem("PLACE", themeMenu, listener);
	addMenuItem("FLAG", themeMenu, listener);
	addMenuItem("ANIMAL", themeMenu, listener);
	addMenuItem("ACTIVITY", themeMenu, listener);

	JMenu sizeMenu = new JMenu("DIFFICULTY");
	menuBar.add(sizeMenu);
	addMenuItem("EASY", sizeMenu, listener);
	addMenuItem("MID", sizeMenu, listener);
	addMenuItem("HARD", sizeMenu, listener);


	JMenu soundMenu = new JMenu("SOUND");
	menuBar.add(soundMenu);
	addMenuItem("PLAY", soundMenu, listener);
	addMenuItem("STOP", soundMenu, listener);
    }

    /**
     * Adds a menu item to the specified menu.
     * @param name     the name of the menu item
     * @param menu     the menu to which the item is to be added
     * @param listener the action listener for the menu item
     */
    private static void addMenuItem(String name, JMenu menu, ActionListener listener) {
	JMenuItem newItem = new JMenuItem(name);
	newItem.setActionCommand(name);
	newItem.addActionListener(listener);
	menu.add(newItem);
    }

    /**
     * Creates an "About" panel containing a text area with information about the game.
     * @param mainContentPane the main content pane of the game frame
     * @param mainFrame       the main frame of the game
     */
    public static void aboutGame(Container mainContentPane, JFrame mainFrame) {
	mainContentPane.removeAll();
	JPanel aboutPanel = new JPanel();
	BoxLayout boxLayout = new BoxLayout(aboutPanel, BoxLayout.Y_AXIS);
	aboutPanel.setLayout(boxLayout);
	JTextArea aboutText = new JTextArea("This is a memory card game. You have to match two cards together" +
					    " and when you matched all the cards to their matching cards you win" +
					    " .There is a flip counter at the bottom of the screen which indicates" +
					    " the amount of times you flipped two cards that aren't matching." +
					    " The lesser the number the better! you can at any point start a new game, " +
					    "change theme/difficulty or exit." +
					    " We hope you enjoy our retro game! \n\nCreated by Musse." +
					    "\nwww.ingenframtid.se");
	aboutText.setEditable(false);
	aboutText.setLineWrap(true);
	aboutText.setWrapStyleWord(true);
	aboutText.setAlignmentX(Component.CENTER_ALIGNMENT);
	aboutPanel.add(Box.createVerticalGlue());
	aboutPanel.add(aboutText);
	aboutPanel.add(Box.createVerticalGlue());

	mainContentPane.add(aboutPanel);

	mainFrame.setVisible(true);
    }
}