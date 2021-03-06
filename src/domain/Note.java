package domain;

import java.util.*;

public class Note extends NoteComponent {
	private String content;
	private Date date;
	private INoteState state;
	
	public Note() {}
	
	public Note(int id, String title, NoteComponent parent, String content, INoteState state, Date date) {
		super(id, title, parent);
		setContent(content);
		setState(state);
		setDate(date);
	}
	
	public ArrayList<NoteComponent> getChildren() throws OperationIsNotAllowedException {
		throw new OperationIsNotAllowedException("Get Children operation is not allowed for the notes");
	}
	
	public NoteComponent findChild(int id) throws OperationIsNotAllowedException {
		throw new OperationIsNotAllowedException("Find Child operation is not allowed for the notes");
	}
	
	public void addNoteComponent(NoteComponent noteComponent) throws OperationIsNotAllowedException {
		throw new OperationIsNotAllowedException("Add Component operation is not allowed for the notes");
	}
	
	public String getNodeDetails() {
		return "Content: " + this.getContent() + "\nState: " + this.getState();
	}
	
	public void deleteChildren() {
		return;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public INoteState getState() {
		return this.state;
	}
	
	public void setState(INoteState state) {
		this.state = state;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public void changeToIncomplete() throws StateTransitionException {
		this.state.changeToIncomplete(this);
	}

	public void changeToCancelled() throws StateTransitionException {
		this.state.changeToCancelled(this);
	}

	public void changeToPermanent() throws StateTransitionException {
		this.state.changeToPermanent(this);
	}

	public void changeToCompleted() throws StateTransitionException {
		this.state.changeToCompleted(this);
	}
}