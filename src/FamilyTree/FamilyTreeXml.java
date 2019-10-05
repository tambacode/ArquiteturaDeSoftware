package FamilyTree;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FamilyTreeXml implements FamilyTreeAdapter {
	private FamilyMember FamilyTree;
	
	public FamilyTreeXml(FamilyMember familyMember) {
		this.FamilyTree = familyMember;
	}
	
	@Override
	public void Export(String filePath) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			
			ExportChildrenIterator(doc, null, FamilyTree);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath));
			
			transformer.transform(source, result);
		}
		catch (ParserConfigurationException e1) { e1.printStackTrace(); } 
		catch (TransformerConfigurationException e) { e.printStackTrace(); }
		catch (TransformerException e) { e.printStackTrace(); }
	}
	
	private void ExportChildrenIterator(Document doc, Element element, FamilyMember currentMember) {
		ArrayList<FamilyMember> children = currentMember.getChildren();
		
		// Create element
		Element currentElement = doc.createElement("Element");

		// Set parent
		if (element == null) {
			doc.appendChild(currentElement);
		} else {
			element.appendChild(currentElement);
		}

		// Set Name Element
		Element nameElement = doc.createElement("Name");
		nameElement.appendChild(doc.createTextNode(currentMember.Name));
		currentElement.appendChild(nameElement);
		
		// Set Gender Element
		Element genderElement = doc.createElement("Gender");
		genderElement.appendChild(doc.createTextNode(currentMember.getGender()));
		currentElement.appendChild(genderElement);
		
		if (children.size() > 0) {
			// Set Children Element
			Element childrenElement = doc.createElement("Children");
			currentElement.appendChild(childrenElement);
			
			for (int i = 0; i < children.size(); i++) {
				ExportChildrenIterator(doc, childrenElement, children.get(i));
			}	
		}
	}

	@Override
	public FamilyMember Import(String filePath) {
		FamilyTree = null;
		
		try {
			File file = new File(filePath);
			
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			
			if (doc.hasChildNodes()) {
				FamilyTree = ImportChildrenIterator(doc.getChildNodes(), FamilyTree);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return FamilyTree;
	}

	private FamilyMember ImportChildrenIterator(NodeList myObj, FamilyMember familyMember) {
		for (int i = 0; i < myObj.getLength(); i++) {
			Node currentNode = myObj.item(i);
	    	
	    	if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
	    		// Create a new member
	    		String name = "";
	    		FamilyMember childMember = null;
	    		
	    		NodeList children = currentNode.getChildNodes();
	    		for (int j = 0; j < children.getLength(); j++) {
	    			Node node = children.item(j);
	    			switch (node.getNodeName()) {
					case "Name":
						name = node.getTextContent();
						break;
					case "Gender":
						childMember = GetFamilyMember(FamilyTree, name, node.getTextContent());
						
						if (familyMember == null) {
		        			/* Init family */
		        			familyMember = childMember;
		        			// Add initial object to the tree so we can search for duplicate children
		        			FamilyTree = familyMember;
		        		} else {
		        			// Add a member as another one child
		                	familyMember.addChild(childMember);
		        		}
						break;
					case "Children":
						ImportChildrenIterator(node.getChildNodes(), childMember);
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
