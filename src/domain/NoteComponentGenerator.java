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

	/**
	 * Create a new Note. 
	 * @return Note with the specified title, content, state. 
	 */
	public Note createNote(String title, String content, NoteComponent parent, INoteState state) {
		setUniqueIdTracer(getUniqueIdTracer() + 1);
		
		return new Note(getUniqueIdTracer(), title, parent, content, state, new Date());
	}
	
	/**
	 * Create a new NoteGroup.
	 * @return NoteGroup with the specified title.
	 */
	public NoteGroup createNoteGroup(String title, NoteComponent parent) {
		
		setUniqueIdTracer(getUniqueIdTracer() + 1);
		
		return new NoteGroup(getUniqueIdTracer(), title, parent);
	}
	
}
