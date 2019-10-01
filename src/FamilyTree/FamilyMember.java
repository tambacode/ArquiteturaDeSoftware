package FamilyTree;
import java.util.ArrayList;

public abstract class FamilyMember {
	protected String Name;
	protected ArrayList<FamilyMember> Children;
	
	public FamilyMember(String name) {
		this.Children = new ArrayList<FamilyMember>();
		this.Name = name;
	}

	public String getName() {
		return this.Name;
	}
	
	public ArrayList<FamilyMember> getChildren() {
		return Children;
	}	 
	
	public void addChild(FamilyMember person) {
		Children.add(person);
	}
	
	public void removeChild(FamilyMember person) {
		Children.remove(person);
	}
	
	public void displayChildren(Integer generation) {
		ClearChildrenNullRefs();
		FTUtil.PrintTab(generation);
		FTUtil.Print(getName());
				
		for (FamilyMember person : Children) {
			if (person == null) {
				
			}
			person.displayChildren(generation + 1);
		}
	}
	
	private void ClearChildrenNullRefs() {
		Object[] c = Children.toArray();
		for (int i = c.length-1; i >= 0; i--) {
			if (c[i] == null) {
				Children.remove(i);
			}
		}
	}
	
	public FamilyMember RequestFamilyMember() {
		String familyMemberName = FTUtil.RequestString("Digite o nome do membro a ser procurado: ");
		
		FamilyMember memberFound = SearchFamilyMember(familyMemberName);
		
		if (memberFound == null || memberFound.getName() == "") {
			Boolean answer = FTUtil.RequestOption("Nenhum membro encontrado com esse nome, Deseja tentar novamente? (S ou N)", "S", "N");
			
			if (answer) {
				memberFound = RequestFamilyMember();
			} else {
				return null;
			}
		}
		
		return memberFound;
	}
	
	public FamilyMember SearchFamilyMember(String familyMemberName) {
		FamilyMember memberFound = null;
		
		for (FamilyMember person : Children) {
			if (memberFound != null)
			{
				return person;
			}
			
			FTUtil.Print(person.Name.toLowerCase());
			FTUtil.Print(familyMemberName.toLowerCase());
			
			if (person.Name.toLowerCase().equals(familyMemberName.toLowerCase())) {
				return person;
			} else {
				memberFound = person.SearchFamilyMember(familyMemberName);	
			}
		}
		
		return memberFound;
	}
}
