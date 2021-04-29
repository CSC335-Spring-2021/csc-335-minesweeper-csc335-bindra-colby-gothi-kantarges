package model;

import java.io.Serializable;
import java.util.PriorityQueue;
import java.util.Queue;

public class HighScores implements Serializable {

    /**
     * generated serial version ID
     */
    private static final long serialVersionUID = -8510255117516896366L;

    private Queue<ScoreEntry> scores;

    public HighScores(){
        scores = new PriorityQueue<>();

        //Add 5 dummy scores
        scores.add(new ScoreEntry("JEFF",000));
        scores.add(new ScoreEntry("ABE",000));
        scores.add(new ScoreEntry("MARK",000));
        scores.add(new ScoreEntry("JOE",000));
        scores.add(new ScoreEntry("KADE",000));
    }

    /**
     * Adds a score entry to the priority queue
     * @param name
     * @param score
     */
    public void addScore(String name, int score){
        scores.add(new ScoreEntry(name,score));
    }

    /**
     * Returns an array of the scores
     * @return array of scores
     */
    public ScoreEntry[] getScores(){
        ScoreEntry[] retval = new ScoreEntry[5];

        //Take off the top 5 scores
        for(int i = 0; i<5;i++){
            retval[i] = scores.poll();
        }

        //Put them back because we polled them
        for(ScoreEntry e : retval){
            scores.add(e);
        }

        return retval;
    }


    public class ScoreEntry implements Comparable, Serializable{
        public String name;
        public int score;

        public ScoreEntry(String name, int score){
            this.name = name;
            this.score = score;
        }

        @Override
        public int compareTo(Object o) {
            return ((ScoreEntry) o).score -this.score;
        }
    }
}
