package FamilyTree;

public class Woman extends FamilyMember {
	
	public Woman(String name) {
		super(name);
		Gender = "Woman";
	}
	
	@Override
	public String getName() {
		return "( " + this.Name + " )";
	}
}
