package FamilyTree;

import java.util.ArrayList;

public class FamilyTreeYaml implements FamilyTreeAdapter {
	private FamilyMember FamilyMember;
	
	public FamilyTreeYaml(FamilyMember familyMember) {
		this.FamilyMember = familyMember;
	}
	
	@Override
	public void Export(String file) {
		FTUtil.Print("YAML Export called with sucess");
	}

	@Override
	public ArrayList<FamilyMember> Import(String file) {
		FTUtil.Print("YAML Import called with sucess");
		return null;
	}
}
