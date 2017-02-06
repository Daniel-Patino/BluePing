import org.apache.commons.math3.linear.RealVector;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		
			TrilaterationTest test = new TrilaterationTest();
			RealVector dot = test.trilateration3DExact();
			
			Circle pos = new Circle(dot.getEntry(0), dot.getEntry(1), 10, Color.BLUE);
			
			Circle node1 = new Circle(test.node1[0], test.node1[1], test.distances[0]);
			Circle node2 = new Circle(test.node2[0], test.node2[1], test.distances[1]);
			Circle node3 = new Circle(test.node3[0], test.node3[1], test.distances[2]);
			
			node1.setStroke(Color.BLACK);
			node1.setStrokeWidth(3);
			node1.setFill(null);

			node2.setStroke(Color.BLACK);
			node2.setStrokeWidth(3);
			node2.setFill(null);
			
			node3.setStroke(Color.BLACK);
			node3.setStrokeWidth(3);
			node3.setFill(null);
			
			Pane bp = new Pane();
			bp.getChildren().addAll(pos, node3, node2, node1);
			
			Scene openScene = new Scene(bp, 1000, 1000);
			
			
			
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