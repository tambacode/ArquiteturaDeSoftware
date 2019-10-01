package FamilyTree;

public class AddMemberMenu extends BaseMenu {
	public AddMemberMenu(FamilyTreeProgram familyTreeProgram) {
		super(familyTreeProgram);
	}
	
	public void DisplayAddMemberOption() {
		Boolean answer = FTUtil.RequestOption("Digite o genero do novo membro (F ou M): ", "M", "F");
		String name = FTUtil.RequestString("Digite o nome do novo membro: ");
		FamilyMember memberAdded;
		
		if (answer) {
			memberAdded = new Man(name);
		} else {
			memberAdded = new Woman(name);
		}
		
		FTUtil.Print("Membro criado com sucesso!");
		AddMemberAsChild(memberAdded, false);
	}
	
	private void AddMemberAsChild(FamilyMember familyMember, Boolean alreadyAChild) {
		Boolean answer = FTUtil.RequestOption("Adicionar esse membro como filho de outro antecedente? (S ou N): ", "S", "N");
		
		if (answer) {
			FamilyMember parent = GetFamily().RequestFamilyMember("Digite o nome do membro a ser procurado: ");
			
			if (parent != null) {
				parent.addChild(familyMember);

				FTUtil.Print("Membro adicionado como filho com sucesso!");
				AddMemberAsChild(familyMember, true);	
				
				alreadyAChild = true;
			}
		}
		
		if (!alreadyAChild) {
			GetFamily().addChild(familyMember);	
		}
	}
}
