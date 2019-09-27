package FamilyTree;
import java.util.ArrayList;

public abstract class FamilyMember {
	protected String Name;
	protected ArrayList<FamilyMember> Children;
	
	public FamilyMember(String name) {
		this.Children = new ArrayList<FamilyMember>();
		this.Name = name;
	}

	public String getName() {
		return this.Name;
	}
	
	public ArrayList<FamilyMember> getChildren() {
		return Children;
	}	 
	
	public void addChild(FamilyMember person) {
		Children.add(person);
	}
	
	public void removeChild(FamilyMember person) {
		Children.remove(person);
	}
	
	public void displayChildren() {
		if (Children.size() > 0 ) {
			FTUtil.Print(getName() + " e estes são meus decendentes");	
		} else {
			FTUtil.Print(getName() + " e não tenho decendentes");
		}
				
		for (FamilyMember person : Children) {
			displayChildren();
		}
	}
}
