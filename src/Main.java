import org.apache.commons.math3.linear.RealVector;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.*;
import java.lang.Thread;

public class Main extends Application {	
	
	public static final int WIDTH = 1440;
	public static final int HEIGHT = 900;
	public static final int DETAILS_BUTTONS = 3;

	@Override
	public void start(Stage primaryStage) {
		try {
            /* Instantiate */
            StackPane masterStack = new StackPane();			// What the user will ultimately see
            Database db = DBHandler.connectDatabase();
            TrilaterationTest test = new TrilaterationTest();
            test = DBHandler.retrieveData(db);
            RealVector[] dots = test.trilateration3DExact();		// Used to determine the user Pos

            /* Creates the Coordinate Scene */
            CoordinateScene coordScene = new CoordinateScene(test, dots, false);

			/* Creates the Details Scene */
			DetailsScene detScene = new DetailsScene(DETAILS_BUTTONS);
			
			/* Creates the Widgets Scene */
			WidgetsScene widScene = new WidgetsScene();

            /* Creates the masterStack that will hold all the scenes */
            StackPane.setAlignment(coordScene.coordinatePane, Pos.TOP_LEFT);
            StackPane.setAlignment(detScene.getDetailsScene(), Pos.TOP_RIGHT);
            StackPane.setAlignment(widScene.widgetsPane, Pos.BOTTOM_CENTER);
            masterStack.getChildren().addAll(coordScene.coordinatePane, detScene.getDetailsScene(), widScene.widgetsPane);
            Scene openScene = new Scene(masterStack, WIDTH, HEIGHT);

            /* Shows the Stage */
            primaryStage.setScene(openScene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("BluePing - Indoor Positioning System");
            primaryStage.show();

            for (int j = 0; j < 20; j++) {
                test = DBHandler.retrieveData(db);
                dots = test.trilateration3DExact();		// Used to determine the user Pos
                coordScene = new CoordinateScene(test, dots, false);
                masterStack.getChildren().remove(0);
                StackPane.setAlignment(coordScene.coordinatePane, Pos.TOP_LEFT);
                masterStack.getChildren().add(0, coordScene.coordinatePane);
                //Thread.sleep(1000);
            }
        }
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception
	{
		launch(args);
	}
}