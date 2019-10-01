package FamilyTree;

public class Menu extends BaseMenu {
	private AddMemberMenu AddMemberMenu;
	private SetMemberAsChildMenu SetMemberAsChildMenu;
	private DeleteMemberMenu RemoveMemberMenu;
	
	public Menu(FamilyTreeProgram familyTreeProgram) {
		super(familyTreeProgram);
		AddMemberMenu = new AddMemberMenu(familyTreeProgram);
		SetMemberAsChildMenu = new SetMemberAsChildMenu(familyTreeProgram);
		RemoveMemberMenu = new DeleteMemberMenu(familyTreeProgram);
	}
	
	public void DisplayMainMenu() {
		FTUtil.PrintEmptyText();
		FTUtil.Print("Menu principal:");
		FTUtil.Print("1 - Visualizar a familia.");
		FTUtil.Print("2 - Adicionar um membro da familia.");
		FTUtil.Print("3 - Adicionar um membro da familia, como filho de outro.");
		FTUtil.Print("4 - Remover um membro da familia.");
		FTUtil.Print("8 - Apagar a familia atual, e iniciar uma nova.");
		FTUtil.Print("9 - Para sair");
		
		SelectMainMenuOption(FTUtil.RequestInt("Escolha uma opção digitando um número: "));
	}
	
	public void SelectMainMenuOption(Integer value)
	{
		switch(value)
		{
		case 1:
			FTUtil.PrintEmptyText();
			GetFamily().displayChildren(1);
			break;
		case 2:
			FTUtil.PrintEmptyText();
			AddMemberMenu.DisplayAddMemberOption();
			break;
		case 3:
			FTUtil.PrintEmptyText();
			SetMemberAsChildMenu.DisplaySetMemberAsChildOption();
			break;
		case 4:
			FTUtil.PrintEmptyText();
			RemoveMemberMenu.DisplayRemoveMemberOption();
			break;
		case 8:
			InitFamily();
			break;
		case 9:
			EndProgram();
			break;
		default:
			WrongOption();
			DisplayMainMenu();
			break;
		}	
	}
	
	public void InitFamily() {
		FTUtil.PrintEmptyText();
		
		if (GetFamily() != null) {
			FTUtil.Print("Familia apagada com sucesso!");
		}
		
		String familyName = FTUtil.RequestString("Digite o nome da árvore genealógica: ");
		SetFamily(new Family(familyName));
		
		FTUtil.Print("Familia criada com sucesso!");
	}
}
