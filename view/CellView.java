package view;

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

import model.Cell;
import controller.MinesweeperController;

public class CellView extends StackPane {

	// Style variables for StackPane
	private String cellBorderColor = "657b83";
	private String revealedBGColor = "fdf6e3";

	private int cellSize;
	private int neighborMineCount;

	MinesweeperController controller;

	private BorderStroke blankCellBorderStroke = new BorderStroke(Color.web(cellBorderColor),
																  BorderStrokeStyle.SOLID,
																  CornerRadii.EMPTY,
																  BorderWidths.DEFAULT);
	
	private Border blankCellBorder = new Border(blankCellBorderStroke);

	/**
	 * Default constructor with init cell settings
	 * 
	 * @param controller FIXME: describe
	 * @param cellSize   FIXME: describe
	 */
	public CellView(MinesweeperController controller, int cellSize) {
		
		this.controller = controller;
		this.cellSize   = cellSize;
		
		this.neighborMineCount = 0;
		
		this.setPadding(new Insets(2,2,2,2));
		this.setBorder(blankCellBorder);
	}

	/**
	 * Update method that accepts {@code Cell} from passed {@code MinesweeperModel}
	 * @param cell
	 */
	public void updateCellView(Cell cell) {
		
		neighborMineCount  = cell.getNeighbors();
		new Label(String.valueOf(neighborMineCount));
		
		if (cell.isRevealed()) {
			showRevealedState(cell);
		}
	}
	
	/**
	 * TODO: explain/summarize method
	 * 
	 * @param cell FIXME: describe
	 */
	public void showRevealedState(Cell cell) {

		this.getChildren().clear();
		
		Rectangle revealedBG = new Rectangle(cellSize-5,cellSize-5);
		revealedBG.setFill(Color.web(revealedBGColor));
		
		this.getChildren().add(revealedBG);
	}
}