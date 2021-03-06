package FamilyTree;

public class ExportImportMenu extends BaseMenu {
	public ExportImportMenu(FamilyTreeProgram familyTreeProgram) {
		super(familyTreeProgram);
	}
	
	@Override
	public void DisplayMenu() {
		FTUtil.PrintEmptyText();
		FTUtil.Print("Escolha uma opcao:");
		FTUtil.Print("1 - Importar familia.");
		FTUtil.Print("2 - Exportar familia.");
		FTUtil.Print("3 - Retornar.");
		
		SelectMenuOption(FTUtil.RequestIntWithLimit(("Escolha uma opcao: "), 1, 3));
		//SelectMenuOption(1);
	}
	
	private void SelectMenuOption(Integer value)
	{
		if (value < 3) {
			DisplayFileTypeMenu(value == 2);
		}
	}
	
	private void DisplayFileTypeMenu(Boolean export) {
		FTUtil.PrintEmptyText();
		FTUtil.Print("Escolha o tipo de arquivo desejado:");
		FTUtil.Print("1 - JSON");
		FTUtil.Print("2 - XML");
		FTUtil.Print("3 - YAML");
		FTUtil.Print("4 - TOML");
		FTUtil.Print("5 - Retornar");
		
		SelectFileTypeMenuOption(export, FTUtil.RequestIntWithLimit("Digite uma opcao: ", 1, 5));
		//SelectFileTypeMenuOption(export, 4);
	}
	
	private void SelectFileTypeMenuOption(Boolean export, Integer value)
	{
		if (value < 5) {
			FamilyTreeAdapter familyAdapter = GetFamilyAdapter(value, GetFamily());
			DisplayFilePathMenu(export, familyAdapter);
		} else {
			DisplayMenu();	
		}
	}
	
	private FamilyTreeAdapter GetFamilyAdapter(Integer value, FamilyMember familyMember) {
		switch (value) {
			case 1:
				return new FamilyTreeJson(familyMember);
			case 2:
				return new FamilyTreeXml(familyMember);
			case 3:
				return new FamilyTreeYaml(familyMember);
			case 4:
				return new FamilyTreeToml(familyMember);
		}
		
		return null;
	}
	
	private void DisplayFilePathMenu(Boolean export, FamilyTreeAdapter familyAdapter) {
		FTUtil.PrintEmptyText();
		try {
			String file = FTUtil.RequestString("Digite o caminho para o arquivo (Ex: 'C:\\PastaPreferida\\nomeDoArquivo'): ");
			file = file + familyAdapter.GetFileExtension();
			//String file = "C:\\Nano\\customer.toml";

			if (export) {
				familyAdapter.Export(file);
				FTUtil.Print("Arquivo exportado com sucesso!");
			} else {
				SetFamily(familyAdapter.Import(file));
				FTUtil.Print("Arquivo importado com sucesso!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			FTUtil.Print("Nao foi possivel executar a acao. Verifique o caminho do arquivo.");
			DisplayFilePathMenu(export, familyAdapter);
		}
	}
}
