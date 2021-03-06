package domain;

import java.util.ArrayList;
import java.util.Date;

public abstract class NoteComponent {
	private int id;
	private String title;
	private NoteComponent parent;
	
	public NoteComponent() {}
	
	public NoteComponent(int id, String title, NoteComponent parent) {
		setId(id);
		setTitle(title);
		setParent(parent);
	}
	
	
	
	/**
	 * return the children of a component
	 * throws exception for a note
	 * @return List of NoteComponents if the operation is done on a note group, null otherwise
	 */
	public abstract ArrayList<NoteComponent> getChildren() throws OperationIsNotAllowedException;
	
	/**
	 * find a child of the group according to given id
	 * throws exception for a note
	 * @param id
	 * @return NoteComponent or null if not found
	 */
	public abstract NoteComponent findChild(int id) throws OperationIsNotAllowedException;
	
	/**
	 * get content of a note or list components of a note group
	 */
	public abstract String getNodeDetails();
	
	/**
	 * add a note or note group as a child
	 * throws exception for a note
	 * @param noteComponent a note or a note group
	 */
	public abstract void addNoteComponent(NoteComponent noteComponent) throws OperationIsNotAllowedException;
	
	/**
	 * Delete all noteComponents in the subtree of a node
	 */
	public abstract void deleteChildren();
	
	/**
	 * change the current state to the desired state=
	 */
	public abstract void changeToIncomplete() throws OperationIsNotAllowedException, StateTransitionException;
	public abstract void changeToCancelled() throws OperationIsNotAllowedException, StateTransitionException;
	public abstract void changeToPermanent() throws OperationIsNotAllowedException, StateTransitionException;
	public abstract void changeToCompleted() throws OperationIsNotAllowedException, StateTransitionException;
	
	/**
	 * define method for notes, throws exception for a note group
	 * @throws OperationIsNotAllowedException
	 */
	public abstract String getContent() throws OperationIsNotAllowedException;
	public abstract Date getDate() throws OperationIsNotAllowedException;
	public abstract INoteState getState() throws OperationIsNotAllowedException;
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setParent(NoteComponent parent){
		this.parent = parent;
	}
	
	public NoteComponent getParent(){
		return this.parent;
	}
}