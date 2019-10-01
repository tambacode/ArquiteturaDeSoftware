package FamilyTree;

public class Man extends FamilyMember {
	
	public Man(String name) {
		super(name);
	}
	
	@Override
	public String getName() {
		return "[ " + this.Name + " ]";
	}
}
