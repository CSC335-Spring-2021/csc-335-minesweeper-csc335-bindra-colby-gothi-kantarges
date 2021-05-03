package view;


import controller.MinesweeperController;
import controller.ReadWrite;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import model.Difficulty;
import model.GameState;
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
	private MenuItem easyGameMenuItem;
	private MenuItem mediumGameMenuItem;
	private MenuItem expertGameMenuItem;
	private MenuItem loadGameMenuItem;
	private MenuItem highScoresMenuItem;
	private Timeline timeline;

	
	private GridPane statsBox;
	private Label timerLabel = new Label(String.valueOf(0));
	private Label flagsLeftLabel;
	
	
	public InfoPanelView(MinesweeperController controller, MinesweeperView view) {
		this.controller = controller;

		
		menuBar = new MenuBar();
		fileMenu = new Menu("File");
		menuBar.getMenus().add(fileMenu);
		easyGameMenuItem = new MenuItem("Easy");
		mediumGameMenuItem = new MenuItem("Medium");
		expertGameMenuItem = new MenuItem("Expert");
		loadGameMenuItem = new MenuItem("Load Game");
		highScoresMenuItem = new MenuItem("High Scores");
		timeline = new Timeline(new KeyFrame(
								Duration.seconds(1),
								ae -> incrementTimer()));
		
		
		timeline.setCycleCount(Animation.INDEFINITE);
		
		fileMenu.getItems().addAll(easyGameMenuItem,
									mediumGameMenuItem,
									expertGameMenuItem,
									loadGameMenuItem,
									highScoresMenuItem);
		
		statsBox = new GridPane();
		statsBox.setPadding(new Insets(5,5,5,5));
		
		for (int i = 0; i < 2; i++) {
			ColumnConstraints col = new ColumnConstraints();
			col.setPercentWidth(50);
			col.setHalignment(HPos.CENTER);
			statsBox.getColumnConstraints().add(col);
		}
		
//		timerLabel = new Label(String.valueOf(controller.getTime()));
		timerLabel.setAlignment(Pos.CENTER_LEFT);
		flagsLeftLabel = new Label("10");
		flagsLeftLabel.setAlignment(Pos.CENTER_RIGHT);

		statsBox.add(timerLabel, 0, 0);
		statsBox.add(flagsLeftLabel,1, 0);
		
		
		easyGameMenuItem.setOnAction((event) -> {
			view.makeNewView(Difficulty.EASY, controller);
			controller.initModel(null, Difficulty.EASY, controller.getHighScores());
		});
		mediumGameMenuItem.setOnAction((event) -> {
			view.makeNewView(Difficulty.MEDIUM, controller);
			controller.initModel(null, Difficulty.MEDIUM, controller.getHighScores());

		});
		expertGameMenuItem.setOnAction((event) -> {
			view.makeNewView(Difficulty.EXPERT, controller);
			controller.initModel(null, Difficulty.EXPERT, controller.getHighScores());
		});

		loadGameMenuItem.setOnAction((event) -> {
			ReadWrite rw = new ReadWrite();
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open save game");
			fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));

			try {
				File selectedFile = fileChooser.showOpenDialog(stage);
				MinesweeperBoard board = rw.readSaveData(selectedFile.getPath());
				view.makeNewView(board.getDifficulty(), controller);
				controller.initModel(board, controller.getDifficulty(), controller.getHighScores());
			}catch (NullPointerException e){
				System.out.println("No file selected, default loaded.");
			}
		});

		highScoresMenuItem.setOnAction((event) -> {
			showHighScores();
		});
		
		this.getChildren().add(menuBar);
		this.getChildren().add(statsBox);
		
	}
	
	private void startTimer(Timeline tl) {
		tl.play();
	}
	
	public void stopTime() {
		timeline.stop();
	}
	
	private void incrementTimer() {
		controller.incrementTimer();
	}
	
	protected void updateFlagsLeftLabel(MinesweeperBoard mb) {
		flagsLeftLabel.setText(String.valueOf(mb.getFlagsLeft()));
	}
	
	protected void updateTimer(MinesweeperBoard mb) {
		
		if (mb.getGameState() == GameState.START_GAME) {
			startTimer(timeline);
			controller.setGameState(GameState.PLAYING);
		}
		
		if (mb.getGameState() == GameState.PLAYING) {
			timerLabel.setText(String.valueOf(mb.getTime()));
		}
		
		if (mb.getGameState() == GameState.OVER) {
			stopTime();
		}
	}

	protected void showHighScores(){
		Alert alert= new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Scores");
		alert.setHeaderText("High Scores!");

		String content = "";
		for(HighScores.ScoreEntry e : controller.getHighScores().getScores()){
			content += e.name + "  " + e.score + "\n";
		}
		
		alert.setContentText(content);
		alert.showAndWait();
	}
}
