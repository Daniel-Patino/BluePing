import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class WidgetsScene {
	
	BorderPane widgetsPane = new BorderPane();
	
	public WidgetsScene(){
		Slider slider = new Slider();
		slider.setMin(0);
		slider.setMax(400);
		slider.setValue(40);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(50);
		slider.setMinorTickCount(5);
		slider.setBlockIncrement(10);
		slider.setMaxWidth(500);
		slider.setMinWidth(500);
		widgetsPane.setTop(slider);
		widgetsPane.setMaxSize(Main.WIDTH, Main.HEIGHT - 750);
		widgetsPane.setStyle("-fx-border-color: red;");
		//widgetsPane.getChildren().addAll(slider);
	}	
}
