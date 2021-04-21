package view;

import java.util.Observable;
import java.util.Observer;

import controller.MinesweeperController;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import model.MinesweeperBoard;
import model.MinesweeperModel;

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

	//FIXME: Hardcoded global variables for board
	private static final int ROW_SIZE = 8;
	private static final int COL_SIZE = 8;
	
	// Instance of controller, that accepts constructor of model
	// as param (which accepts difficulty setting and 
	private MinesweeperController controller = new MinesweeperController(
												new MinesweeperModel(this));
	
	/**
	 * The entry point of the GUI that sets up the {@code Stage} of the GUI
	 * 
	 * @param stage The {@code Stage} of the game's GUI
	 */
	@Override
	public void start(Stage stage) throws Exception {

		BorderPane window = new BorderPane();
		
		BoardGridView grid = new BoardGridView(ROW_SIZE,
											   COL_SIZE,
											   controller
											   );
		
		window.setCenter(grid);
		
		stage.setTitle("Minesweeper");
		Scene scene = new Scene(window, Color.GREY);
		stage.setScene(scene);
		stage.show();
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