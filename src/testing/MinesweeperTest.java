package testing;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Observer;
import java.util.Random;
import java.util.Observable;

import model.Cell;
import model.Difficulty;
import model.GameState;
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
				assertEquals(0, model.getCell(i, j).getNeighbors());
				
				model.getCell(i, j).flag();
				model.getCell(i, j).addMine();
				model.getCell(i, j).setNeighbors(8);
				
				assertEquals(true, model.getCell(i, j).hasFlag());
				assertEquals(true, model.getCell(i, j).hasMine());
				assertEquals(8, model.getCell(i, j).getNeighbors());

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
		
		assertEquals(true, model.isInBounds(Difficulty.EASY_ROW-1, 
				Difficulty.EASY_COL -1 ));	
		assertEquals(false, model.isInBounds(Difficulty.EASY_ROW, 
											Difficulty.EASY_COL));
		
		assertEquals(false, model.isInBounds(-1, -1));
		
		// Check number of rows, cols, 
		assertEquals( Difficulty.EASY_ROW, model.getRows());
		assertEquals( Difficulty.EASY_COL, model.getCols());
		
		// make sure calculate methods return correct numbers
		assertEquals(Difficulty.EASY_ROW, model.calculateRows(Difficulty.EASY));
		assertEquals(Difficulty.EASY_COL, model.calculateCols(Difficulty.EASY));
		assertEquals(Difficulty.MEDIUM_ROW, model.calculateRows(Difficulty.MEDIUM));
		assertEquals(Difficulty.MEDIUM_COL, model.calculateCols(Difficulty.MEDIUM));
		assertEquals(Difficulty.EXPERT_ROW, model.calculateRows(Difficulty.EXPERT));
		assertEquals(Difficulty.EXPERT_COL, model.calculateCols(Difficulty.EXPERT));
		
		
		
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
						if (!testGrid[i][j].hasMine() && !testGrid[i][j].hasFlag()) {
							testGrid[i][j].reveal();
						}
						
					default:
						break;
						
				}
			}	
		}
		
		// make a new minesweeper board with this randomized grid
		MinesweeperBoard testBoard = new MinesweeperBoard(testGrid, 10, 10, 10, 
														Difficulty.MEDIUM_ROW,
														Difficulty.MEDIUM_COL,
														Difficulty.MEDIUM,
														false,
														GameState.UNSTARTED);
		
		
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
		
		
	}
	
	/**
	 * Test method for {@link MinesweeperController}
	 */
	@Test
	public void testController() {
		
		// TODO: test the controller
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
		
		ScoreEntry[] easyScores = scores.getScores(Difficulty.EASY);
		ScoreEntry[] mediumScores = scores.getScores(Difficulty.MEDIUM);
		ScoreEntry[] expertScores = scores.getScores(Difficulty.EXPERT);
		
		assertEquals(easyScores[0].name, "TEST1");
		assertEquals(easyScores[1].name, "TEST2");
		assertEquals(mediumScores[0].name, "TEST3");
		assertEquals(mediumScores[1].name, "TEST4");
		assertEquals(expertScores[0].name, "TEST5");
		assertEquals(expertScores[1].name, "TEST6");
		
		
	}
}