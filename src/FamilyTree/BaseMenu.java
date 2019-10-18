package FamilyTree;

public class BaseMenu {
	protected FamilyTreeProgram FamilyTreeProgram;
	
	public BaseMenu(FamilyTreeProgram familyTreeProgram) {
		this.FamilyTreeProgram = familyTreeProgram;
	}
	
	public void DisplayMenu() {}
	
	protected void EndProgram() {
		FTUtil.PrintEmptyText();
		FTUtil.Print("Programa finalizado!");
		System.exit(0);
	}
	
	protected void WrongOption()
	{
		FTUtil.PrintEmptyText();
		FTUtil.Print("Opcao incorreta, tente novamente!");	
	}
	
	protected FamilyMember GetFamily() {
		return FamilyTreeProgram.Family;
	}
	
	protected void SetFamily(FamilyMember family) {
		FamilyTreeProgram.Family = family;
	}
}
