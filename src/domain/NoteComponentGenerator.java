package domain;

import java.util.Date;

public class NoteComponentGenerator implements INoteComponentGenerator{

private int uniqueIdTracer; // To assign a unique id to a NoteComponent. 
	
	public NoteComponentGenerator() {
		setUniqueIdTracer(0); // At the beginning, unique id is set as 0.
	}
	
	private int getUniqueIdTracer() {
		return uniqueIdTracer;
	}

	private void setUniqueIdTracer(int uniqueIdTracer) {
		this.uniqueIdTracer = uniqueIdTracer;
	}

	public Note createNote(String title, String content, INoteState state) {
	
		setUniqueIdTracer(getUniqueIdTracer() + 1);
		
		return new Note(getUniqueIdTracer(), title, content, state, new Date());
	}
	
	public NoteGroup createNoteGroup(String title) {
		
		setUniqueIdTracer(getUniqueIdTracer() + 1);
		
		return new NoteGroup(getUniqueIdTracer(), title);
	}
	
}
