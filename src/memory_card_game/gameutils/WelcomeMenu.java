package memory_card_game.gameutils;

import memory_card_game.game_logic.MatchingCardGame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The WelcomeMenu class represents the welcome screen of the Matching Card Game application.
 * It includes buttons for starting a new game or exiting the application.
 */
public class WelcomeMenu {
    private JFrame mainFrame = null;

    /**
     * Initializes the welcome screen with a background image, a New Game button, and an Exit button.
     */
    public void initialize() {
	mainFrame = new JFrame("Matching Card Game");
	mainFrame.setSize(700, 800);
	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mainFrame.setResizable(false);
	mainFrame.setLocationRelativeTo(null);

	// Load background image
	ImageIcon backgroundImageIcon;
	try {
	    backgroundImageIcon = new ImageIcon(ImageIO.read(new File("resources/images/background/background.jpg")));
	} catch (IOException e) {
	    // If cannot load original image, notify and set a default image
	    e.printStackTrace();
	    backgroundImageIcon = createDefaultBackgroundImage();
	}

	// Create background label with GridBagLayout
	JLabel backgroundLabel = new JLabel(backgroundImageIcon);
	backgroundLabel.setLayout(new GridBagLayout());

	GridBagConstraints gridBagConstraints = new GridBagConstraints();
	gridBagConstraints.insets = new Insets(10, 10, 5, 10);
	gridBagConstraints.ipadx = 50;
	gridBagConstraints.ipady = 40;

	// New Game button
	JButton newGameButton = new JButton("New Game");
	newGameButton.setFont(new Font("Arial", Font.BOLD, 25));
	gridBagConstraints.gridx = 0;
	gridBagConstraints.gridy = 0;
	gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
	backgroundLabel.add(newGameButton, gridBagConstraints);

	// Exit button
	JButton exitButton = new JButton("Exit");
	exitButton.setFont(new Font("Arial", Font.BOLD, 25));
	gridBagConstraints.gridx = 0;
	gridBagConstraints.gridy = 1;
	gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
	backgroundLabel.add(exitButton, gridBagConstraints);

	// ActionListener for New Game button
	newGameButton.addActionListener(e -> startNewGame());

	// ActionListener for Exit button
	exitButton.addActionListener(e -> System.exit(0));

	mainFrame.setContentPane(backgroundLabel);
	mainFrame.setVisible(true);
    }

    /**
     * Starts a new game by disposing of the welcome screen and initializing the MatchingCardGame.
     */
    private void startNewGame() {
	mainFrame.dispose();

	// Start new game
	MatchingCardGame game = new MatchingCardGame();
	game.newGame();
    }

    /**
     * Creates a default background image in case the original background image cannot be loaded.
     * @return an ImageIcon containing the default background image
     */
    private ImageIcon createDefaultBackgroundImage() {
	BufferedImage defaultImage = new BufferedImage(700, 800, BufferedImage.TYPE_INT_RGB);
	Graphics graphics = defaultImage.getGraphics();
	graphics.setColor(Color.GRAY);
	graphics.fillRect(0, 0, 700, 800);
	graphics.dispose();
	return new ImageIcon(defaultImage);
    }
}
