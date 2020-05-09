package domain;

import java.util.ArrayList;
import java.util.Date;

public class NoteGroup extends NoteComponent {
	private ArrayList<NoteComponent> noteComponentList;
	
	public NoteGroup(int id, String title, NoteComponent parent) {
		super(id, title, parent);
		this.noteComponentList = new ArrayList<NoteComponent>();
	}
	
	public NoteGroup() {
		this.noteComponentList = new ArrayList<NoteComponent>();
	}

	public ArrayList<NoteComponent> getChildren() {
		return this.noteComponentList;
	}
	
	public NoteComponent findChild(int id) {
		for (int i = 0; i < noteComponentList.size(); i++)
			if (noteComponentList.get(i).getId() == id)
				return noteComponentList.get(i);
		
		return null;
	}
	
	public String getNodeDetails() {
		String s = "";
		for (NoteComponent component: noteComponentList)
			s += "(" + component.getId() + ") " + component.getTitle() + "\n";
		
		return s;
	}

	public void addNoteComponent(NoteComponent noteComponent) {
		this.noteComponentList.add(noteComponent);
	}
	
	public void deleteChildren() {
		this.noteComponentList.clear();
	}
	
	public String getContent() throws OperationIsNotAllowedException {
		throw new OperationIsNotAllowedException("Group does note have a content");
	}
	
	public Date getDate() throws OperationIsNotAllowedException {
		throw new OperationIsNotAllowedException("Group does note have a date");
	}
	
	public INoteState getState() throws OperationIsNotAllowedException {
		throw new OperationIsNotAllowedException("Group does note have a state");
	}
	
	public void changeToIncomplete() throws OperationIsNotAllowedException {
		throw new OperationIsNotAllowedException("Change State operation is not allowed for the note groups");
	}

	public void changeToCancelled() throws OperationIsNotAllowedException {
		throw new OperationIsNotAllowedException("Change State operation is not allowed for the note groups");
	}

	public void changeToPermanent() throws OperationIsNotAllowedException {
		throw new OperationIsNotAllowedException("Change State operation is not allowed for the note groups");
	}

	public void changeToCompleted() throws OperationIsNotAllowedException {
		throw new OperationIsNotAllowedException("Change State operation is not allowed for the note groups");
	}

}