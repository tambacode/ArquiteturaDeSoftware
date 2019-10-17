package FamilyTree;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;


public class FamilyTreeToml implements FamilyTreeAdapter {
	private FamilyMember FamilyTree;
	private int MemberIndex = 0;
	
	public FamilyTreeToml(FamilyMember familyMember) {
		this.FamilyTree = familyMember;
	}

	@Override
	public FamilyMember Import(String filePath) {
		//  /Users/jbalves/Documents/arvore/arvore.toml
		FamilyTree = null;
		Toml toml = new Toml();
		
		try (InputStream inputStream = new FileInputStream(filePath);) {
			//System.out.println("try ");
			Map<String, Object> myObj = toml.read(inputStream).toMap();
			
			FamilyTree = ImportChildrenIterator(myObj, FamilyTree);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return FamilyTree;
	}
	
	private FamilyMember ImportChildrenIterator(Map<String, Object> myObj, FamilyMember familyMember) {
		for (Map.Entry<String, Object> entry : myObj.entrySet()) {
			Object currentObj = entry.getValue();
			
			if (currentObj instanceof LinkedHashMap) {
				Map<String, Object> currentMap = (LinkedHashMap)currentObj;
				
	    		// Create a new member
	    		String name = "";
	    		FamilyMember childMember = null;
	    		
	    		for (Map.Entry<String, Object> currentChildEntry : currentMap.entrySet()) {
	    			
	    			switch (currentChildEntry.getKey()) {
					case "Name":
						name = (String)currentChildEntry.getValue();
						break;
					case "Gender":
						childMember = GetFamilyMember(FamilyTree, name, (String)currentChildEntry.getValue());
						
						if (familyMember == null) {
		        			//Init family
		        			familyMember = childMember;
		        			// Add initial object to the tree so we can search for duplicate children
		        			FamilyTree = familyMember;
		        		} else {
		        			// Add a member as another one child
		                	familyMember.addChild(childMember);
		        		}
						break;
					case "Children":
						ImportChildrenIterator((LinkedHashMap)currentChildEntry.getValue(), childMember);
						break;
					}
	    		}
	    	}
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
		MemberIndex = 0;
		FTUtil.Print("YAML Export called with sucess");
		
		TomlWriter tomlWriter = new TomlWriter();
		StringWriter writer = new StringWriter();
	    Map<String, Object> data = new LinkedHashMap<String, Object>();
	    
	    data = ExportChildrenIterator(data, FamilyTree);
	    
	    String tomlString = tomlWriter.write(FamilyTree);
	    tomlString = tomlWriter.write(data);
	    
	    try {
			tomlWriter.write(FamilyTree, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

	private Map<String, Object> ExportChildrenIterator(Map<String, Object> my_obj, FamilyMember currentMember) {
		MemberIndex++;
		String key = Integer.toString(MemberIndex);
		
		ArrayList<FamilyMember> children = currentMember.getChildren();
		
		my_obj.put(key, new LinkedHashMap<String, Object>());
		
		LinkedHashMap selfObj = (LinkedHashMap)my_obj.get(key);
		
		selfObj.put("Name", currentMember.Name);
		selfObj.put("Gender", currentMember.getGender());
		
		if (children.size() > 0) {
			selfObj.put("Children", new LinkedHashMap<String, Object>());			
			LinkedHashMap childrenObj = (LinkedHashMap)selfObj.get("Children");
			
			for (int i = 0; i < children.size(); i++) {
				ExportChildrenIterator(childrenObj, children.get(i));
			}
		}
		
		return my_obj;
	}


}
