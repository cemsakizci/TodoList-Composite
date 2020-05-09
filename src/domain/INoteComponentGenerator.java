package domain;

public interface INoteComponentGenerator {

	/**
	 * Create a new Note. 
	 * @return Note with the specified title, content, state. 
	 */
	public Note createNote(String title, String content, NoteComponent parent, INoteState state);
	
	/**
	 * Create a new NoteGroup.
	 * @return NoteGroup with the specified title.
	 */
	public NoteGroup createNoteGroup(String title, NoteComponent parent);
}
