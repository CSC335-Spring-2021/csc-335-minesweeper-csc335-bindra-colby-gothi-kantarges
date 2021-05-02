package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;

import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

import model.MinesweeperBoard;
import controller.MinesweeperController;

public class BoardGridView extends GridPane {

	private int rows;
	private int cols;

	private String boardBGColor = "002b36";
	
	private static final int CELL_SIZE = 30;

	private Node[][] gridPaneArray = null;

	public BoardGridView(int rows, int cols, MinesweeperController controller) {
		initGrid(rows,cols, controller);
	}
	
	public void initGrid(int rows, int cols, MinesweeperController controller) {
		this.rows = rows;
		this.cols = cols;
		
		// Board GridPane setup
		this.setPadding(new Insets(8));
		this.setBackground(new Background(new BackgroundFill(Color.web(boardBGColor), CornerRadii.EMPTY, Insets.EMPTY)));

		// Create column and grid restraints on this
		for (int i = 0; i < this.rows; i++) {

			RowConstraints row = new RowConstraints();

			row.setMaxHeight(CELL_SIZE);
			row.setPrefHeight(CELL_SIZE);
			row.setMinHeight(CELL_SIZE);
			row.setValignment(VPos.CENTER);

			this.getRowConstraints().add(row);
		}

		for (int i = 0; i < this.cols; i++) {

			ColumnConstraints col = new ColumnConstraints();

			col.setMaxWidth(CELL_SIZE);
			col.setPrefWidth(CELL_SIZE);
			col.setMinWidth(CELL_SIZE);
			col.setHalignment(HPos.CENTER);

			this.getColumnConstraints().add(col);
		}

		// Populate board grid with CellView objects
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {

				CellView blankCell = new CellView();
				this.add(blankCell, i, j);

				blankCell.setOnMouseClicked((event) -> {

					// Get the cellView that was the source of the click
					CellView clickedCell = (CellView) event.getSource();

					// Parse it's row and column information
					int r = GridPane.getRowIndex(clickedCell);
					int c = GridPane.getColumnIndex(clickedCell);

					// parse whether click was left click or right click
					if (event.getButton() == MouseButton.PRIMARY) {
						controller.handleCellLeftClick(r, c);
					}

					if (event.getButton() == MouseButton.SECONDARY) {
						controller.handleCellRightClick(r, c);
					}
				});
			}
		}

		// Set up a 2D array of nodes of CellView children to make it
		// easier to update them via their directly accessing row and col
		this.gridPaneArray = new Node[rows][cols];
		
		for (Node node : this.getChildren()) {

			this.gridPaneArray[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = node;
		}
	}
	
	public void updateCells(MinesweeperBoard mb) {
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {

				Node node = this.gridPaneArray[i][j];

				// CellView decides what to show based on state of
				// model's cell at same row/col
				((CellView) node).updateCellView(mb.getBoard()[i][j]);
			}
		}
	}


	public void redrawGrid(int rows, int cols, MinesweeperController controller) {
		this.rows = rows;
		this.cols = cols;
		
		// Board GridPane setup
		this.setPadding(new Insets(8));
		this.setBackground(new Background(new BackgroundFill(Color.web(boardBGColor), CornerRadii.EMPTY, Insets.EMPTY)));

		// Create column and grid restraints on this
		for (int i = 0; i < this.rows; i++) {

			RowConstraints row = new RowConstraints();

			row.setMaxHeight(CELL_SIZE);
			row.setPrefHeight(CELL_SIZE);
			row.setMinHeight(CELL_SIZE);
			row.setValignment(VPos.CENTER);

			this.getRowConstraints().add(row);
		}

		for (int i = 0; i < this.cols; i++) {

			ColumnConstraints col = new ColumnConstraints();

			col.setMaxWidth(CELL_SIZE);
			col.setPrefWidth(CELL_SIZE);
			col.setMinWidth(CELL_SIZE);
			col.setHalignment(HPos.CENTER);

			this.getColumnConstraints().add(col);
		}

		// Populate board grid with CellView objects
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {

				CellView blankCell = new CellView();
				this.add(blankCell, i, j);
				System.err.println("added node at " + i + j );
				blankCell.setOnMouseClicked((event) -> {

					// Get the cellView that was the source of the click
					CellView clickedCell = (CellView) event.getSource();

					// Parse it's row and column information
					int r = GridPane.getRowIndex(clickedCell);
					int c = GridPane.getColumnIndex(clickedCell);

					// parse whether click was left click or right click
					if (event.getButton() == MouseButton.PRIMARY) {
						controller.handleCellLeftClick(r, c);
					}

					if (event.getButton() == MouseButton.SECONDARY) {
						controller.handleCellRightClick(r, c);
					}
				});
			}
		}

		// Set up a 2D array of nodes of CellView children to make it
		// easier to update them via their directly accessing row and col
		this.gridPaneArray = new Node[rows][cols];
		
		for (Node node : this.getChildren()) {
			this.gridPaneArray[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = node;
		}
		
	}
}