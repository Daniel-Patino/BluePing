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
	
	public double[] distances1 = new double[] {375, 530.33, 530.33};
	double[] node1 = new double[] {375, 0, 0};
	double[] node2 = new double[] {0, 750, 0};
	double[] node3 = new double[] {750, 750, 0};
	
	public double[] distances2 = new double[] {375, 530.33, 530.33};
	double[] node4 = new double[] {375, 0, 0};
	double[] node5 = new double[] {0, 750, 0};
	double[] node6 = new double[] {750, 750, 0};
	
	public double[] distances3 = new double[] {375, 530.33, 530.33};
	double[] node7 = new double[] {375, 0, 0};
	double[] node8 = new double[] {0, 750, 0};
	double[] node9 = new double[] {750, 750, 0};
	
	public double[] distances4 = new double[] {375, 530.33, 530.33};
	double[] node10 = new double[] {375, 0, 0};
	double[] node11 = new double[] {0, 750, 0};
	double[] node12 = new double[] {750, 750, 0};
	
	public double[] distances5 = new double[] {375, 530.33, 530.33};
	double[] node13 = new double[] {375, 0, 0};
	double[] node14 = new double[] {0, 750, 0};
	double[] node15 = new double[] {750, 750, 0};
	
	double[][] nodeArr1 = {node1, node4, node7, node10, node13};
	double[][] nodeArr2 = {node2, node5, node8, node11, node14};
	double[][] nodeArr3 = {node3, node6, node9, node12, node15};
	double[][] distArr1 = {distances1, distances2, distances3, distances4, distances5};
	
	@Override
	public void start(Stage primaryStage) {
		try {
			ReadFile ourRead = new ReadFile();
			
			/* Instantiate */
			StackPane masterStack = new StackPane();			// What the user will ultimately see
			TrilaterationTest test = new TrilaterationTest();	// What will do the Trilateration
			RealVector[] dots = test.trilateration3DExact(nodeArr1, nodeArr2, nodeArr3, distArr1);		// Used to determine the user Pos
			
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