package domain;

public interface IMenuController {
		
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
	 * Returns the details to print of the current component
	 * @return Returns detail string
	 */
	public String getCurrentNoteDetails();
			
	/**
	 * Updates the current component with the given child id
	 * @return True if operation succeeded, False if operation failed.
	 */
	public boolean goToNote(int id);
	
	/**
	 * Updates the current component with parent of it.
	 * @return True if operation succeeded, False if operation failed.
	 */
	public boolean goToParent();
	
	/**
	 * Changes the state of current note.
	 * @return True if operation succeeded, False if operation failed.
	 */
	public boolean changeNoteState(int state);
	
	/**
	 * Updates the current component with root component.
	 * @return True if operation succeeded, False if operation failed.
	 */
	public boolean goToRoot();

}
