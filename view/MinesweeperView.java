package view;

import java.io.*;

import java.util.Observable;
import java.util.Observer;

import controller.ReadWrite;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import model.MinesweeperBoard;
import model.MinesweeperModel;
import controller.MinesweeperController;

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
	
	private MinesweeperController controller = new MinesweeperController(new MinesweeperModel(this));
	
	private BoardGridView grid = new BoardGridView(ROW_SIZE, COL_SIZE, controller);

	/**
	 * The entry point of the GUI that sets up the {@code Stage} of the GUI
	 * 
	 * @param stage The {@code Stage} of the game's GUI
	 */
	@Override
	public void start(Stage stage) throws Exception {
		
		BorderPane window = new BorderPane();

		window.setCenter(grid);
		
		InfoPanelView infoPanel = new InfoPanelView(controller, stage);
		
		window.setTop(infoPanel);
		window.setCenter(grid);
		stage.setTitle("Minesweeper");
		Scene scene = new Scene(window, Color.GREY);
		stage.setScene(scene);
		stage.show();

		//Initialize game with default board
		controller.initModel(null);

		stage.setOnCloseRequest((event) -> {
			ReadWrite rw = new ReadWrite();

			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save Game");
			fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));

			try{
				File selectedFile = fileChooser.showSaveDialog(stage);
				rw.writeSaveData(controller.getBoard(),selectedFile.getPath());
			}catch (NullPointerException e){
				System.out.println("Saving Aborted");
				System.exit(1);
			}
		});
	}
	
	/**
	 * This method is responsible for updating the view whenever the model changes.
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
		grid.updateCells(mb);
	}
}