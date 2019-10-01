package FamilyTree;

public class SetMemberAsChildMenu extends BaseMenu {
	public SetMemberAsChildMenu(FamilyTreeProgram familyTreeProgram) {
		super(familyTreeProgram);
	}
	
	public void DisplaySetMemberAsChildOption() {
		FamilyMember member = GetFamily().RequestFamilyMember("Digite o nome do membro a ser adicionado como filho: ");
		
		if (member != null) {
			DisplaySetMemberAsChildParentOption(member);
		}
	}
	
	private void DisplaySetMemberAsChildParentOption(FamilyMember childMember) {
		FamilyMember member = GetFamily().RequestFamilyMember("Digite o nome do antecedente: ");
		
		if (member != null) {
			GetFamily().removeChild(childMember);
			
			member.addChild(childMember);
			FTUtil.Print("Membro adicionado como filho com sucesso!");
			
			if (FTUtil.RequestOption("Adicionar como filho de outro membro? (S ou N)", "S", "N")) {
				DisplaySetMemberAsChildParentOption(childMember);
			}
		}
	}
}

