package FamilyTree;

import java.util.ArrayList;

public class FamilyTreeToml implements FamilyTreeAdapter {
	private FamilyMember FamilyMember;
	
	public FamilyTreeToml(FamilyMember familyMember) {
		this.FamilyMember = familyMember;
	}
	
	@Override
	public void Export(String file) {
		FTUtil.Print("TOML Export called with sucess");
	}

	@Override
	public FamilyMember Import(String file) {
		FTUtil.Print("TOML Import called with sucess");
		return null;
	}
}
