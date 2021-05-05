package testing;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Observer;
import java.util.Observable;

import model.Cell;
import model.GameState;
import model.Difficulty;
import model.HighScores;
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
		
			model.initialize(null, Difficulty.EASY, null);
			
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
		
			model.initialize(null, Difficulty.MEDIUM, null);
			
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
		
		scores.addScore("JOSH3", 500, 0);
		scores.addScore("JOSH2", 700, 0);
		scores.addScore("JOSH1", 900, 0);

		for (HighScores.ScoreEntry e : scores.getScores(0)){
			
			System.out.println(e.name + " " + e.score);
		}

		scores.addScore("BOB", 9000, 0);

		for(HighScores.ScoreEntry e : scores.getScores(0)) {
			
			System.out.println(e.name + " " + e.score);
		}
	}
}