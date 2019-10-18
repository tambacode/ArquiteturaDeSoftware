package FamilyTree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.yaml.snakeyaml.Yaml;

public class FamilyTreeYaml implements FamilyTreeAdapter {
	private FamilyMember FamilyTree;
	private int MemberIndex = 0;
	
	public FamilyTreeYaml(FamilyMember familyMember) {
		this.FamilyTree = familyMember;
	}
	
	@Override
	public String GetFileExtension() {
		return ".yaml";
	}
	
	@Override
	public FamilyMember Import(String filePath) {
		FamilyTree = null;
		Yaml yaml = new Yaml();
		
		try (InputStream inputStream = new FileInputStream(filePath);) {
			Map<String, Object> myObj = yaml.load(inputStream);

			FamilyTree = ImportChildrenIterator(myObj, FamilyTree);
		}
		catch (FileNotFoundException e) { e.printStackTrace(); }
		catch (IOException e1) { e1.printStackTrace(); }
		
		return FamilyTree;
	}
	
	private FamilyMember ImportChildrenIterator(Map<String, Object> myObj, FamilyMember familyMember)
	{
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
		
		Yaml yaml = new Yaml();
	    StringWriter writer = new StringWriter();
	    Map<String, Object> data = new LinkedHashMap<String, Object>();
	    
	    data = ExportChildrenIterator(data, FamilyTree);
    	yaml.dump(data, writer);
	    
	    try (FileWriter file = new FileWriter(filePath)) {
	    	file.write(writer.toString());
        } catch (IOException e) {
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
