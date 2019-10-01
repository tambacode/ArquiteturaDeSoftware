package JsonPoc;

import java.io.FileReader;
/// READ
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/// WRITE
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.Reader;
import java.util.Iterator;
 
public class CrunchifyJSONFileWrite {
	static String jsonFile = "C:\\wamp64\\www\\ArvoreGenealogica\\src\\JsonPoc\\file1.json";
	public static void main(String[] args) {
		WriteJSON();
		ReadJSON();
    }
	
	
	private static void ReadJSON() {
		System.out.println("");
		System.out.println("");
		System.out.println("LEITURA");
		JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(jsonFile)) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            System.out.println(jsonObject);

            String name = (String) jsonObject.get("name");
            System.out.println(name);

            long age = (Long) jsonObject.get("age");
            System.out.println(age);

            // loop array
            JSONArray msg = (JSONArray) jsonObject.get("messages");
            Iterator<String> iterator = msg.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }	
	}
	
	private static void WriteJSON() {
		System.out.println("ESCRITA");
		
		JSONObject obj = new JSONObject();
        obj.put("name", "mkyong.com");
        obj.put("age", 100);

        JSONArray list = new JSONArray();
        list.add("msg 1");
        list.add("msg 2");
        list.add("msg 3");

        obj.put("messages", list);

        try (FileWriter file = new FileWriter(jsonFile)) {
            file.write(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(obj);
	}
}