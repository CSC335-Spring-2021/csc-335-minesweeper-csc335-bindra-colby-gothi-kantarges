package controller;

import java.util.Random;

import model.Cell;
import model.MinesweeperBoard;
import model.MinesweeperModel;

/**
 * Controller of MVC architecture.
 * 
 * View communicates user's interactions to this controller,
 * which in-turn calls methods that can change the model.
 * 
 * This controller is responsible for performing all kinds of
 * calculations/computations/checking, and making changes to the board.
 * 
 * @author Prabhkirat Singh Bindra
 * @author James Colby
 * @author Denson Selm Gothi
 * @author Joshua Paul Kantarges
 *
 */
public class MinesweeperController {

	/**
	 * the model that represents the game state internally
	 */
	MinesweeperModel model;
	
	/**
	 * Parameterized Constructor
	 * 
	 * @param model The {@code MinesweeperModel} that this controller is responsible for
	 */
	public MinesweeperController(MinesweeperModel model) {
		this.model = model;
	}
	
	public void initModel(MinesweeperBoard mb) {
		this.model.initialize(mb);
	}

	/**
	 * Game logic for handling click on a cell.
	 * 
	 * @param r row of cell clicked
	 * @param c col of cell clicked
	 */
	public void handleCellLeftClick(int r, int c) {
		
		if (model.getIsFirstClick()) {		// need to generate bombs
			
			Random rand = new Random();

			int numRows = getBoard().getRows();
			int numCols = getBoard().getCols();
			
			int mines = (int) Math.floor(0.15 * numRows * numCols);		// Formula for how many mines to place
			
			while (mines != 0) {
				
				int xIndex = rand.nextInt(numCols);
				int yIndex = rand.nextInt(numRows);
				
				if ((xIndex != r) && (yIndex != c)) {
					
					Cell cell = model.getCell(xIndex, yIndex);
					
					if (!cell.hasMine()) {		// only place mine if cell does not have one already
						cell.addMine();
						mines--;
					}
				}
			}
			
			
			for (int i = 0; i < numRows; i++) {
				
				for (int j = 0; j < numCols; j++) {
					
					model.getCell(i, j).setNeighbors(this.getNeighbors(i, j));
				}
			}
			
			model.changeFirstClick();
		}
		
		model.revealCell(r, c);
	}
	
	/**
	 * Returns an integer specifying the number of mines adjacent to this {@code Cell}
	 * 
	 * @param r row number of the {@code Cell}
	 * @param c col number of the {@code Cell}
	 */
	private int getNeighbors(int r, int c) {
		
		int mineCount = 0;
		
		// check NW neighbor
		if (model.isInBounds(r-1, c-1)) {
			if (model.getCell(r-1, c-1).hasMine()) {
				mineCount++;
			}
		}
		
		// check N neighbor
		if (model.isInBounds(r-1, c)) {
			if (model.getCell(r-1, c).hasMine()) {
				mineCount++;
			}
		}
		
		// check NE neighbor
		if (model.isInBounds(r-1, c+1)) {
			if (model.getCell(r-1, c+1).hasMine()) {
				mineCount++;
			}
		}
		
		// check W neighbor
		if (model.isInBounds(r, c-1)) {
			if (model.getCell(r, c-1).hasMine()) {
				mineCount++;
			}
		}
		
		// check E neighbor
		if (model.isInBounds(r, c+1)) {
			if (model.getCell(r, c+1).hasMine()) {
				mineCount++;
			}
		}
		
		// check SW neighbor
		if (model.isInBounds(r+1, c-1)) {
			if (model.getCell(r+1, c-1).hasMine()) {
				mineCount++;
			}
		}
		
		// check S neighbor
		if (model.isInBounds(r+1, c)) {
			if (model.getCell(r+1, c).hasMine()) {
				mineCount++;
			}
		}
		
		// check SE neighbor
		if (model.isInBounds(r+1, c+1)) {
			if (model.getCell(r+1, c+1).hasMine()) {
				mineCount++;
			}
		}
		
		return mineCount;
	}
	
	/**
	 * Game logic for handling a right click.
	 * 
	 * @param r row of cell clicked
	 * @param c col of cell clicked
	 */
	public void handleCellRightClick(int r, int c) {
		model.toggleFlag(r, c);
	}

	/**
	 * Gets the board from the model
	 * 
	 * @return MinesweeperBoard
	 */
	public MinesweeperBoard getBoard(){
		return model.getBoard();
	}
}