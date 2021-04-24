package view;


import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * InfoPanelView is a {@code VBox} that holds the information about the 
 * game, how many flags are left to place, how much time is left, etc. 
 * @author Jim Colby
 *
 */
public class InfoPanelView extends VBox {
	
	private MenuBar menuBar;
	private Menu fileMenu;
	private MenuItem newGameMenuItem;
	private MenuItem highScoresMenuItem;
	
	private GridPane statsBox;
	private Label timerLabel;
	private Label flagsLeftLabel;
	
	
	public InfoPanelView() {

		
		menuBar = new MenuBar();
		fileMenu = new Menu("File");
		menuBar.getMenus().add(fileMenu);
		newGameMenuItem = new MenuItem("New Game");
		highScoresMenuItem = new MenuItem("High Scores");
		fileMenu.getItems().addAll(newGameMenuItem, highScoresMenuItem);
		
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
			//TODO: Implement new game creation code
			System.out.println("Dummmy new game code!");
		});
		
		highScoresMenuItem.setOnAction((event) -> {
			//TODO: Show high scores menu item code
			System.out.println("Dummy high scores code");
		});
		
		this.getChildren().add(menuBar);
		this.getChildren().add(statsBox);
		
	}
	
}
