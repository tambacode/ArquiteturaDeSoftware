package FamilyTree;

public class AddMemberMenu extends BaseMenu {
	public AddMemberMenu(FamilyTreeProgram familyTreeProgram) {
		super(familyTreeProgram);
	}
	
	public void DisplayAddMemberMenu() {
		FTUtil.Print("Menu para adicionar membro da familia:");
		FTUtil.Print("1 - Adicionar novo membro.");
		FTUtil.Print("2 - Adicionar membro existente como filho de outro.");
		FTUtil.Print("9 - Retornar");
		
		Integer selected = FTUtil.RequestInt("Escolha uma opção digitando um número: ");
		
		switch(selected)
		{
		case 1:
			DisplayAddMemberOption();
			break;
		case 2:
			FTUtil.PrintEmptyText();
			GetFamily().displayChildren(1);
			break;
		}		
	}
	
	private void DisplayAddMemberOption() {
		FTUtil.PrintEmptyText();
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
			FamilyMember parent = GetFamily().RequestFamilyMember();
			
			if (parent != null) {
				FTUtil.Print(familyMember.getName());
				parent.addChild(familyMember);

				FTUtil.Print("Membro adicionado como filho com sucesso!");
				AddMemberAsChild(familyMember, true);	
			}
		} else {
			if (!alreadyAChild) {
				GetFamily().addChild(familyMember);	
			}
		}
	}
}
