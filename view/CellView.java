package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import model.Cell;

public class CellView extends StackPane {

	// Style variables for StackPane
	private String cellBorderColor = "657b83";
	private String revealedBGColor = "fdf6e3";

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
	public CellView() {
		
		this.setPadding(new Insets(2));
		this.setBorder(blankCellBorder);
	}

	/**
	 * Update method that accepts {@code Cell} from passed {@code MinesweeperModel}
	 * 
	 * @param cell
	 */
	public void updateCellView(Cell cell) {
		
		if (cell.isRevealed()) {
			
			this.getChildren().clear();
			
			StackPane nestedCell = new StackPane();
			
			nestedCell.setPadding(new Insets(5));
			nestedCell.setBackground(new Background(new BackgroundFill(Color.web(revealedBGColor), CornerRadii.EMPTY, Insets.EMPTY)));
			
			nestedCell.getChildren().add(new Label(String.valueOf(cell.getNeighbors())));		// display number of neighbors
			
			this.getChildren().add(nestedCell);
			
		} else {
			
			this.getChildren().clear();
			
			// display that this cell is flagged
			if (!cell.hasFlag()) {
				
				Label cellLabel = new Label("F");
				cellLabel.setTextFill(Color.RED);
				
				this.getChildren().add(cellLabel);
			}
		}
	}
}