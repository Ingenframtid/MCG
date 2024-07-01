package memory_card_game.game_logic;

import memory_card_game.gameutils.BackgroundMusic;
import memory_card_game.gameutils.GameMenu;
import memory_card_game.themes.ActivityCardTheme;
import memory_card_game.themes.AnimalCardTheme;
import memory_card_game.themes.CardTheme;
import memory_card_game.themes.FlagCardTheme;
import memory_card_game.themes.PlaceCardTheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a Matching Card Game application where players match pairs of cards.
 */
public class MatchingCardGame implements ActionListener
{
    private enum GameAction {
	NEW, EXIT, PLACE, FLAG, ANIMAL, ACTIVITY, EASY, MID, HARD, PLAY, STOP, HIGHSCORES,  RULES
    }
    //Declaring instance vairables
    private JFrame mainFrame;
    private Container mainContentPane;

    private CardTheme currentTheme;
    private GameGrid currentGrid;

    private BackgroundMusic music;
    private CardFlipCounter counter;

    private int rows = 3; // default
    private int columns = 4; // default

    private List<HighScore> highScoreList = new ArrayList<>();


    /**
     * Constructor. Sets up the main window of the game, loads card images, initializes
     * background music and the card flip counter, and starts a new game.
     */
    public MatchingCardGame() {
	setupFrame();
	currentTheme = new PlaceCardTheme(mainFrame);
	setUpGameGrid();
	initBackgroundMusic();
	initCardFlipCounter();
	GameMenu.setupGameMenu(mainFrame, this);
	newGame();
    }

    /**
     * Sets up the main window frame of the game.
     */
    private void setupFrame() {
	//Creating a main window
	this.mainFrame = new JFrame("Matching Card Game");
	this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.mainFrame.setSize(700, 800);
	mainFrame.setResizable(false);
	// Center frame to middle of screen
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int x = (screenSize.width - this.mainFrame.getWidth()) / 2;
	int y = (screenSize.height - this.mainFrame.getHeight()) / 2;
	this.mainFrame.setLocation(x, y);
	//Setup the main content pane using container
	this.mainContentPane = this.mainFrame.getContentPane();
	this.mainContentPane.setLayout(new BoxLayout(this.mainContentPane, BoxLayout.PAGE_AXIS));
    }
    /**
     * Initializes the background music for the game.
     */
    private void initBackgroundMusic() {
	this.music = new BackgroundMusic("resources/audio/BackgroundMusic.wav");

    }
    /**
     * Sets up the game grid with the specified number of rows and columns.
     */
    private void setUpGameGrid() {
	currentGrid = new StandardGameGrid(loadCardImageIcons(currentTheme));
    }

    /**
     * Initializes the card flip counter.
     */
    private void initCardFlipCounter() {
	// code to initialize card flip counter
	this.counter = new CardFlipCounter();
    }

    /**
     * Loads the card images from the theme and returns an array of ImageIcon objects.
     * @param theme the theme from which to load card images
     * @return an array of ImageIcon objects representing card images
     */
    private ImageIcon[] loadCardImageIcons(CardTheme theme) {
	return theme.loadCardImageIcons();
    }

    /**
     * Method that creates a JPanel containing a grid of cards
     */
    public JPanel createCards() {
	int totalMatches = (rows * columns) / 2;
	CardController controller = new CardController(counter, totalMatches, this);
	return currentGrid.createGrid(controller, rows, columns);
    }

    /**
     * Method that resets the card flip counter, removes the previous card panel from the content pane, creates a new card panel using the
     * createCards method, adds the new card panel and the card flip counter to the content pane, and displays the main window.
     */
    public void newGame() {
	SwingUtilities.invokeLater(() -> {
	    // reset the card flip counter to 0 again
	    this.counter.reset();
	    // remove the previous panel
	    this.mainContentPane.removeAll();
	    // show new card set
	    JPanel cardsPanel = createCards();
	    if (cardsPanel != null) {
		this.mainContentPane.add(cardsPanel);
	    }
	    // start a new count
	    this.mainContentPane.add(this.counter.getComponent());
	    // validate and repaint the content pane
	    this.mainContentPane.validate();
	    this.mainContentPane.repaint();
	    // show main window
	    this.mainFrame.setVisible(true);
	});
    }

    /**
     Records the current game's high score by creating a HighScore object with the current theme.
     */
    public void updateHighScore() {
	String theme = currentTheme.getThemeName();
	String difficulty = getDifficulty();
	HighScore highScore = new HighScore(theme, counter.getFlipCount(), difficulty);
	highScoreList.add(highScore);
	showHighScores();
    }

    /**
     * Displays the high scores in a pop up window.
     */
    private void showHighScores() {
	// Create a modal JDialog instead of a JFrame
	JDialog highScoreDialog = new JDialog(mainFrame, "High Scores", true);
	highScoreDialog.setSize(400, 300);
	highScoreDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	highScoreDialog.setLocationRelativeTo(mainFrame);

	JTextArea textArea = new JTextArea();
	textArea.setEditable(false);

	StringBuilder highScores = new StringBuilder();
	for (HighScore highScore : highScoreList) {
	    highScores.append(highScore).append("\n");
	}
	textArea.setText(highScores.toString());

	JScrollPane scrollPane = new JScrollPane(textArea);
	scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

	highScoreDialog.getContentPane().add(scrollPane, BorderLayout.CENTER);

	// Show the dialog and force the user to close it before continuing
	highScoreDialog.setVisible(true);
    }

    /**
     * Retrieves difficulty level based on the current grid dimensions.
     * @return the difficulty level as a String.
     */
    private String getDifficulty() {
	final Map<String, String> difficultyMap = Map.of(
		"2x3", "EASY",
		"3x4", "MID",
		"4x4", "HARD"
	);
	return difficultyMap.getOrDefault(rows + "x" + columns, "Not found");
    }

    /**
     * Changes the current theme of the game and refreshes the game UI.
     * @param theme
     */
    public void changeTheme(CardTheme theme) {
	currentTheme = theme;
	loadCardImageIcons(theme);
	setUpGameGrid();
	newGame();
    }

    /**
     * Handles action events triggered by menu items.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
	GameAction action = GameAction.valueOf(e.getActionCommand());
	switch (action) {
	    case NEW:
		newGame();
		break;
	    case PLACE:
		changeTheme(new PlaceCardTheme(mainFrame));
		break;
	    case FLAG:
		changeTheme(new FlagCardTheme(mainFrame));
		break;
	    case ANIMAL:
		changeTheme(new AnimalCardTheme(mainFrame));
		break;
	    case ACTIVITY:
		changeTheme(new ActivityCardTheme(mainFrame));
		break;
	    case EASY:
		rows = 2;
		columns = 3;
		newGame();
		break;
	    case MID:
		rows = 3;
		columns = 4;
		newGame();
		break;
	    case HARD:
		rows = 4;
		columns = 4;
		newGame();
		break;
	    case EXIT:
		System.exit(0);
		break;
	    case PLAY:
		music.start();
		break;
	    case STOP:
		music.stop();
		break;
	    case HIGHSCORES:
	    showHighScores();
	    break;
	    case RULES:
		GameMenu.aboutGame(mainContentPane, mainFrame);
		break;
	}
    }
}