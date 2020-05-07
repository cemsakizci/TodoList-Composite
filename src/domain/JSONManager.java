package domain;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONObject;

public class JSONManager {
	public boolean writeNotes(NoteComponent rootNote) {
	    	    
		JSONObject notes = this.recursiveJSONExport(rootNote);
	    //Write JSON file
	    try {
	    	FileWriter file = new FileWriter("notes.json");
	        file.write(notes.toJSONString());
	        file.flush();
	        file.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	    return true;
	
	}
	
	public JSONObject recursiveJSONExport(NoteComponent rootNote) {
		JSONObject note = new JSONObject();
		try {
			//if not throws error means its note group
			ArrayList<NoteComponent> noteChildren = rootNote.getChildren();
			for(NoteComponent childNote : noteChildren) {
				JSONObject childNoteJSONObject = recursiveJSONExport(childNote);
				note.put(childNote.getTitle(), childNoteJSONObject);
			}
			
		} catch(Exception e) {
			Note noteLeaf = (Note) rootNote;
			//Exception means we are in leaf note
			//This is the base of the recursive function
			note.put("Note-Title", noteLeaf.getTitle());
			note.put("Note-Content", noteLeaf.getContent());
			note.put("Note-Date", noteLeaf.getDate());
		}
		
		return note;
	}
}
