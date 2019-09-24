package Composite;

public class Text extends Graphic{

	@Override
	public void draw() {
		System.out.println("Eu sou um texto!");
	}

	@Override
	public Boolean adicionar(Graphic graphic) {
		return false;
	}

	@Override
	public Boolean remover(Graphic graphic) {
		return false;
	}

	@Override
	public Graphic getChild(Integer child) {
		return null;
	}

}
