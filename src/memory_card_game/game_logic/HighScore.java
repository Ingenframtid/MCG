package memory_card_game.game_logic;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a high score achieved in a matching card game.
 */
public class HighScore {
    private final String theme;
    private final String difficulty;

    /*
    The 'attempts' field is not temporary and is an important part of the HighScore's state, since
    It stores the number of attempts taken to achieve the score, which is needed for displaying high score information.
     */
    private final int attempts;

    private final String dateTime;

    /**
     * Constructs a HighScore object with the specified theme, number of attempts, and difficulty level.
     * @param theme      the theme of the card game for which the high score is recorded
     * @param attempts   the number of attempts taken to achieve the high score
     * @param difficulty the difficulty level at which the high score was achieved
     */
    public HighScore(final String theme, final int attempts, final String difficulty) {
	this.theme = theme;
	this.attempts = attempts;
	this.difficulty = difficulty;
	this.dateTime = new SimpleDateFormat("yy/MM-dd HH:mm").format(new Date());
    }

    /**
     * Returns the theme of the card game for which the high score is recorded.
     * @return the theme of the card game
     */
    public String getTheme() {
	return theme;
    }

    /**
     * Returns the number of attempts taken to achieve the high score.
     * @return the number of attempts
     */
    public int getAttempts() {
	return attempts;
    }

    /**
     * Returns the difficulty level at which the high score was achieved.
     * @return the difficulty level
     */
    public String getDifficulty() {
	return difficulty;
    }

    /**
     * Returns a string representation of the HighScore object.
     * @return a string containing the attempts, difficulty level, theme, and timestamp of the high score
     */
    @Override
    public String toString() {
	return "Attempts: " + attempts +
	       "    Mode: " + difficulty +
	       "    Theme: " + theme +
	       "    Date: " + dateTime;
    }
}
