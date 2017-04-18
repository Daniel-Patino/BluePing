import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {	
	
	public static final int WIDTH = 1440;
	public static final int HEIGHT = 900;
	public static final int DETAILS_BUTTONS = 3;

	@Override
	public void start(Stage primaryStage) {
		try {
			/* Instantiate */
            StackPane masterStack = new StackPane();			// What the user will ultimately see
            
            /* Creates the Coordinate Scene */

            CoordinateScene coordScene = new CoordinateScene(false);
            
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

            /*System.out.println(RSSItoDistance.calculateDistance(-47.6));
            System.out.println(RSSItoDistance.calculateDistance(-41.94871795));
            System.out.println(RSSItoDistance.calculateDistance(-48.35714286));
            System.out.println(RSSItoDistance.calculateDistance(-52.47368421));
			System.out.println(RSSItoDistance.calculateDistance(-54));*/

            /*System.out.println(RSSItoDistance.calculateEnvConst(-25.5, 0));
            System.out.println(RSSItoDistance.calculateEnvConst(-41.94871795, 1));
            System.out.println(RSSItoDistance.calculateEnvConst(-48.35714286, 2));
            System.out.println(RSSItoDistance.calculateEnvConst(-52.47368421, 3));
            System.out.println(RSSItoDistance.calculateEnvConst(-54, 4));*/
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