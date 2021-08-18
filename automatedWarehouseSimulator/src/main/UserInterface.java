package main;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Details here
 * 
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

public class UserInterface extends Application {

	private static int ysize;
	private static int xsize;
	private static Warehouse warehouse;
	private HashMap<Position, Button> buttons;
	private Stage stageT;
	private Scene sceneT;
	private int currentTick;
	private Stage nStage;

	/**
	 * 
	 */
	public UserInterface() {
		buttons = new HashMap<Position, Button>();
		// grid = new HashMap<Position, ArrayList<String>>();
		currentTick = 0;
	}

	public UserInterface(int x, int y, Warehouse warehouse) {
		ysize = y;
		xsize = x;
		this.warehouse = warehouse;
		buttons = new HashMap<Position, Button>();
		currentTick = 0;

	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		UserInterface ui = new UserInterface(xsize, ysize, warehouse);

		Application.launch(args);

	}

	public void updateGrid(HashMap<Position, ArrayList<String>> grid) {

		// Gotta figure out how the hell to update the grid

		// Loop in the buttons
		for (Position buttonP : buttons.keySet()) {
			// Loop in the grid
			for (Position gridP : grid.keySet()) {
				if (buttonP.getX() == gridP.getX() && buttonP.getY() == gridP.getY()) {
					// If positions match, update text to be string
					String t = "";
					ArrayList<String> uids = grid.get(gridP);
					for (String uid : uids) {
						switch (uid.substring(0, 2)) {
						case "ps":
							t += "Packing Station (" + uid + ") \n";
							break;
						case "ss":
							t += "Storage Shelf (" + uid + ") \n";
							break;
						default:
							if (uid.startsWith("c")) {
								t += "Charging Pod (" + uid + ") \n";
								break;

							}
							if (uid.startsWith("r")) {
								t += "Robot (" + uid + ") \n";
								break;
							}
						}
					}
					buttons.get(buttonP).setText(t);
				}
			}

		}

	}

	/**
	 * 
	 * @return
	 */
	public int getCurrentTick() {
		return 0;
	}

	/**
	 * 
	 */

	@Override
	public void start(Stage stage) throws Exception {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

			}
		});

		stageT = stage;
		// Creating a Grid Pane
		GridPane gridPane = new GridPane();

		// Setting size for the pane
		gridPane.setMinSize(400, 200);

		// Setting the padding
		gridPane.setPadding(new Insets(10, 10, 10, 10));

		// Setting the vertical and horizontal gaps between the columns
		gridPane.setVgap(5);
		gridPane.setHgap(5);

		// Setting the Grid alignment
		gridPane.setAlignment(Pos.CENTER);

		// Creating buttons and adding to grid based on specified grid size

		for (int x = 0; x < xsize; x++) {
			for (int y = 0; y < ysize; y++) {
				Button b = new Button();
				b.wrapTextProperty().setValue(true);
				b.setStyle("-fx-font-size:10");
				buttons.put(new Position(x, y), b);
				b.setPrefSize(70, 70);
				gridPane.add(b, x, y);

			}
		}

		updateGrid(warehouse.getGrid());

		Label l = new Label("Current tick: " + currentTick);
		l.setAlignment(Pos.CENTER);
		Font font = Font.font(null, FontWeight.BOLD, 15);
		l.setFont(font);

		Label m = new Label("Welcome to the automated warehouse simulator");
		m.setAlignment(Pos.CENTER);

		Button b = new Button("Next tick");
		b.setPrefHeight(70);
		b.setMaxWidth(Double.MAX_VALUE);
		l.setMaxWidth(Double.MAX_VALUE);
		m.setMaxWidth(Double.MAX_VALUE);
		GridPane.setFillWidth(b, true);
		GridPane.setFillWidth(l, true);
		
		ScrollPane messagePane = new ScrollPane();
		messagePane.setContent(m);
		messagePane.setMaxWidth(Double.MAX_VALUE);
		messagePane.setPrefHeight(100);
		
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (warehouse.isRunning()) {
					currentTick += 1;
					l.setText("Current tick: " + currentTick);
					// Make the tick stuff run here :)
					warehouse.tickAllObjects();
					updateGrid(warehouse.getGrid());
					m.setText(warehouse.getMessage());
				} else {
					if (nStage == null || !nStage.isShowing()) {
						b.setText("Show log");
						m.setText(warehouse.getMessage());
						nStage = new Stage();
						ScrollPane pane = new ScrollPane();
						GridPane grid = new GridPane();
						nStage.setTitle("Log");
						nStage.setScene(new Scene(pane, 500, 200));
						for (int i = 0; i < warehouse.getLog().size(); i++) {
							Label l = new Label(warehouse.getLog().get(i));
							l.setWrapText(true);
							l.setPrefWidth(500);
							l.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
									CornerRadii.EMPTY, BorderWidths.DEFAULT)));
							grid.add(l, 0, i);

						}
						m.setText("Simulation has ended, please see log for details");
						pane.setContent(grid);
						nStage.show();
					}
				}
			}
		};
		b.setOnAction(event);
		gridPane.add(b, 0, ysize + 1, xsize + 1, 1);
		gridPane.add(l, 0, ysize + 2, xsize + 1, 1);
		gridPane.add(messagePane, 0, ysize + 3, xsize + 1, 1);

		// Creating a scene object
		Scene scene = new Scene(gridPane);
		sceneT = scene;

		// Setting title to the Stage
		stage.setTitle("Warehouse moment");

		// Adding scene to the stage
		stage.setScene(scene);

		// Displaying the contents of the stage
		stage.show();

	}
}
