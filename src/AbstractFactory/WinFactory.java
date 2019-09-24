package AbstractFactory;

public class WinFactory extends GUIFactory {
	
	public Button CreateButton() {
		WinButton winbutton = new WinButton();
		return winbutton;
	}

}
