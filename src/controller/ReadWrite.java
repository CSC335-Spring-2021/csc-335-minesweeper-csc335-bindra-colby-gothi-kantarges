package controller;

import java.io.*;

import model.HighScores;
import model.MinesweeperBoard;

public class ReadWrite {

    /**
     * Reads in the board save game file if available
     * Requires a path to the save_game.dat file
     *
     * @return the loaded board or null if it could not load it
     */
    public MinesweeperBoard readSaveData(String path) {

        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            MinesweeperBoard board = (MinesweeperBoard) ois.readObject();
            ois.close();

            return board;

        } catch (IOException | ClassNotFoundException e) {

            System.err.println("Could not find/Read save_game.dat");
            return null;
        }
    }

    /**
     * Writes out the board class to a save game file
     *
     * @param board
     * @return True if successfully wtote out the board false if an error occured
     */
    public boolean writeSaveData(MinesweeperBoard board, String path) {

        try {

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path, false));
            oos.writeObject(board);
            oos.close();

            return true;

        } catch (IOException e) {

            System.err.println("Could not write save_game.dat");
            return false;
        }
    }

    /**
     * Reads in the HighScores file if available
     *  high_scores.dat file
     *
     * @return the loaded scores or null if it could not load it
     */
    public HighScores readHighScoreData() {
    	
        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("high_scores.dat"));
            HighScores highScores = (HighScores) ois.readObject();
            ois.close();

            return highScores;

        } catch (IOException | ClassNotFoundException e) {

            System.err.println("Could not find/Read high_scores.dat");
            return null;
        }
    }

    /**
     * Writes out the HighScores class to a save game file
     *
     * @param highScores
     * @return True if successfully wtote out the board false if an error occured
     */
    public boolean writeHighScoreData(HighScores highScores) {

        try {

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("high_scores.dat", false));
            oos.writeObject(highScores);
            oos.close();

            return true;

        } catch (IOException e) {

            System.err.println("Could not write high_scores.dat");
            return false;
        }
    }
}