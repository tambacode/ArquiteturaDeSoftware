package Composite;

public class Line extends Graphic{

	@Override
	public void draw() {
		System.out.println("Eu sou uma linha!");
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
