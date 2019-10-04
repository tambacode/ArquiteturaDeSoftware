package FamilyTree;
import java.util.ArrayList;

public abstract class FamilyMember {
	protected String Name;
	protected String Gender;
	protected ArrayList<FamilyMember> Children;
	
	public FamilyMember(String name) {
		this.Children = new ArrayList<FamilyMember>();
		this.Name = name;
	}

	public String getName() {
		return this.Name;
	}
	
	public String getGender() {
		return this.Gender;
	}
	
	public ArrayList<FamilyMember> getChildren() {
		return Children;
	}	 
	
	public void addChild(FamilyMember member) {
		Children.add(member);
	}
	
	public void deleteChild(FamilyMember member) {
		if (Children.contains(member)) {
			Children.remove(member);
			member = null;
			
			return;
		}
		
		for (FamilyMember person : Children) {
			person.deleteChild(member);
		}
	}
	
	public void removeChild(FamilyMember member) {
		if (Children.contains(member)) {
			Children.remove(member);
		}
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
	
	public void ClearChildrenNullRefs() {
		Object[] c = Children.toArray();
		for (int i = c.length-1; i >= 0; i--) {
			if (c[i] == null) {
				Children.remove(i);
			}
		}
	}
	
	public FamilyMember RequestFamilyMember(String text) {
		String familyMemberName = FTUtil.RequestString(text);
		
		FamilyMember memberFound = SearchFamilyMember(familyMemberName);
		
		if (memberFound == null || memberFound.getName() == "") {
			Boolean answer = FTUtil.RequestOption("Nenhum membro encontrado com esse nome, Deseja tentar novamente? (S ou N)", "S", "N");
			
			if (answer) {
				memberFound = RequestFamilyMember(text);
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
				break;
			}
			
			if (person.Name.toLowerCase().equals(familyMemberName.toLowerCase())) {
				memberFound = person;
				break;
			} else {
				memberFound = person.SearchFamilyMember(familyMemberName);	
			}
		}
		
		return memberFound;
	}
}
