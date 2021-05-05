package view;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class CellSounds {

	static MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("sounds/explosion.wav").toURI().toString()));
}