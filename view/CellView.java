package view;

import controller.MinesweeperController;
import javafx.geometry.Insets;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class CellView extends StackPane {
	
	// State 
	public enum CellState{
		COVERED,
		REVEALED,
		FLAGGED,
	}
	
	private boolean mined;
	private int neighborMineCount;
	private CellState state;
	private Text neighborCountText;
	MinesweeperController controller;
	// Style variables for StackPane
	private String cellBorderColor = "657b83";
	
	private BorderStroke blankCellBorderStroke = new BorderStroke(
											Color.web(cellBorderColor),
											BorderStrokeStyle.SOLID,
											CornerRadii.EMPTY,
											BorderWidths.DEFAULT);
	
	private Border blankCellBorder = new Border(blankCellBorderStroke);
	
	// Default constructor with init cell settings
	public CellView(MinesweeperController controller) {
		this.controller = controller;
		this.mined = false;
		this.neighborMineCount = 0;
		this.state = CellState.COVERED;
		this.setPadding(new Insets(2,2,2,2));
		this.setBorder(blankCellBorder);
	}
	
	
}
