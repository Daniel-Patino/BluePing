import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;

public class WidgetsScene {
	
	public BorderPane widgetsPane = new BorderPane();
	public Slider slider = new Slider();
	
	public WidgetsScene()
	{
		defineSlider();
		defineWidgetsPane();
	}	
	
	private void defineWidgetsPane()
	{
		widgetsPane.setTop(slider);
		widgetsPane.setMaxSize(Main.WIDTH, Main.HEIGHT - 800);
		widgetsPane.setStyle("-fx-border-color: red;");
	}
	
	private void defineSlider()
	{
		slider.setMaxSize(1440, 200);
		slider.setMin(0);
		slider.setMax(25);
		slider.setValue(slider.getMax());
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(5);
		slider.setMinorTickCount(1);;
		slider.setBlockIncrement(10);
		slider.setBlockIncrement(5);
		slider.setSnapToTicks(true);
		slider.isSnapToTicks();
	}
}
