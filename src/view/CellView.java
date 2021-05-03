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

			this.setBackground(new Background(new BackgroundFill(Color.web(revealedBGColor), CornerRadii.EMPTY, Insets.EMPTY)));
			
			Label cellLabel = new Label();
			
			if (cell.hasMine()) {
				
				cellLabel = new Label("M");
				cellLabel.setTextFill(Color.DARKBLUE);
				
			} else {
				
				int numMines = cell.getNeighbors();
				
				if (numMines != 0) {
					cellLabel = new Label(String.valueOf(numMines));
				}
			}
			
			this.getChildren().add(cellLabel);
			
		} else {
			
			this.getChildren().clear();

			// Reset the color of the CellView if the cell is no longer revealed
			if (!cell.isRevealed()){
				this.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
			}
			
			// display that this cell is flagged
			if (cell.hasFlag()) {
				
				Label cellLabel = new Label("F");
				cellLabel.setTextFill(Color.RED);
				
				this.getChildren().add(cellLabel);
			}
		}
	}
}