package Composite;

import java.util.ArrayList;
import java.util.List;

public class Picture extends Graphic{
	
	public List<Graphic> childs = new ArrayList<>();
	
	@Override
	public void draw() {
		System.out.println("Eu sou uma imagem!");
		
		if(childs.size() > 0)  {
		System.out.println("Estes sao meus filhos: ");
		
		for (int i = 0; i < childs.size(); i++) {
			childs.get(i).draw();
		}
		
		}
	}

	@Override
	public Boolean adicionar(Graphic graphic) {
		childs.add(graphic);
		return true;
	}

	@Override
	public Boolean remover(Graphic graphic) {
		childs.remove(graphic);
		return true;
	}

	@Override
	public Graphic getChild(Integer child) {
		return childs.get(child);
	}

}
