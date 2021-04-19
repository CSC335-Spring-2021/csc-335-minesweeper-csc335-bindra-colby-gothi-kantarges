package view;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.stage.Stage;

import model.MinesweeperBoard;

/**
 * This class represents the view of the game,
 * and is reponsible for interacting with the user
 * and providing them information.
 * 
 * This class implements a GUI to accomplish all of the above.
 * 
 * @author Prabhkirat Singh Bindra
 * @author James Colby
 * @author Denson Selm Gothi
 * @author Joshua Paul Kantarges
 *
 */
@SuppressWarnings("deprecation")
public class MinesweeperView extends Application implements Observer {

	/**
	 * The entry point of the GUI that sets up the {@code Stage} of the GUI
	 * 
	 * @param arg0 The {@code Stage} of the game's GUI
	 */
	@Override
	public void start(Stage arg0) throws Exception {

		// TODO: implement GUI
	}
	
	/**
	 * This method is reponsible for updating the view whenever the model changes.
	 * 
	 * {@code MinesweeperView} is an {@code Observer} of {@code MinesweeperModel} and this method is called
	 * whenever {@code MinesweeperModel} notifies its {@code Observer}s (whenever it changes).
	 * Then, this method copies the new game board and scores provided via {@code Object args}
	 * and changes the GUI to show this new state.
	 * 
	 * @param arg A {@code MinesweeperBoard Object} that carries the information of the game-state updates
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		MinesweeperBoard mb = (MinesweeperBoard) arg;

		// TODO: complete method to update GUI
	}
}