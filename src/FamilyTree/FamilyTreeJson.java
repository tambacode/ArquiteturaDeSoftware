package FamilyTree;

import java.util.ArrayList;

public class FamilyTreeJson implements FamilyTreeAdapter {
	private FamilyMember FamilyMember;
	
	public FamilyTreeJson(FamilyMember familyMember) {
		this.FamilyMember = familyMember;
	}
	
	@Override
	public void Export(String file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<FamilyMember> Import(String file) {
		// TODO Auto-generated method stub
		return null;
	}

}
