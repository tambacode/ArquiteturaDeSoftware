package FamilyTree;

public class Menu extends BaseMenu {
	private BaseMenu AddMemberMenu;
	private BaseMenu SetMemberAsChildMenu;
	private BaseMenu RemoveMemberMenu;
	private BaseMenu ExportImportMenu;
	
	public Menu(FamilyTreeProgram familyTreeProgram) {
		super(familyTreeProgram);
		AddMemberMenu = new AddMemberMenu(familyTreeProgram);
		SetMemberAsChildMenu = new SetMemberAsChildMenu(familyTreeProgram);
		RemoveMemberMenu = new DeleteMemberMenu(familyTreeProgram);
		ExportImportMenu = new ExportImportMenu(familyTreeProgram);
	}
	
	@Override
	public void DisplayMenu() {
		FTUtil.PrintEmptyText();
		FTUtil.Print("Menu principal:");
		FTUtil.Print("1 - Visualizar a familia.");
		FTUtil.Print("2 - Adicionar um membro da familia.");
		FTUtil.Print("3 - Adicionar um membro da familia, como filho de outro.");
		FTUtil.Print("4 - Remover um membro da familia.");
		FTUtil.Print("5 - Importar/Exportar familía.");
		FTUtil.Print("8 - Apagar a familia atual, e iniciar uma nova.");
		FTUtil.Print("9 - Para sair");
		
		SelectMainMenuOption(FTUtil.RequestInt("Escolha uma opção digitando um número: "));
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
	
	private void SelectMainMenuOption(Integer value)
	{
		switch(value)
		{
		case 1:
			GetFamily().displayChildren(1);
			break;
		case 2:
			DisplayBaseMenu(AddMemberMenu);
			break;
		case 3:
			DisplayBaseMenu(SetMemberAsChildMenu);
			break;
		case 4:
			DisplayBaseMenu(RemoveMemberMenu);
			break;
		case 5:
			DisplayBaseMenu(ExportImportMenu);
			break;
		case 8:
			InitFamily();
			break;
		case 9:
			EndProgram();
			break;
		default:
			WrongOption();
			DisplayMenu();
			break;
		}	
	}
	
	private void DisplayBaseMenu(BaseMenu menu) {
		FTUtil.PrintEmptyText();
		menu.DisplayMenu();
	}
}
