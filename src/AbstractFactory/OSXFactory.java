package AbstractFactory;

public class OSXFactory extends GUIFactory{

	public Button CreateButton() {
		OSXButton osxbutton = new OSXButton();
		return osxbutton;
	}

}
