package model;

import java.io.Serializable;

import java.util.Queue;
import java.util.PriorityQueue;

public class HighScores implements Serializable {

    /**
     * generated serial version ID
     */
    private static final long serialVersionUID = -8510255117516896366L;

    private Queue<ScoreEntry> easyScores;
    private Queue<ScoreEntry> medScores;
    private Queue<ScoreEntry> expertScores;

    public HighScores() {
    	
        easyScores = new PriorityQueue<>();

        // Add 5 dummy scores
        easyScores.add(new ScoreEntry("JEFF", 999));
        easyScores.add(new ScoreEntry("ABEL", 999));
        easyScores.add(new ScoreEntry("MARK", 999));
        easyScores.add(new ScoreEntry("JOEY", 999));
        easyScores.add(new ScoreEntry("KADE", 999));
        
        medScores = new PriorityQueue<>();

        // Add 5 dummy scores
        medScores.add(new ScoreEntry("JEFF", 999));
        medScores.add(new ScoreEntry("ABEL", 999));
        medScores.add(new ScoreEntry("MARK", 999));
        medScores.add(new ScoreEntry("JOEY", 999));
        medScores.add(new ScoreEntry("KADE", 999));
        
        expertScores = new PriorityQueue<>();

        // Add 5 dummy scores
        expertScores.add(new ScoreEntry("JEFF", 999));
        expertScores.add(new ScoreEntry("ABEL", 999));
        expertScores.add(new ScoreEntry("MARK", 999));
        expertScores.add(new ScoreEntry("JOEY", 999));
        expertScores.add(new ScoreEntry("KADE", 999));
    }

    /**
     * Adds a score entry to the priority queue
     * 
     * @param name
     * @param score
     * @param difficulty
     */
    public void addScore(String name, int score, int difficulty) {
        if (difficulty == Difficulty.EASY)
        	easyScores.add(new ScoreEntry(name, score));
        else if (difficulty == Difficulty.MEDIUM)
        	medScores.add(new ScoreEntry(name, score));
        else if (difficulty == Difficulty.EXPERT)
        	expertScores.add(new ScoreEntry(name, score));
    }

    /**
     * Returns an array of the scores
     * 
     * @return array of scores
     */
    public ScoreEntry[] getScores(int difficulty) {
    	
    	Queue<ScoreEntry> scores;
		if (difficulty == Difficulty.EASY)
    		scores = easyScores;
		else if (difficulty == Difficulty.MEDIUM)
			scores = medScores;
		else
			scores = expertScores;
    	
        ScoreEntry[] retval = new ScoreEntry[5];

        // Take off the top 5 scores
        for (int i = 0; i < 5; i++) {
        	
            retval[i] = scores.poll();
        }

        // Put them back because we polled them
        for (ScoreEntry e : retval) {
            scores.add(e);
        }

        return retval;
    }

    public class ScoreEntry implements Comparable, Serializable {		// FIXME: parameterize with a generic type
    	
        /**
		 * generated serial version ID
		 */
		private static final long serialVersionUID = -1640787370787311993L;
		
		public String  name;
        public Integer score;

        public ScoreEntry(String name, int score) {
            this.name  = name;
            this.score = score;
        }

        @Override
        public int compareTo(Object o) {
            return -(((ScoreEntry) o).score - this.score);				// we want to order based on least amount of time taken
        }
    }
}