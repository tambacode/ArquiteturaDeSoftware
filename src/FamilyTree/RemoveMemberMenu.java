package FamilyTree;

public class RemoveMemberMenu extends BaseMenu {
	public RemoveMemberMenu(FamilyTreeProgram familyTreeProgram) {
		super(familyTreeProgram);
	}
	
	public void DisplayRemoveMemberOption() {
		FamilyMember member = GetFamily().RequestFamilyMember("Digite o nome do membro a ser removido: ");
		
		if (member != null) {
			GetFamily().removeChild(member);
			GetFamily().ClearChildrenNullRefs();

			FTUtil.Print("Membro removido com sucesso!");
		}
	}
}
