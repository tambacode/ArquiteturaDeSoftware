package FamilyTree;

import java.util.ArrayList;

public interface FamilyTreeAdapter {
	public void Export(String file);
	public ArrayList<FamilyMember> Import(String file);
}
