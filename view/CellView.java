package view;



import controller.MinesweeperController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Cell;
import model.CellState;

public class CellView extends StackPane {

	// Style variables for StackPane
	private String cellBorderColor = "657b83";
	private String revealedBGColor = "fdf6e3";
	
	private int cellSize;
	private boolean mined;
	private int neighborMineCount;
	private CellState state;
	private Label neighborCountLabel;
	MinesweeperController controller;

	
	private BorderStroke blankCellBorderStroke = new BorderStroke(
											Color.web(cellBorderColor),
											BorderStrokeStyle.SOLID,
											CornerRadii.EMPTY,
											BorderWidths.DEFAULT);
	
	private Border blankCellBorder = new Border(blankCellBorderStroke);
	

	
	// Default constructor with init cell settings
	public CellView(MinesweeperController controller, int cellSize) {
		this.cellSize = cellSize;
		this.controller = controller;
		this.mined = false;
		this.neighborMineCount = 0;
		this.state = CellState.COVERED;
		this.setPadding(new Insets(2,2,2,2));
		this.setBorder(blankCellBorder);
	}
	
	// Update that method that accepts Cell from passed Minesweeper model
	public void updateCellView(Cell cell) {
		neighborMineCount = cell.getNeighbors();
		neighborCountLabel = new Label(String.valueOf(neighborMineCount));
		if (cell.isRevealed()) {
			showRevealedState(cell);
		}
	}
	
	public void showRevealedState(Cell cell) {

		this.getChildren().clear();
		Rectangle revealedBG = new Rectangle(cellSize-5,cellSize-5);
		revealedBG.setFill(Color.web(revealedBGColor));
		this.getChildren().add(revealedBG);
	}
	
}
