package controller;

import java.util.Random;

import model.Cell;
import model.GameState;
import model.HighScores;
import model.MinesweeperBoard;
import model.MinesweeperModel;

/**
 * Controller of MVC architecture.
 * 
 * View communicates user's interactions to this controller, which in-turn calls
 * methods that can change the model.
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
	 * @param model The {@code MinesweeperModel} that this controller is responsible
	 *              for
	 */
	public MinesweeperController(MinesweeperModel model) {
		this.model = model;
	}

	public void initModel(MinesweeperBoard mb, int difficulty, HighScores highScores) {
		this.model.initialize(mb, difficulty, highScores);
	}

	/**
	 * Game logic for handling click on a cell.
	 * 
	 * @param r row of cell clicked
	 * @param c col of cell clicked
	 */
	public void handleCellLeftClick(int r, int c) {
		
		if (!model.hasFlag(r, c) && model.getGameState() != GameState.OVER) {
			
			if (model.getIsFirstClick()) {									// need to generate bombs
				
				model.setGameState(GameState.START_GAME);
				
				Random rand = new Random();
	
				int numRows = getBoard().getRows();
				int numCols = getBoard().getCols();
	
				int mines = (int) Math.floor(0.15 * numRows * numCols);		// Formula for how many mines to place
	
				while (mines != 0) {
	
					int xIndex = rand.nextInt(numCols);
					int yIndex = rand.nextInt(numRows);
	
					if ((xIndex != r) && (yIndex != c)) {
	
						Cell cell = model.getCell(xIndex, yIndex);
	
						if (!cell.hasMine()) {								// only place mine if cell does not have one already
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
	
			reveal(r, c);
		}
	}
	
	/**
	 * Game logic for handling a right click.
	 * 
	 * @param r row of cell clicked
	 * @param c col of cell clicked
	 */
	public void handleCellRightClick(int r, int c) {
		
		if (model.getGameState() != GameState.OVER) {
			model.toggleFlag(r, c);
		}
	}

	/**
	 * Designed to start the chain of reveals if the given cell is not a mine.
	 * Otherwise, a mine is revealed.
	 * 
	 * @param r row to be revealed
	 * @param c column to be revealed
	 */
	public void reveal(int r, int c) {
		
		if (model.cellHasMine(r, c)) {
			
			model.revealCell(r, c);
			return;
			
		} else {
			multipleClear(r, c);
		}
	}

	/**
	 * Designed to reveal a cell if it does not contain a mine and several others if
	 * the cell has no neighboring mines.
	 * 
	 * @param r row of cell clicked
	 * @param c column of cell clicked
	 */
	private void multipleClear(int r, int c) {
		
		if (model.isInBounds(r, c)) {

			if (model.getCell(r, c).hasMine())					// Don't want to accidentally reveal a mine.
				return;

			if (model.getCell(r, c).isRevealed())				// No need to reveal a cell again
				return;

			if (model.getCell(r, c).getNeighbors() > 0) {		// If the given cell has more than 0 mine neighbors, reveal it and return
				
				model.revealCell(r, c);
				return;

			} else {

				model.revealCell(r, c);

				multipleClear(r - 1, c);			// North
				multipleClear(r + 1, c);			// South
				multipleClear(r, c - 1);			// West
				multipleClear(r, c + 1);			// East
				multipleClear(r - 1, c - 1);		// Northwest
				multipleClear(r + 1, c - 1);		// Southwest
				multipleClear(r + 1, c + 1);		// Southeast
				multipleClear(r - 1, c + 1);		// Northeast
				
				return;
			}
		}
	}

	/**
	 * Returns an integer specifying the number of mines adjacent to this
	 * {@code Cell}
	 * 
	 * @param r row number of the {@code Cell}
	 * @param c col number of the {@code Cell}
	 */
	private int getNeighbors(int r, int c) {

		int mineCount = 0;

		// check NW neighbor
		if (model.isInBounds(r - 1, c - 1)) {
			if (model.getCell(r - 1, c - 1).hasMine()) {
				mineCount++;
			}
		}

		// check N neighbor
		if (model.isInBounds(r - 1, c)) {
			if (model.getCell(r - 1, c).hasMine()) {
				mineCount++;
			}
		}

		// check NE neighbor
		if (model.isInBounds(r - 1, c + 1)) {
			if (model.getCell(r - 1, c + 1).hasMine()) {
				mineCount++;
			}
		}

		// check W neighbor
		if (model.isInBounds(r, c - 1)) {
			if (model.getCell(r, c - 1).hasMine()) {
				mineCount++;
			}
		}

		// check E neighbor
		if (model.isInBounds(r, c + 1)) {
			if (model.getCell(r, c + 1).hasMine()) {
				mineCount++;
			}
		}

		// check SW neighbor
		if (model.isInBounds(r + 1, c - 1)) {
			if (model.getCell(r + 1, c - 1).hasMine()) {
				mineCount++;
			}
		}

		// check S neighbor
		if (model.isInBounds(r + 1, c)) {
			if (model.getCell(r + 1, c).hasMine()) {
				mineCount++;
			}
		}

		// check SE neighbor
		if (model.isInBounds(r + 1, c + 1)) {
			if (model.getCell(r + 1, c + 1).hasMine()) {
				mineCount++;
			}
		}

		return mineCount;
	}

	/**
	 * Gets the board from the model
	 * 
	 * @return MinesweeperBoard
	 */
	public MinesweeperBoard getBoard() {
		return model.getBoard();
	}

	/**
	 * Gets the highscores object from the model
	 * @return Highscores
	 */
	public HighScores getHighScores() {
		return model.getHighScores();
	}

	/**
	 * Gets the difficulty integer
	 * @return
	 */
	public int getDifficulty() {
		return model.getDifficulty();
	}

	public int getRows() {
		return model.getRows();
	}

	public int getCols() {
		return model.getCols();
	}

	public int calcRows(int difficulty) {
		return model.calculateRows(difficulty);
	}

	/**
	 * Takes in a difficulty and calculates the boards columns
	 * @param difficulty
	 * @return int num of columns
	 */
	public int calcCols(int difficulty) {
		return model.calculateCols(difficulty);
	}
	
	/**
	 * Checks if all cells without mines have been revealed.
	 * 
	 * @return {@code true} if all cells without mines have been revealed, {@code false} otherwise.
	 */
	public boolean win() {
		
		int col;
		int row = 0;
		
		while (model.isInBounds(row, 0)) {
			
			col = 0;
			
			while (model.isInBounds(row, col)) {
				
				if (model.cellHasMine(row, col) && model.getCell(row, col).isRevealed()) {
					return false;
				}
				
				if (!model.cellHasMine(row, col) && !model.getCell(row, col).isRevealed()) {
					return false;
				}
				
				col++;
			}
			
			row++;
		}
		
		return true;
	}
	
	/**
	 * Checks if a cell with a mine has been revealed.
	 * 
	 * @return {@code true} if a cell containing a mine was revealed, {@code false} otherwise.
	 */
	public boolean lose() {
		
		int col;
		int row = 0;
		
		while (model.isInBounds(row, 0)) {
			
			col = 0;
			
			while (model.isInBounds(row, col)) {
				
				if (model.cellHasMine(row, col) && model.getCell(row, col).isRevealed()) {
					return true;
				}
				
				col++;
			}
			
			row++;
		}
		
		return false;
	}

	/**
	 * Gets the current time from the model
	 * @return
	 */
	public int getTime() {
		return model.getTime();
	}

	/**
	 * Calls model's increment timer method
	 */
	public void incrementTimer() {
		model.incrementTimer();
	}

	/**
	 * Gets current state of game: unstarted, playing, or over
	 * @return int representation of game state
	 */
	public int getGameState() {
		return model.getGameState();
	}

	/**
	 * Changes play state of model
	 * @param gameState int representation of state to change to
	 */
	public void setGameState(int gameState) {
		model.setGameState(gameState);
	}
}