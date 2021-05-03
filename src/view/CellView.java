package view;

import javafx.geometry.Insets;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import model.Cell;

public class CellView extends StackPane {
	
	private ImageView unclicked = new ImageView(CellImages.unclicked);
	
	private ImageView mine = new ImageView(CellImages.mine);
	private ImageView flag = new ImageView(CellImages.flag);

	private ImageView blank = new ImageView(CellImages.blank);
	
	private ImageView one   = new ImageView(CellImages.one);
	private ImageView two   = new ImageView(CellImages.two);
	private ImageView three = new ImageView(CellImages.three);
	private ImageView four  = new ImageView(CellImages.four);
	private ImageView five  = new ImageView(CellImages.five);
	private ImageView six   = new ImageView(CellImages.six);
	private ImageView seven = new ImageView(CellImages.seven);
	private ImageView eight = new ImageView(CellImages.eight);

	/**
	 * Default constructor with init cell settings
	 */
	public CellView() {
		this.setPadding(new Insets(2));
		this.getChildren().add(unclicked);
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

				this.getChildren().add(mine);
				
			} else {
				
				int numMines = cell.getNeighbors();

				ImageView img;
				
				switch (numMines) {
				
					case 1:
						img = one;
					break;
					
					case 2:
						img = two;
					break;
						
					case 3:
						img = three;
					break;
						
					case 4:
						img = four;
					break;
						
					case 5:
						img = five;
					break;
						
					case 6:
						img = six;
					break;
						
					case 7:
						img = seven;
					break;
						
					case 8:
						img = eight;
					break;

					default:
						img = blank;
					break;
				}
				
				this.getChildren().add(img);
			}
			
		} else {
			
			this.getChildren().clear();
			
			// display that this cell is flagged
			if (cell.hasFlag()) {
				
				this.getChildren().add(flag);
				
			} else {
				
				this.getChildren().add(unclicked);
			}
		}
	}
}