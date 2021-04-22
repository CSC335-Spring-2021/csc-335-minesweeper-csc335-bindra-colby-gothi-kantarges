package view;

import java.io.*;
import java.util.Observable;
import java.util.Observer;

import controller.MinesweeperController;
import javafx.application.Application;
import javafx.event.EventHandler;
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

import javafx.stage.WindowEvent;
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
	private BoardGridView grid;
	
	// Controller Model and Board class instances
	private MinesweeperController controller;
	private MinesweeperModel model;

	/**
	 * The entry point of the GUI that sets up the {@code Stage} of the GUI    
	 * 
	 * @param stage The {@code Stage} of the game's GUI
	 */
	@Override
	public void start(Stage stage) throws Exception {

		//Potentially read in dave game data
		MinesweeperBoard board = readSaveData();

		if (board == null) { // if its null start a default game
			this.model = new MinesweeperModel(this);
		}else{ // we have a board time to load it in to the model
			this.model = new MinesweeperModel(board,this);
		}
		this.controller = new MinesweeperController(model);

		BorderPane window = new BorderPane();
		
		grid = new BoardGridView(ROW_SIZE,COL_SIZE,controller);

		window.setCenter(grid);
		
		stage.setTitle("Minesweeper");
		Scene scene = new Scene(window, Color.GREY);
		stage.setScene(scene);
		stage.show();

		update(this.model,controller.getBoard()); // updates the GUI after a save game load

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent windowEvent) {
				writeSaveData(controller.getBoard());
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

	/**
	 * Reads in the board save game file if available
	 *
	 * @return the loaded board or null if it could not load it
	 */
	public MinesweeperBoard readSaveData() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save_game.dat"));
			MinesweeperBoard board = (MinesweeperBoard) ois.readObject();
			return board;
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Could not find/Read save_game.dat");
			return null;
		}
	}

	/**
	 * Writes out the board class to a save game file
	 *
	 * @param board
	 * @return True if successfully wtote out the board false if an error occured
	 */
	public boolean writeSaveData(MinesweeperBoard board) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save_game.dat",false));
			oos.writeObject(board);
			oos.close();
			return true;
		} catch (IOException e) {
			System.out.println("Could not write save_game.dat");
			return false;
		}
	}

	//Testing methods not to actually be sued for anything else
	public MinesweeperController getController(){
		return this.controller;
	}

	public MinesweeperModel getModel(){
		return this.model;
	}
}