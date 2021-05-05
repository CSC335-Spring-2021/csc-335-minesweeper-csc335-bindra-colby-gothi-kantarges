package view;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Reference to sound file locations
 */
public class CellSounds {

	static MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("sounds/explosion.wav").toURI().toString()));
}