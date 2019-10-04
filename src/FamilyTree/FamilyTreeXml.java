package FamilyTree;

import java.util.ArrayList;

public class FamilyTreeXml implements FamilyTreeAdapter {
	private FamilyMember FamilyMember;
	
	public FamilyTreeXml(FamilyMember familyMember) {
		this.FamilyMember = familyMember;
	}
	
	@Override
	public void Export(String file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FamilyMember Import(String file) {
		// TODO Auto-generated method stub
		return null;
	}

}
