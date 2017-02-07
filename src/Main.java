import org.apache.commons.math3.linear.RealVector;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {	
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 960;
	
	@Override
	public void start(Stage primaryStage) {
		try {	
			StackPane masterStack = new StackPane();
			TrilaterationTest test = new TrilaterationTest();
			RealVector dot = test.trilateration3DExact();
			
			CoordinateScene coordScene = new CoordinateScene(test, dot, false);
			coordScene.coordinatePane.setTranslateX((coordScene.coordinatePane.getMaxWidth() / 2) - WIDTH/2);
			coordScene.coordinatePane.setTranslateY((coordScene.coordinatePane.getMaxHeight() / 2) - HEIGHT/2);
			
			masterStack.getChildren().addAll(coordScene.coordinatePane);			
			Scene openScene = new Scene(masterStack, WIDTH, HEIGHT);
			
			primaryStage.setScene(openScene);
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