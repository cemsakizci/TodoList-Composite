package domain;

import java.util.*;

public class Note extends NoteComponent {
	private String content;
	private Date date;
	
	public Note(int id, String title, String content, Date date) {
		super(id, title);
		setContent(content);
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
		return "Content: " + this.getContent() + "\nDate: " + this.getDate();
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
	
	public Date getDate() {
		return this.date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public void changeToIncomplete() {
	}

	public void changeToCancelled() {
	}

	public void changeToPermanent() {
	}

	public void changeToCompleted() {
	}
}