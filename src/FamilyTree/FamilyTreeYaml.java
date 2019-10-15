package FamilyTree;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.yaml.snakeyaml.Yaml;

public class FamilyTreeYaml implements FamilyTreeAdapter {
	private FamilyMember FamilyTree;
	private int MemberIndex = 0;
	
	public FamilyTreeYaml(FamilyMember familyMember) {
		this.FamilyTree = familyMember;
	}
	
	@Override
	public void Export(String filePath) {
		MemberIndex = 0;
		FTUtil.Print("YAML Export called with sucess");
		
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

	@Override
	public FamilyMember Import(String filePath) {
		FTUtil.Print("YAML Import called with sucess");
		return null;
	}
}
