package view;

import controller.MinesweeperController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class BoardGridView extends GridPane {
		
		private int rows; 
		private int cols;
		private String boardBGColor = "002b36";
		private static final int CELL_SIZE = 30;
		private MinesweeperController controller;
		
		public BoardGridView(int rows, int cols,
				MinesweeperController controller) {
			
			this.controller = controller;
			this.rows = rows;
			this.cols = cols;
			
			// Board GridPane setup
			this.setPadding(new Insets(8,8,8,8));
			this.setBackground(new Background(
									new BackgroundFill(
											Color.web(boardBGColor),
											CornerRadii.EMPTY,
											Insets.EMPTY)));
			
			// Create column and grid restraints on this
			for (int i = 0; i < rows; i++) {
				
				RowConstraints row = new RowConstraints();
				row.setMaxHeight(CELL_SIZE);
				row.setPrefHeight(CELL_SIZE);
				row.setMinHeight(CELL_SIZE);
				row.setValignment(VPos.CENTER);
				this.getRowConstraints().add(row);
			}
			
			for (int i = 0; i < cols; i++) {
				
				ColumnConstraints col = new ColumnConstraints();
				col.setMaxWidth(CELL_SIZE);
				col.setPrefWidth(CELL_SIZE);
				col.setMinWidth(CELL_SIZE);
				col.setHalignment(HPos.CENTER);	
				this.getColumnConstraints().add(col);
			}
			
			// Populate boardgrid with CellView objects
			
			for (int i = 0; i < rows ; i++ ) {
				for (int j = 0; j < cols; j++) {
		
					CellView blankCell = new CellView(controller);
					this.add(blankCell, i, j);
					
					//FIXME: Dummy event handler
					blankCell.setOnMouseClicked((event) -> {
						
						System.out.println("Cell is listening!");
					});		
				}
			}
		}
}
