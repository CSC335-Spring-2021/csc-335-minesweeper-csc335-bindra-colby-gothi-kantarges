package view;


import controller.MinesweeperController;
import controller.ReadWrite;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Difficulty;
import model.HighScores;
import model.MinesweeperBoard;

import javax.swing.*;
import javax.swing.text.View;
import java.io.File;
import java.util.List;

/**
 * InfoPanelView is a {@code VBox} that holds the information about the 
 * game, how many flags are left to place, how much time is left, etc. 
 * @author Jim Colby
 *
 */
public class InfoPanelView extends VBox {

	private MinesweeperController controller;
	private Stage stage;

	
	private MenuBar menuBar;
	private Menu fileMenu;
	private MenuItem newGameMenuItem;
	private MenuItem loadGameMenuItem;
	private MenuItem highScoresMenuItem;
	
	private GridPane statsBox;
	private Label timerLabel;
	private Label flagsLeftLabel;
	
	
	public InfoPanelView(MinesweeperController controller, Stage stage) {
		this.controller = controller;
		this.stage = stage;

		menuBar = new MenuBar();
		fileMenu = new Menu("File");
		menuBar.getMenus().add(fileMenu);
		newGameMenuItem = new MenuItem("New Game");
		loadGameMenuItem = new MenuItem("Load Game");
		highScoresMenuItem = new MenuItem("High Scores");

		fileMenu.getItems().addAll(newGameMenuItem,loadGameMenuItem, highScoresMenuItem);
		
		statsBox = new GridPane();
		statsBox.setPadding(new Insets(5,5,5,5));
		
		for (int i = 0; i < 2; i++) {
			ColumnConstraints col = new ColumnConstraints();
			col.setPercentWidth(50);
			col.setHalignment(HPos.CENTER);
			statsBox.getColumnConstraints().add(col);
		}
		
		timerLabel = new Label("000");
		timerLabel.setAlignment(Pos.CENTER_LEFT);
		flagsLeftLabel = new Label("10");
		flagsLeftLabel.setAlignment(Pos.CENTER_RIGHT);

		statsBox.add(timerLabel, 0, 0);
		statsBox.add(flagsLeftLabel,1, 0);
		
		
		newGameMenuItem.setOnAction((event) -> {
			controller.initModel(null, Difficulty.EASY, controller.getHighScores());
		});

		loadGameMenuItem.setOnAction((event) -> {
			ReadWrite rw = new ReadWrite();
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open save game");
			fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));

			try {
				File selectedFile = fileChooser.showOpenDialog(stage);
				MinesweeperBoard board = rw.readSaveData(selectedFile.getPath());
				controller.initModel(board, controller.getDifficulty(), controller.getHighScores());
			}catch (NullPointerException e){
				System.out.println("No file selected, default loaded.");
			}
		});

		highScoresMenuItem.setOnAction((event) -> {

			//TODO remove prompt to enter score should be in game win condition
			TextInputDialog td = new TextInputDialog("Enter Your Name");
			td.setHeaderText("Your Score is  " + controller.getBoard().getScore());
			td.showAndWait();

			//Add the entry to high scores
			controller.getHighScores().addScore(td.getEditor().getText(),controller.getBoard().getScore());

			Alert alert= new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Scores");
			alert.setHeaderText("High Scores!");

			String content = "";
			for(HighScores.ScoreEntry e : controller.getHighScores().getScores()){
				content += e.name + "  " + e.score + "\n";
			}
			alert.setContentText(content);
			alert.showAndWait();
		});
		this.getChildren().add(menuBar);
		this.getChildren().add(statsBox);
		
	}
	
}
