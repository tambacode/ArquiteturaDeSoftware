package FamilyTree;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FamilyTreeJson implements FamilyTreeAdapter {
	private FamilyMember FamilyTree;
	
	public FamilyTreeJson(FamilyMember familyMember) {
		this.FamilyTree = familyMember;
	}
	
	@Override
	public void Export(String filePath) {
		JSONObject my_obj = new JSONObject();

		try {
			ExportChildrenIterator(my_obj, FamilyTree);
		} catch (JSONException e1) { e1.printStackTrace(); }
		
		try (FileWriter file = new FileWriter(filePath)) {
			file.write(my_obj.toString());
        } catch (IOException e) { }
	}
	
	private void ExportChildrenIterator(JSONObject my_obj, FamilyMember currentMember) throws JSONException {
		String key = currentMember.Name;;
		ArrayList<FamilyMember> children = currentMember.getChildren();
		
		my_obj.put(key, new JSONObject());
		JSONObject selfObj = (JSONObject)my_obj.get(key);
		selfObj.put("Gender", currentMember.getGender());
		
		if (children.size() > 0) {
			selfObj.put("Children", new JSONObject());			
			JSONObject childrenObj = (JSONObject)selfObj.get("Children");
			
			for (int i = 0; i < children.size(); i++) {
				ExportChildrenIterator(childrenObj, children.get(i));
			}	
		}
	}
	
	@Override
	public FamilyMember Import(String filePath) {
		FamilyTree = null;
		
		JSONParser parser = new JSONParser();
		
		try (FileReader reader = new FileReader(filePath)) {
			Object file = parser.parse(reader);
			//System.out.println(file.toString());
			
			JSONObject myObj = new JSONObject(file.toString());
			
			FamilyTree = ImportChildrenIterator(myObj, FamilyTree);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return FamilyTree;
	}
	
	private FamilyMember ImportChildrenIterator(JSONObject myObj, FamilyMember familyMember) throws JSONException
	{
		// Loop in all keys from the obj
		Iterator<String> iterator = myObj.keys();
        while (iterator.hasNext()) {
        	String name = iterator.next();
        	FamilyMember childMember;

        	try {
        		// Get the member as an obj
        		JSONObject currentObj = myObj.getJSONObject(name);        		
        		
        		// Create a new member
        		
        		childMember = GetFamilyMember(FamilyTree, name, currentObj.getString("Gender"));
        		
        		if (familyMember == null) {
        			// Init family
        			familyMember = childMember;
        			// Add initial object to the tree so we can search for duplicate children
        			FamilyTree = familyMember;
        		} else {
        			// Add a member as another one child
                	familyMember.addChild(childMember);
        		}
            	
        		if (currentObj.has("Children"))
        		{
        			// LOOP ON CHILDREN
                	JSONObject currentObjChildren = currentObj.getJSONObject("Children");
            		Iterator<String> childrenIterator = currentObjChildren.keys();

            		int a = 0;
            		for (Iterator i = childrenIterator; i.hasNext(); ) 
            		{
            		    a++;
            		    if (a > 1) { break; }
            		    
                       	String childrenKey = (String)i.next();
                       	ImportChildrenIterator(currentObjChildren, childMember);
            		}	
        		}
			} catch (Exception e) {
				e.printStackTrace();
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
}
