package FamilyTree;

public class DeleteMemberMenu extends BaseMenu {
	public DeleteMemberMenu(FamilyTreeProgram familyTreeProgram) {
		super(familyTreeProgram);
	}
	
	@Override
	public void DisplayMenu() {
		FamilyMember member = GetFamily().RequestFamilyMember("Digite o nome do membro a ser deletado: ");
		
		if (member != null) {
			GetFamily().deleteChild(member);
			GetFamily().ClearChildrenNullRefs();

			FTUtil.Print("Membro removido com sucesso!");
		}
	}
}
