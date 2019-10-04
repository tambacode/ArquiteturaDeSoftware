package FamilyTree;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface FamilyTreeAdapter {
	public void Export(String filePath) throws JSONException;
	public FamilyMember Import(String filePath) throws JSONException, FileNotFoundException, IOException, ParseException, ParseException;
}
