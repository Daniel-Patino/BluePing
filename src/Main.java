import org.apache.commons.math3.linear.RealVector;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {	
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 960;
	public static final int DETAILS_BUTTONS = 3;
	
	@Override
	public void start(Stage primaryStage) {
		try {	
			/* Instantiate */
			StackPane masterStack = new StackPane();			// What the user will ultimately see
			TrilaterationTest test = new TrilaterationTest();	// What will do the Trilateration
			RealVector dot = test.trilateration3DExact();		// Used to determine the user Pos
			
			/* Creates the Coordinate Scene */
			CoordinateScene coordScene = new CoordinateScene(test, dot, false);
			
			/* Creates the Details Scene */
			DetailsScene detScene = new DetailsScene(DETAILS_BUTTONS);
			
			/* Creates the Widgets Scene */
			WidgetsScene widScene = new WidgetsScene();
			
			/* Creates the masterStack, what the user will see */
			StackPane.setAlignment(coordScene.coordinatePane, Pos.TOP_LEFT);
			StackPane.setAlignment(detScene.getDetailsScene(), Pos.TOP_RIGHT);
			StackPane.setAlignment(widScene.widgetsPane, Pos.BOTTOM_CENTER);
			masterStack.getChildren().addAll(coordScene.coordinatePane, detScene.getDetailsScene(), widScene.widgetsPane);			
			Scene openScene = new Scene(masterStack, WIDTH, HEIGHT);
			
			/* Shows the Stage */
			primaryStage.setScene(openScene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}