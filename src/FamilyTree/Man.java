package FamilyTree;

public class Man extends FamilyMember {
	
	public Man(String name) {
		super(name);
		Gender = "Man";
	}
	
	@Override
	public String getName() {
		return "[ " + this.Name + " ]";
	}
}
