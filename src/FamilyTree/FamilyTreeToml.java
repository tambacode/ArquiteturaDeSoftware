package FamilyTree;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;

public class FamilyTreeToml implements FamilyTreeAdapter {
	private FamilyMember FamilyTree;
	
	public FamilyTreeToml(FamilyMember familyMember) {
		this.FamilyTree = familyMember;
	}
	
	@Override
	public String GetFileExtension() {
		return ".toml";
	}

	@Override
	public FamilyMember Import(String filePath) {
		FamilyTree = null;
		Toml toml = new Toml();
		
		try (InputStream inputStream = new FileInputStream(filePath);) {
			Map<String, Object> myObj = toml.read(inputStream).toMap();
			
			FamilyTree = ImportChildrenIterator(myObj, FamilyTree);
		}
		catch (Exception e) { e.printStackTrace(); }
		
		return FamilyTree;
	}
	
	private FamilyMember ImportChildrenIterator(Map<String, Object> myObj, FamilyMember familyMember) {
		String name = (String)myObj.get("Name");
		String gender = (String)myObj.get("Gender");
		ArrayList children = (ArrayList)myObj.get("Children");
		
		FamilyMember childMember = GetFamilyMember(FamilyTree, name, gender);
		
		if (familyMember == null) {
			familyMember = childMember;
			FamilyTree = familyMember;
		} else {
			familyMember.addChild(childMember);
		}
		
		for (int i = 0; i < children.size(); i++) {
			ImportChildrenIterator((Map)children.get(i), childMember);
		}
		
		return familyMember;
	}

	private FamilyMember GetFamilyMember(FamilyMember familyTree, String memberName, String memberGender) {
		FamilyMember foundFamilyMember = null;
		
		if (familyTree != null) {
			//familyTree.displayChildren(0);
			foundFamilyMember = familyTree.SearchFamilyMember(memberName);	
		}

		// If member was found on the tree return it
		if (foundFamilyMember != null) {
			return foundFamilyMember;
		} else {
			return GetNewFamilyMemberByGender(null, memberName, memberGender);
		}
	}

	private FamilyMember GetNewFamilyMemberByGender(FamilyMember familyMember, String name, String gender) {
		switch (gender) {
		case "Family":
			return new Family(name);
		case "Man":
			return new Man(name);
		case "Woman":
			return new Woman(name);
	}
	
	return new Woman(name);	
	}

	@Override
	public void Export(String filePath) {
		try {
			TomlWriter tomlWriter = new TomlWriter();
	    	tomlWriter.write(FamilyTree, new File(filePath));
	    } catch (IOException e) {
			e.printStackTrace();
		}
	}
}
