package testing;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Observable;
import java.util.Observer;

import model.Difficulty;
import model.HighScores;
import model.MinesweeperModel;

import controller.MinesweeperController;

@SuppressWarnings("deprecation")
public class MinesweeperTest {
	
	// TODO: remove hardcoding
	final static int BEGINNER = 0;
	
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
		
		// TODO: remove hardcoding
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