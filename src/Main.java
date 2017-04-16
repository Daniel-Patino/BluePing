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
            //primaryStage.show();

            /*System.out.println(RSSItoDistance.calculateDistance(-30.60611694));
            System.out.println(RSSItoDistance.calculateDistance(-47.45745746));
            System.out.println(RSSItoDistance.calculateDistance(-55.51740482));
            System.out.println(RSSItoDistance.calculateDistance(-58.28055556));
            System.out.println(RSSItoDistance.calculateDistance(-59.56140351));
            System.out.println(RSSItoDistance.calculateDistance(-60.72239422));
            System.out.println(RSSItoDistance.calculateDistance(-63.93225918));
            System.out.println(RSSItoDistance.calculateDistance(-64.16424116));
            System.out.println(RSSItoDistance.calculateDistance(-70.82926829));
            System.out.println(RSSItoDistance.calculateDistance(-70.26315789));
            System.out.println(RSSItoDistance.calculateDistance(-70.35135135));
            System.out.println(RSSItoDistance.calculateDistance(-71.30612245));*/

            /*System.out.println(RSSItoDistance.calculateEnvConst(-30.60611694, 0));
            System.out.println(RSSItoDistance.calculateEnvConst(-47.45745746, 1));
            System.out.println(RSSItoDistance.calculateEnvConst(-55.51740482, 1.524));
            System.out.println(RSSItoDistance.calculateEnvConst(-58.28055556, 3.048));
            System.out.println(RSSItoDistance.calculateEnvConst(-59.56140351, 4.572));
            System.out.println(RSSItoDistance.calculateEnvConst(-60.72239422, 6.096));
            System.out.println(RSSItoDistance.calculateEnvConst(-63.93225918, 7.62));
            System.out.println(RSSItoDistance.calculateEnvConst(-64.16424116, 9.144));
            System.out.println(RSSItoDistance.calculateEnvConst(-70.82926829, 10.668));
            System.out.println(RSSItoDistance.calculateEnvConst(-70.26315789, 12.192));
            System.out.println(RSSItoDistance.calculateEnvConst(-70.35135135, 13.716));
            System.out.println(RSSItoDistance.calculateEnvConst(-71.30612245, 15.24));*/
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