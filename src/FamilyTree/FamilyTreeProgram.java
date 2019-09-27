package FamilyTree;

public class FamilyTreeProgram {
	private FamilyMember Family;
	private Menu menu;
	
	public FamilyTreeProgram() {
		menu = new Menu();
	}
	
	public void DoProgramLoop() {
		InitFamily();
		
		while(true) {
			SelectMainMenuOption(menu.DisplayMainMenu());	
		}
	}
	
	public void SelectMainMenuOption(Integer value)
	{
		switch(value)
		{
		case 0:
			InitFamily();
			break;
		case 1:
			FTUtil.PrintEmptyText();
			Family.displayChildren();
			break;
		case 9:
			EndProgram();
		default:
			WrongOption();
			menu.DisplayMainMenu();
		}	
	}
	
	private void InitFamily() {
		FTUtil.PrintEmptyText();
		
		if (Family != null) {
			FTUtil.Print("Familia apagada com sucesso!");
		}
		
		String familyName = FTUtil.RequestString("Digite o nome da árvore genealógica: ");
		Family = new Family(familyName);
		
		FTUtil.Print("Familia criada com sucesso!");
	}
	
	private void EndProgram() {
		FTUtil.PrintEmptyText();
		FTUtil.Print("Programa finalizado!");
		System.exit(0);
	}
	
	private void WrongOption()
	{
		FTUtil.PrintEmptyText();
		FTUtil.Print("Opção incorreta, tente novamente!");	
	}
}
