package view;

import javafx.geometry.Insets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import model.Cell;

public class CellView extends StackPane {

	/**
	 * Default constructor with init cell settings
	 *
	 */
	public CellView() {
		this.setPadding(new Insets(2));
		this.getChildren().add(new ImageView(new Image("file:images/unclicked.png", 26, 26, true, true, false)));
	}

	/**
	 * Update method that accepts {@code Cell} from passed {@code MinesweeperModel}
	 * 
	 * @param cell
	 */
	public void updateCellView(Cell cell) {
		
		if (cell.isRevealed()) {
			
			this.getChildren().clear();

			if (cell.hasMine()) {

				this.getChildren().add(new ImageView(new Image("file:images/mine.png", 26, 26, true, true, false)));
				
			} else {
				
				int numMines = cell.getNeighbors();

				this.getChildren().add(new ImageView(new Image("file:images/" + numMines + ".png", 26, 26, true, true, false)));
			}
			
		} else {
			
			this.getChildren().clear();
			
			// display that this cell is flagged
			if (cell.hasFlag()) {
				
				this.getChildren().add(new ImageView(new Image("file:images/flag.png", 26, 26, true, true, false)));
				
			} else {
				
				this.getChildren().add(new ImageView(new Image("file:images/unclicked.png", 26, 26, true, true, false)));
			}
		}
	}
}