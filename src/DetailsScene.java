import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class creates the scene for details regarding the application
 * the details will be placed to the left of the CoordinateScene and
 * will contain three tabs at the top which will give the user control
 * over the information to be displayed.
 * 
 * The three tabs will be;
 * 1. Pings (A list of MAC Addresses)
 * 2. (Empty)
 * 3. (Empty)
 * 
 * @author Daniel Patino
 * @author Danny Suarez
 */
public class DetailsScene {
	
	private HBox optionsTabs = new HBox();
	private BorderPane detailsScene = new BorderPane();	
	
	/**
	 * The constructor will create a DetailsScene with the parameters
	 */
	public DetailsScene(int numberOfButtons){
		Button[] buttons = buttons(numberOfButtons);
		optionsTabs.getChildren().addAll(buttons);
		detailsScene.setTop(optionsTabs);
		detailsScene.setMaxSize(Main.WIDTH - 750, Main.HEIGHT - (Main.HEIGHT - 750));
		detailsScene.setStyle("-fx-border-color: red;");
	}
	
	/**
	 * Creates the buttons for the HBox
	 * 
	 * @param numberOfButtons
	 * @return
	 */
	private Button[] buttons(int numberOfButtons){
		
		Button[] buttons = new Button[numberOfButtons];
		
		for(int i = 0; i < numberOfButtons; i++){
			buttons[i] = new Button("Text");
			buttons[i].setPrefWidth((Main.WIDTH - 750) / numberOfButtons);
		}

		return buttons;
	}
	
	/* Setters and Getters */
	
	public HBox getOptionsTabs() {
		return optionsTabs;
	}

	public void setOptionsTabs(HBox optionsTabs) {
		this.optionsTabs = optionsTabs;
	}

	public BorderPane getDetailsScene() {
		return detailsScene;
	}

	public void setDetailsScene(BorderPane detailsScene) {
		this.detailsScene = detailsScene;
	}
}