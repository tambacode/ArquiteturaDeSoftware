package Composite;

public class Main {

	public static void main(String[] args) {
		Picture picture = new Picture();
		Picture picture2 = new Picture();
		
		picture.adicionar(new Line());
		picture.adicionar(new Rectangle());
		picture.adicionar(picture2);
		
		picture2.adicionar(new Line());
		
		picture.draw();
		
	}

}
