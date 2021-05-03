package view;

import java.io.*;

import java.util.Observable;
import java.util.Observer;

import controller.ReadWrite;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Difficulty;
import model.GameState;
import model.HighScores;
import model.MinesweeperBoard;
import model.MinesweeperModel;
import controller.MinesweeperController;

/**
 * This class represents the view of the game, and is responsible for interacting
 * with the user and providing them information.
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

	private MinesweeperController controller = new MinesweeperController(new MinesweeperModel(this));

	private BoardGridView grid = new BoardGridView(Difficulty.EASY_ROW, Difficulty.EASY_COL, controller);

	private InfoPanelView infoPanel = new InfoPanelView(controller, this);
	
	private ReadWrite rw = new ReadWrite();

	private Stage stage;

	/**
	 * The entry point of the GUI that sets up the {@code Stage} of the GUI
	 * 
	 * @param stage The {@code Stage} of the game's GUI
	 */
	@Override
	public void start(Stage stage) throws Exception {

		this.stage = stage;

		// Initialize game with default board
		controller.initModel(null, Difficulty.EASY, rw.readHighScoreData());

		// Create instance of game view, depending on difficulty
		makeNewView(Difficulty.EASY, controller);

	}

	protected void makeNewView(int difficulty, MinesweeperController controller) {

		BorderPane window = new BorderPane();

		grid = new BoardGridView(controller.calcRows(difficulty), controller.calcCols(difficulty), controller);

		window.setCenter(grid);

		window.setTop(infoPanel);
		window.setCenter(grid);
		stage.setTitle("Minesweeper");
		Scene scene = new Scene(window, Color.GREY);
		stage.setScene(scene);
		stage.show();

		stage.setOnCloseRequest((event) -> {

			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save Game");
			fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));

			// Write high score data
			rw.writeHighScoreData(controller.getHighScores());

			// Attempt to ask user to save game
			try {
				File selectedFile = fileChooser.showSaveDialog(stage);
				rw.writeSaveData(controller.getBoard(), selectedFile.getPath());
			} catch (NullPointerException e) {
				System.out.println("Saving Aborted");
				System.exit(1);
			}
		});
	}

	/**
	 * This method is responsible for updating the view whenever the model changes.
	 * 
	 * {@code MinesweeperView} is an {@code Observer} of {@code MinesweeperModel}
	 * and this method is called whenever {@code MinesweeperModel} notifies its
	 * {@code Observer}s (whenever it changes). Then, this method copies the new
	 * game board and scores provided via {@code Object args} and changes the GUI to
	 * show this new state.
	 * 
	 * @param arg A {@code MinesweeperBoard Object} that carries the information of
	 *            the game-state updates
	 */
	@Override
	public void update(Observable o, Object arg) {
		MinesweeperBoard mb = (MinesweeperBoard) arg;

		
		if (controller.win()) {
			controller.setGameState(GameState.OVER);
			infoPanel.stopTime();
			Alert win = new Alert(Alert.AlertType.INFORMATION);
			win.setTitle("WIN");
			win.setContentText("You didn't blow up!");
			win.setHeaderText("You Win!");
			win.showAndWait();
			scoreInput();
		} else if (controller.lose()) {
			controller.setGameState(GameState.OVER);
			infoPanel.stopTime();
			Alert lose = new Alert(Alert.AlertType.INFORMATION);
			lose.setTitle("FAILURE");
			lose.setContentText("You blew up!");
			lose.setHeaderText("You Lose.");
			lose.showAndWait();
		}
		
		grid.updateCells(mb);
		
		infoPanel.updateFlagsLeftLabel(mb);
		infoPanel.updateTimer(mb);

	}
	
	/**
	 * Outputs a {@code TextInputDialog} and shows the player their score.
	 */
	public void scoreInput() {
		TextInputDialog td = new TextInputDialog("Enter Your Name");
		td.setHeaderText("Your Score is  " + controller.getBoard().getScore());
		td.showAndWait();

		//Add the entry to high scores
		controller.getHighScores().addScore(td.getEditor().getText(),controller.getBoard().getScore());
		infoPanel.showHighScores();
	}
}