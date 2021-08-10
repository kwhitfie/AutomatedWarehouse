package main;
import javafx.application.Application; 
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.layout.GridPane; 
import javafx.scene.text.Text; 
import javafx.scene.control.TextField; 
import javafx.stage.Stage;

/**
 * Details here
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

public class UserInterface extends Application{
	
	private int ysize;
	private int xsize;
	
	/**
	 * 
	 */
	public UserInterface() {
		ysize = 7; 
		xsize = 7;
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}
	
	/**
	 * 
	 * @return
	 */
	public void showGrid(int width, int height) {
		
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
	public void showUserInterface() {
		
	}
	
	/**
	 * 
	 */


	@Override
	public void start(Stage stage) throws Exception {
		
	      
		//Creating a Grid Pane 
	      GridPane gridPane = new GridPane();    
	      
	      //Setting size for the pane  
	      gridPane.setMinSize(400, 200); 
	       
	      //Setting the padding  
	      gridPane.setPadding(new Insets(10, 10, 10, 10)); 
	      
	      //Setting the vertical and horizontal gaps between the columns 
	      gridPane.setVgap(5); 
	      gridPane.setHgap(5);       
	      
	      //Setting the Grid alignment 
	      gridPane.setAlignment(Pos.CENTER); 
	      
	      //Creating buttons and adding to grid based on specified grid size
	      
	      for(int x =0; x<=xsize;x++) {
	    	  for(int y =0; y<=ysize;y++) {
	    		  Button b = new Button("("+x+","+y+")"); 
	    		  b.setPrefSize(70,70);
	    		  gridPane.add(b, x, y);
		    	  
		      }
	      }
	       
	      
	      //Creating a scene object 
	      Scene scene = new Scene(gridPane);  
	      
	      //Setting title to the Stage 
	      stage.setTitle("Warehouse moment"); 
	         
	      //Adding scene to the stage 
	      stage.setScene(scene); 
	         
	      //Displaying the contents of the stage 
	      stage.show(); 
		
	}
}
