package FamilyTree;

public class Family extends FamilyMember {
	
	public Family(String name) {
		super(name);
		Gender = "Family";
	}
	
	@Override
	public String getName() {
		return "{ " + this.Name + " }";
	}
}
