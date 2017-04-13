import org.apache.commons.math3.linear.RealVector;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {	
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 960;
	public static final int DETAILS_BUTTONS = 3;

	@Override
	public void start(Stage primaryStage) {
		try {
			//ReadFile ourRead = new ReadFile();
			
			/* Instantiate */
			/*StackPane masterStack = new StackPane();			// What the user will ultimately see
			TrilaterationTest test = new TrilaterationTest();	// What will do the Trilateration
			RealVector[] dots = test.trilateration3DExact();*/		// Used to determine the user Pos
			
			/* Creates the Coordinate Scene */
			//CoordinateScene coordScene = new CoordinateScene(test, dots, false);
			
			/* Creates the Details Scene */
			DetailsScene detScene = new DetailsScene(DETAILS_BUTTONS);
			
			/* Creates the Widgets Scene */
			WidgetsScene widScene = new WidgetsScene();
			
			/* Creates the masterStack that will hold all the scenes */
			/*StackPane.setAlignment(coordScene.coordinatePane, Pos.TOP_LEFT);
			StackPane.setAlignment(detScene.getDetailsScene(), Pos.TOP_RIGHT);
			StackPane.setAlignment(widScene.widgetsPane, Pos.BOTTOM_CENTER);
			masterStack.getChildren().addAll(coordScene.coordinatePane, detScene.getDetailsScene(), widScene.widgetsPane);			
			Scene openScene = new Scene(masterStack, WIDTH, HEIGHT);*.
			
			/* Shows the Stage */
			/*primaryStage.setScene(openScene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("BluePing - Indoor Positioning System");
			primaryStage.show();*/

			/* Instantiate */
            StackPane masterStack = new StackPane();			// What the user will ultimately see
            TrilaterationTest test = new TrilaterationTest();	// What will do the Trilateration

			/* Implements the Database*/
            String url = "jdbc:mysql://localhost:3306/blueping?autoReconnect=true&useSSL=false";
            String user = "root";
            String password = "addjteam4";

            Database db = new Database(url, user, password);
            db.connect();

            ArrayList<Integer> size = db.runIntQuery("SELECT COUNT(*) FROM `beacon1`");
            System.out.println(size.get(0));

            for (int i = 1; i <= 10; i++) {
                db.prepareQuery("SELECT MAC FROM beacon1 WHERE id = (?)");
                db.setQueryId(i);
                String mac = db.runStringPrepQuery();
                //System.out.println(mac);

                db.prepareQuery("SELECT RSSI FROM beacon1 WHERE MAC = (?) ORDER BY `TIME` DESC LIMIT 1");
                db.setQueryId(mac);
                double rssi1 = db.runIntPrepQuery();
                //System.out.println(rssi1);
                rssi1 = RSSItoDistance.calculateDistance(rssi1);
                //System.out.println(rssi1);

                db.prepareQuery("SELECT RSSI FROM beacon2 WHERE MAC = (?) ORDER BY `TIME` DESC LIMIT 1");
                db.setQueryId(mac);
                double rssi2 = db.runIntPrepQuery();
                //System.out.println(rssi2);
                rssi2 = RSSItoDistance.calculateDistance(rssi2);
                //System.out.println(rssi2);

                db.prepareQuery("SELECT RSSI FROM beacon3 WHERE MAC = (?) ORDER BY `TIME` DESC LIMIT 1");
                db.setQueryId(mac);
                double rssi3 = db.runIntPrepQuery();
                //System.out.println(rssi3);
                rssi3 = RSSItoDistance.calculateDistance(rssi3);
                //System.out.println(rssi3);

                test.idToDistances.put(mac, new double[]{rssi1, rssi2, rssi3});
            }

            RealVector[] dots = test.trilateration3DExact();		// Used to determine the user Pos

            /* Creates the Coordinate Scene */
            CoordinateScene coordScene = new CoordinateScene(test, dots, false);

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