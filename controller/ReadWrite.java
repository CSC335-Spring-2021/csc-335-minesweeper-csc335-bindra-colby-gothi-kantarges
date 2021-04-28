package controller;

import model.MinesweeperBoard;

import java.io.*;

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
}
