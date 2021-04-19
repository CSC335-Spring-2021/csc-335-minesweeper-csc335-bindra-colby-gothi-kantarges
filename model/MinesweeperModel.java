package model;

import java.util.Observable;

/**
 * Model class of the Minesweeper MVC architecture
 * 
 * TODO: brief description
 * 
 * @author Prabhkirat Singh Bindra
 * @author James Colby
 * @author Denson Selm Gothi
 * @author Joshua Paul Kantarges
 *
 */
@SuppressWarnings("deprecation")
public class MinesweeperModel extends Observable {
	
	// TODO: implement model
	
	/**
	 * Sends updated game-state to the view
	 * 
	 * @param mb A {@code MinesweeperBoard} {@code Object} that represents the most up-to-date game-state
	 */
	private void updateView(MinesweeperBoard mb) {
		setChanged();
		notifyObservers(mb);
	}
}