package domain;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
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
			
			JSONArray childrenNotes = new JSONArray();
			for(NoteComponent childNote : noteChildren) {
				JSONObject childNoteJSONObject = recursiveJSONExport(childNote);
				childrenNotes.add(childNoteJSONObject);
			}
			note.put(rootNote.getTitle(), childrenNotes);
			
		} catch(Exception e) {
			//Exception means we are in leaf note
			//This is the base of the recursive function
			note.put("Note-ID", rootNote.getId());
			note.put("Note-Title", rootNote.getTitle());
			try {
				note.put("Note-Content", rootNote.getContent());
				note.put("Note-Date", rootNote.getDate());
				note.put("Note-State", rootNote.getState().toString());
			} catch(Exception error) {
				//this part will not be executed as the given 3 methods are defined for a note
			}
			
		}
		
		return note;
	}
}
