package testing;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Observer;
import java.util.Random;
import java.util.Observable;

import model.Cell;
import model.GameState;
import model.Difficulty;
import model.HighScores;
import model.HighScores.ScoreEntry;
import model.MinesweeperBoard;
import model.MinesweeperModel;

import controller.MinesweeperController;

@SuppressWarnings("deprecation")
public class MinesweeperTest {
	
	/**
	 * Test method for {@link MinesweeperModel}
	 */
	@Test
	public void testModel() {

		MinesweeperModel model = new MinesweeperModel(new Observer() {
			@Override
			public void update(Observable o, Object arg) {  }
		});
		
		// Test 1 ----------------------------------------------------------------//

			model.initialize(null,Difficulty.EASY,null);
			
			// Check each cell in game to assert it is unflagged, unrevealed, unmined
			// Then use setters to change their states, and verify it worked
			
			for (int i = 0; i < Difficulty.EASY_ROW ; i++) {
				for (int j = 0; j < Difficulty.EASY_COL; j++) {
					
					assertEquals(false, model.getCell(i, j).hasFlag());
					assertEquals(false, model.getCell(i, j).hasMine());
					assertEquals(0,     model.getCell(i, j).getNeighbors());
					
					model.getCell(i, j).flag();
					model.getCell(i, j).addMine();
					model.getCell(i, j).setNeighbors(8);
					
					assertEquals(true, model.getCell(i, j).hasFlag());
					assertEquals(true, model.getCell(i, j).hasMine());
					assertEquals(8,    model.getCell(i, j).getNeighbors());
				}
			}
			
			model.setGameState(GameState.UNSTARTED);
			assertEquals(GameState.UNSTARTED, model.getGameState());
			assertEquals(GameState.UNSTARTED, model.getBoard().getGameState());
			
			assertEquals(Difficulty.EASY, model.getDifficulty());
			
			assertEquals(0, model.getTime());
			model.incrementTimer();
			assertEquals(1, model.getTime());
			
			assertEquals(true, model.getIsFirstClick());
			model.changeFirstClick();
			assertEquals(false, model.getIsFirstClick());
			
			assertEquals(true, model.isInBounds(Difficulty.EASY_ROW - 1, Difficulty.EASY_COL - 1));
			assertEquals(false, model.isInBounds(Difficulty.EASY_ROW, Difficulty.EASY_COL));
			
			assertEquals(false, model.isInBounds(-1, -1));
			
			// Check number of rows, cols, 
			assertEquals(Difficulty.EASY_ROW, model.getRows());
			assertEquals(Difficulty.EASY_COL, model.getCols());
			
			// make sure calculate methods return correct numbers
			assertEquals(Difficulty.EASY_ROW,   model.calculateRows(Difficulty.EASY));
			assertEquals(Difficulty.EASY_COL,   model.calculateCols(Difficulty.EASY));
			assertEquals(Difficulty.MEDIUM_ROW, model.calculateRows(Difficulty.MEDIUM));
			assertEquals(Difficulty.MEDIUM_COL, model.calculateCols(Difficulty.MEDIUM));
			assertEquals(Difficulty.EXPERT_ROW, model.calculateRows(Difficulty.EXPERT));
			assertEquals(Difficulty.EXPERT_COL, model.calculateCols(Difficulty.EXPERT));

		//------------------------------------------------------------------------//

		// Test 2 ----------------------------------------------------------------//
		
			// make a grid with randomly placed mines and flags, or revealing cells
			Cell[][] testGrid = new Cell[Difficulty.MEDIUM_ROW][Difficulty.MEDIUM_COL];
			
			Random random = new Random();
			
			for (int i = 0; i < Difficulty.MEDIUM_ROW; i++) {
				for (int j = 0; j < Difficulty.MEDIUM_COL; j++) {
					
					testGrid[i][j] = new Cell();
					
					int rand = random.nextInt(4);
			
					switch(rand) {
					
						case 0:
							testGrid[i][j].addMine();
						break;
							
						case 1:
							testGrid[i][j].flag();
						break;
							
						case 2:
							if (!testGrid[i][j].hasMine() && !testGrid[i][j].hasFlag())
								testGrid[i][j].reveal();
						break;
							
						default:
							// no-op
						break;
					}
				}	
			}
			
			// make a new minesweeper board with this randomized grid
			MinesweeperBoard testBoard = new MinesweeperBoard(testGrid, 10, 10, 10, 
															  Difficulty.MEDIUM_ROW,
															  Difficulty.MEDIUM_COL,
															  Difficulty.MEDIUM,
															  false, GameState.UNSTARTED);
	
			// make minesweeper model with these properties
			model.initialize(testBoard, Difficulty.MEDIUM, model.getHighScores());
			
			// randomly call reveal cell and toggle flag on the board
			for (int i = 0; i < Difficulty.MEDIUM_ROW; i++) {
				for (int j = 0; j < Difficulty.MEDIUM_COL; j++) {
					
					int rand = random.nextInt(4);
					
					switch(rand) {
					
						case 0:
							if (!model.hasFlag(i, j) && !testGrid[i][j].hasFlag()) {
								testGrid[i][j].reveal();
								model.revealCell(i, j);
							}
						break;
							
						case 1:
							if (!model.getCell(i, j).isRevealed() && !testGrid[i][j].isRevealed()) {
								model.toggleFlag(i,j);
								testGrid[i][j].flag();
							}
						break;
							
						case 2:
							if (!model.cellHasMine(i,j) && !model.hasFlag(i,j)) {
								model.revealCell(i, j);
								testGrid[i][j].reveal();
							}
						break;
							
						default:
							// no-op
						break;
					}
				}	
			}
			
			// now run through model and testGrid, and see if cells match up
			
			for (int i = 0; i < Difficulty.MEDIUM_ROW; i++) {
				for (int j = 0; j < Difficulty.MEDIUM_COL; j++) {
					
					assertEquals(model.getCell(i,j).isRevealed(), testGrid[i][j].isRevealed());
					assertEquals(model.getCell(i,j).hasFlag(), testGrid[i][j].hasFlag());
					assertEquals(model.cellHasMine(i, j), testGrid[i][j].hasMine());
				}
			}

		//------------------------------------------------------------------------//
			
		// Test 3 ----------------------------------------------------------------//
		
			model.initialize(null, Difficulty.EXPERT, null);
			
			// Check each cell in game to assert it is unflagged, unrevealed, unmined
			// Then use setters to change their states, and verify it worked
			
			for (int i = 0; i < Difficulty.EXPERT_ROW ; i++) {
				for (int j = 0; j < Difficulty.EXPERT_COL; j++) {
					
					assertEquals(false, model.getCell(i, j).hasFlag());
					assertEquals(false, model.getCell(i, j).hasMine());
					assertEquals(0,     model.getCell(i, j).getNeighbors());
					
					model.getCell(i, j).flag();
					model.getCell(i, j).addMine();
					model.getCell(i, j).setNeighbors(8);
					
					assertEquals(true, model.getCell(i, j).hasFlag());
					assertEquals(true, model.getCell(i, j).hasMine());
					assertEquals(8,    model.getCell(i, j).getNeighbors());
				}
			}
		
		//------------------------------------------------------------------------//
	}
	
	/**
	 * Test method for {@link MinesweeperController}
	 */
	@Test
	public void testController() {
		
		MinesweeperModel model = new MinesweeperModel(new Observer() {
			@Override
			public void update(Observable o, Object arg) {  }
		});
		
		MinesweeperController controller = new MinesweeperController(model);

		// Test 1 ----------------------------------------------------------------//
		
			controller.initModel(null, Difficulty.EASY, null);
			
			// Check each cell in game to assert it is unflagged, unrevealed, unmined
			// Then use setters to change their states, and verify it worked
			
			for (int i = 0; i < Difficulty.EASY_ROW ; i++) {
				for (int j = 0; j < Difficulty.EASY_COL; j++) {
					
					assertEquals(false, model.getCell(i, j).hasFlag());
					assertEquals(false, model.getCell(i, j).hasMine());
					assertEquals(0,     model.getCell(i, j).getNeighbors());
					
					model.getCell(i, j).flag();
					model.getCell(i, j).addMine();
					model.getCell(i, j).setNeighbors(8);
					
					assertEquals(true, model.getCell(i, j).hasFlag());
					assertEquals(true, model.getCell(i, j).hasMine());
					assertEquals(8,    model.getCell(i, j).getNeighbors());
				}
			}

		//------------------------------------------------------------------------//
		
		// Test 2 ----------------------------------------------------------------//
		
			controller.initModel(null, Difficulty.MEDIUM, null);
			
			// Check each cell in game to assert it is unflagged, unrevealed, unmined
			// Then use setters to change their states, and verify it worked
			
			for (int i = 0; i < Difficulty.MEDIUM_ROW ; i++) {
				for (int j = 0; j < Difficulty.MEDIUM_COL; j++) {
					
					assertEquals(false, model.getCell(i, j).hasFlag());
					assertEquals(false, model.getCell(i, j).hasMine());
					assertEquals(0,     model.getCell(i, j).getNeighbors());
					
					model.getCell(i, j).flag();
					model.getCell(i, j).addMine();
					model.getCell(i, j).setNeighbors(8);
					
					assertEquals(true, model.getCell(i, j).hasFlag());
					assertEquals(true, model.getCell(i, j).hasMine());
					assertEquals(8,    model.getCell(i, j).getNeighbors());
				}
			}

		//------------------------------------------------------------------------//
			
		// Test 3 ----------------------------------------------------------------//
		
			controller.initModel(null, Difficulty.EXPERT, null);
			
			// Check each cell in game to assert it is unflagged, unrevealed, unmined
			// Then use setters to change their states, and verify it worked
			
			for (int i = 0; i < Difficulty.EXPERT_ROW ; i++) {
				for (int j = 0; j < Difficulty.EXPERT_COL; j++) {
					
					assertEquals(false, model.getCell(i, j).hasFlag());
					assertEquals(false, model.getCell(i, j).hasMine());
					assertEquals(0,     model.getCell(i, j).getNeighbors());
					
					model.getCell(i, j).flag();
					model.getCell(i, j).addMine();
					model.getCell(i, j).setNeighbors(8);
					
					assertEquals(true, model.getCell(i, j).hasFlag());
					assertEquals(true, model.getCell(i, j).hasMine());
					assertEquals(8,    model.getCell(i, j).getNeighbors());
				}
			}

		//------------------------------------------------------------------------//
		
		// Test 4 ----------------------------------------------------------------//
		
			controller.initModel(null, Difficulty.EASY, null);
			
			// first click
			controller.handleCellLeftClick(0, 0);
			assertFalse(model.getCell(0, 0).hasMine());
			
			// try to click again
			controller.handleCellLeftClick(0, 0);
			
			for (int i = 0; i < Difficulty.EASY_ROW ; i++) {
				for (int j = 0; j < Difficulty.EASY_COL; j++) {
					
					// click on a mine
					if (model.getCell(i, j).hasMine()) {
						controller.handleCellLeftClick(i, j);
						assertTrue(model.getCell(i, j).isRevealed());
						break;
					}
				}
			}
			
			controller.setGameState(GameState.OVER);
			
			// try to click on unrevealed cells after the game is over
			for (int i = 0; i < Difficulty.EASY_ROW ; i++) {
				for (int j = 0; j < Difficulty.EASY_COL; j++) {

					Cell cell = model.getCell(i, j);
					
					// click on a mine
					if (!cell.isRevealed()) {
						
						boolean revealed  = cell.isRevealed();
						boolean flagged   = cell.hasFlag();
						boolean mined     = cell.hasMine();
						
						int neighborMines = cell.getNeighbors();
						
						controller.handleCellLeftClick(i, j);
						
						assertEquals(neighborMines, cell.getNeighbors());
						assertEquals(revealed,      cell.isRevealed());
						assertEquals(flagged,       cell.hasFlag());
						assertEquals(mined,         cell.hasMine());
						
						controller.handleCellRightClick(i, j);
						
						assertEquals(neighborMines, cell.getNeighbors());
						assertEquals(revealed,      cell.isRevealed());
						assertEquals(flagged,       cell.hasFlag());
						assertEquals(mined,         cell.hasMine());
					}
				}
			}
		
			controller.initModel(null, Difficulty.EASY, null);
			
			// place flag
			controller.handleCellRightClick(0, 0);
			assertTrue(model.getCell(0, 0).hasFlag());
			
			// try to click flagged cell
			controller.handleCellLeftClick(0, 0);
			assertTrue(model.getCell(0, 0).hasFlag());
			assertFalse(model.getCell(0, 0).isRevealed());
			
			// remove flag
			controller.handleCellRightClick(0, 0);
			assertFalse(model.getCell(0, 0).hasFlag());

		//------------------------------------------------------------------------//
		
		// Test 5 ----------------------------------------------------------------//
		
			controller.initModel(null, Difficulty.EASY, null);
			
			assertFalse(model.getCell(0, 0).isRevealed());
			
			// first click
			controller.handleCellLeftClick(0, 0);
			assertTrue(model.getCell(0, 0).isRevealed());
			
			// reveal all cells
			for (int i = 0; i < Difficulty.EASY_ROW ; i++) {
				for (int j = 0; j < Difficulty.EASY_COL; j++) {
					
					// click on a mine
					if (!model.getCell(i, j).isRevealed()) {
						
						controller.reveal(i, j);
					}
				}
			}
			
			// check if all cells were revealed
			for (int i = 0; i < Difficulty.EASY_ROW ; i++) {
				for (int j = 0; j < Difficulty.EASY_COL; j++) {

					assertTrue(model.getCell(i, j).isRevealed());
				}
			}

		//------------------------------------------------------------------------//
			
		// Test 6 ----------------------------------------------------------------//
		
			controller.initModel(null, Difficulty.EASY, null);
			
			// first click
			controller.handleCellLeftClick(0, 0);
			
			MinesweeperBoard modelBoard      = model.getBoard();
			MinesweeperBoard controllerBoard = controller.getBoard();

			assertEquals(modelBoard.isFirstClick(), controllerBoard.isFirstClick());
			
			assertEquals(modelBoard.getRows(), controllerBoard.getRows());
			assertEquals(modelBoard.getCols(), controllerBoard.getCols());

			assertEquals(modelBoard.getFlagsLeft(),  controllerBoard.getFlagsLeft());
			assertEquals(modelBoard.getGameState(),  controllerBoard.getGameState());
			assertEquals(modelBoard.getDifficulty(), controllerBoard.getDifficulty());

			assertEquals(modelBoard.getTime(),  controllerBoard.getTime());
			assertEquals(modelBoard.getScore(), controllerBoard.getScore());
			
			assertEquals(model.getHighScores(), controller.getHighScores());
			assertEquals(model.getDifficulty(), controller.getDifficulty());
			
			assertEquals(Difficulty.EASY_ROW, controller.getRows());
			assertEquals(Difficulty.EASY_COL, controller.getCols());
			
			assertEquals(model.calculateCols(Difficulty.EASY), controller.calcCols(Difficulty.EASY));
			assertEquals(model.calculateRows(Difficulty.EASY), controller.calcRows(Difficulty.EASY));

		//------------------------------------------------------------------------//
		
		// Test 7 ----------------------------------------------------------------//
		
			controller.initModel(null, Difficulty.EASY, null);
			
			// first click
			controller.handleCellLeftClick(0, 0);

			// click all cells that don't have mines
			for (int i = 0; i < Difficulty.EASY_ROW ; i++) {
				for (int j = 0; j < Difficulty.EASY_COL; j++) {
					
					// click on cell if safe
					if (!model.getCell(i, j).hasMine()) {
						
						controller.handleCellLeftClick(i, j);
					}
				}
			}
			
			assertTrue(controller.win());
			assertFalse(controller.lose());

		//------------------------------------------------------------------------//
			
		// Test 8 ----------------------------------------------------------------//
		
			controller.initModel(null, Difficulty.EASY, null);
			
			// first click
			controller.handleCellLeftClick(0, 0);

			// find a mine and click it
			for (int i = 0; i < Difficulty.EASY_ROW ; i++) {
				for (int j = 0; j < Difficulty.EASY_COL; j++) {
					
					// click on cell if safe
					if (model.getCell(i, j).hasMine()) {
						
						controller.handleCellLeftClick(i, j);
						break;
					}
				}
			}

			assertFalse(controller.win());
			assertTrue(controller.lose());

		//------------------------------------------------------------------------//
			
		// Test 9 ----------------------------------------------------------------//
	
			controller.initModel(null, Difficulty.EASY, null);
			
			// first click
			controller.handleCellLeftClick(0, 0);
	
			// check timer getter
			assertEquals(model.getTime(), controller.getTime());
			
			// check iftimer was incremented
			controller.incrementTimer();
			assertEquals(model.getTime(), controller.getTime());

		//------------------------------------------------------------------------//
			
		// Test 9 ----------------------------------------------------------------//
		
			controller.initModel(null, Difficulty.EASY, null);

			assertEquals(GameState.UNSTARTED, controller.getGameState());
			
			// first click
			controller.handleCellLeftClick(0, 0);
			assertEquals(GameState.START_GAME, controller.getGameState());
			
			// find a mine and click it
			for (int i = 0; i < Difficulty.EASY_ROW ; i++) {
				for (int j = 0; j < Difficulty.EASY_COL; j++) {
					
					// click on cell if safe
					if (model.getCell(i, j).hasMine()) {
						
						controller.handleCellLeftClick(i, j);
						break;
					}
				}
			}
			
			controller.initModel(null, Difficulty.EASY, null);
			
			// first click
			controller.handleCellLeftClick(0, 0);
			
			// click all cells that don't have mines
			for (int i = 0; i < Difficulty.EASY_ROW ; i++) {
				for (int j = 0; j < Difficulty.EASY_COL; j++) {
					
					// click on cell if safe
					if (!model.getCell(i, j).hasMine()) {
						
						controller.handleCellLeftClick(i, j);
					}
				}
			}

		//------------------------------------------------------------------------//
	}

	/**
	 * Test method for {@link HighScores}
	 */
	@Test
	public void testHighScores() {
		
		HighScores scores = new HighScores();
		
		scores.addScore("TEST1", 10, Difficulty.EASY);
		scores.addScore("TEST2", 20, Difficulty.EASY);
		scores.addScore("TEST3", 10, Difficulty.MEDIUM);
		scores.addScore("TEST4", 20, Difficulty.MEDIUM);
		scores.addScore("TEST5", 10, Difficulty.EXPERT);
		scores.addScore("TEST6", 20, Difficulty.EXPERT);
		
		ScoreEntry[] easyScores   = scores.getScores(Difficulty.EASY);
		ScoreEntry[] mediumScores = scores.getScores(Difficulty.MEDIUM);
		ScoreEntry[] expertScores = scores.getScores(Difficulty.EXPERT);
		
		assertEquals(easyScores[0].name,   "TEST1");
		assertEquals(easyScores[1].name,   "TEST2");
		assertEquals(mediumScores[0].name, "TEST3");
		assertEquals(mediumScores[1].name, "TEST4");
		assertEquals(expertScores[0].name, "TEST5");
		assertEquals(expertScores[1].name, "TEST6");
	}
}