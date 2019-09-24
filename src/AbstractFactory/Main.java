package AbstractFactory;

public class Main {
	
	public static void PrintButton(GUIFactory factory) {
		Button winButton = factory.CreateButton();
		winButton.Paint();
	}
	
	public static void main(String[] args) {
		PrintButton(new WinFactory());
		PrintButton(new OSXFactory());
	}
	
}
