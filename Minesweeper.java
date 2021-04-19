import javafx.application.Application;

import view.MinesweeperView;

/**
 * Holds main method, starting point for launching the GUI and the game program
 * 
 * @author Prabhkirat Singh Bindra
 * @author James Colby
 * @author Dendon Selm Gothi
 * @author Joshua Paul Kantarges
 *
 */
public class Minesweeper {

	public static void main(String[] args) {

		Application.launch(MinesweeperView.class, args);
	}
}