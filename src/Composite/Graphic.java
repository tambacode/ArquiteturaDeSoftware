package Composite;

public abstract class Graphic {
	public abstract void draw();
	public abstract Boolean adicionar(Graphic graphic);
	public abstract Boolean remover(Graphic graphic);
	public abstract Graphic getChild(Integer child);
}
