package domain;

public interface IMenuController {
	
	/**
	 * Get Current Component
	 * @return returns the current component of the menu controller.
	 */
	public NoteComponent getCurrentComponent();
	
	/**
	 * Create a new Note. 
	 * @return True if operation succeeded, False if operation failed.
	 */
	public boolean createNote(String title, String content, String state);
	
	/**
	 * Export all notes to json file.
	 * @return True if operation succeeded, False if operation failed.
	 */
	public boolean exportNotes();
	
	/**
	 * Reset All Notes.
	 * @return True if operation succeeded, False if operation failed.
	 */
	public boolean resetNotes();
	
	/**
	 * Create a new NoteGroup with given title.
	 * @return True if operation succeeded, False if operation failed.
	 */
	public boolean createNoteGroup(String title);
	
	/**
	 * Returns the component type of current note component
	 * @return Return integer [0,1] 0 -> NoteGroup 1->Note
	 */
	public int getCurrentNoteType();

	/**
	 * Returns the component type of current note component
	 * @return Return integer [0,1] 0 -> NoteGroup 1->Note
	 */
	public String getCurrentNoteDetails();
			
	public boolean goToNote(int id);
	
	public boolean goToParent();
	
	public boolean changeNoteState(int state);
	
	public boolean goToRoot();

}
