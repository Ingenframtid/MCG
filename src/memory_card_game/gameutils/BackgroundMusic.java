package memory_card_game.gameutils;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

/**
 * The BackgroundMusic class is responsible for loading and playing background music in a loop.
 */
public class BackgroundMusic {
    private Clip bgMusic = null;

    /**
     * Constructor to load music file and loop through out the music
     */
    public BackgroundMusic(String filePath) {
	try {
	    File musicFile = new File(filePath);
	    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
	    bgMusic = AudioSystem.getClip();
	    bgMusic.open(audioInputStream);
	    bgMusic.loop(Clip.LOOP_CONTINUOUSLY);
	} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	    e.printStackTrace();
	    System.err.println("Error loading this file: " + filePath);
	}
    }

    /**
     * Method to start music
     */
    public void start() {
	bgMusic.start();
    }

    /**
     * Method to stop music
     */
    public void stop() {
	bgMusic.stop();
    }
}

