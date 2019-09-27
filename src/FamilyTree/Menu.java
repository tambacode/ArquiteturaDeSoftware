package FamilyTree;

public class Menu {
	public Integer DisplayMainMenu() {
		FTUtil.PrintEmptyText();
		FTUtil.Print("Menu principal:");
		FTUtil.Print("0 - Apagar a familia atual, e iniciar uma nova.");
		FTUtil.Print("1 - Visualizar a familia.");
		FTUtil.Print("2 - Adicionar um membro da familia.");
		FTUtil.Print("3 - Remover um membro da familia.");
		FTUtil.Print("4 - Adicionar um membro da familia, como filho de outro.");
		FTUtil.Print("9 - Para sair");
		
		return FTUtil.RequestInt("Escolha uma opção digitando um número: ");
	}
	
	
}
