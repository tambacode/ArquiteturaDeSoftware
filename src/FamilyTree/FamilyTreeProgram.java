package FamilyTree;

public class FamilyTreeProgram {
	public FamilyMember Family;
	private Menu menu;
	
	public FamilyTreeProgram() {
		menu = new Menu(this);
	}
	
	public void DoProgramLoop() {
		menu.InitFamily();
		AddRandomChildren();
		
		while(true) {
			menu.DisplayMenu();	
		}
	}
	
	private void AddRandomChildren() {
		Man m1 = new Man("John");
		Woman w1 = new Woman("Joana");

		Man m2 = new Man("Bruce");
		Woman w2 = new Woman("Anna");
		Man m3 = new Man("Jake");
		
		Family.addChild(m1);
		Family.addChild(w1);
		m1.addChild(m2);
		m1.addChild(w2);
		m1.addChild(m3);
		w1.addChild(m2);
		w1.addChild(w2);
	}
}
