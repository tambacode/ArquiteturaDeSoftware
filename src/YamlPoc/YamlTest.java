package YamlPoc;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YamlTest {
	private String filePath = "C:\\Nano\\customer.yaml"; 
	
	public void WriteYaml()
	{
		Yaml yaml = new Yaml();
	    StringWriter writer = new StringWriter();
	    
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
	    data.put("firstName", "John");
	    data.put("lastName", "Doe");
	    data.put("age", 31);

	    Map<String, Object> contact1 = new LinkedHashMap<String, Object>();	    
	    contact1.put("type", "mobile");
	    contact1.put("number", 123456789);
	    Map<String, Object> contact2 = new LinkedHashMap<String, Object>();	    
	    contact2.put("type", "landline");
	    contact2.put("number", 456786868);
	    ArrayList<Object> contactDetails = new ArrayList<Object>();
	    contactDetails.add(contact1);
	    contactDetails.add(contact2);
	    data.put("contactDetails", contactDetails);
		
		Map<String, Object> homeAddress = new LinkedHashMap<String, Object>();
		homeAddress.put("line", "Xyz, DEF Street");
		homeAddress.put("city", "City Y");
		homeAddress.put("state", "State Y");
		homeAddress.put("zip", 345657);
	    data.put("homeAddress", homeAddress);
//	    data.put("age", new String[] { "ONE_HAND", "ONE_EYE" });
	    
	    yaml.dump(data, writer);
	    
	    //String expectedYaml = "name: Silenthand Olleander\nrace: Human\ntraits: [ONE_HAND, ONE_EYE]\n";
	    //assertEquals(expectedYaml, writer.toString());	
	    
	    try (FileWriter file = new FileWriter(filePath)) {
            file.write(writer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void ReadYaml()
	{
		Yaml yaml = new Yaml();

		try (InputStream inputStream = new FileInputStream(filePath);) {
			Map<String, Object> obj = yaml.load(inputStream);
			
			for (Map.Entry<String, Object> entry : obj.entrySet())  
			{
				PrintEntry(entry);
			}

			System.out.println("");
			System.out.println(obj);
		}
		catch (FileNotFoundException e) { e.printStackTrace(); }
		catch (IOException e1) { e1.printStackTrace(); }
	}
	
	private void PrintEntry(Map.Entry<String, Object> entry)
	{
		//(entry.getValue() instanceof ArrayList)
		//(entry.getValue() instanceof Integer)
		//(entry.getValue() instanceof Map)
		//(entry.getValue() instanceof Object)
		
		String entryType = entry.getValue().getClass().getName();
		System.out.println(entryType + " Key = " + entry.getKey() + ", Value = " + entry.getValue());
	}
}
