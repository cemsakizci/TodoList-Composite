package domain;

import java.util.ArrayList;

public class MenuController {
	private NoteComponent currentComponent;
	private NoteComponent rootComponent;
	private NoteComponent previousComponent;	
	private JSONManager jsonManager;
	private NoteComponentGenerator noteComponentGenerator;
	private int childCounter;
	
	public MenuController() {
		this.childCounter = 0;
		this.jsonManager = new JSONManager();
		this.noteComponentGenerator = new NoteComponentGenerator();
		this.rootComponent = this.noteComponentGenerator.createNoteGroup("MyNotes");
		this.currentComponent = this.rootComponent;
		this.previousComponent = this.rootComponent;
		
	}
	
	public NoteComponent getCurrentComponent() {
		return this.currentComponent;
	}
	
	
	public boolean createNote(String title, String content, String state) {
		try {
			if (state.equals("Incomplete")) {
				this.currentComponent.addNoteComponent(this.noteComponentGenerator.createNote(title, content, new IncompleteState()));
			} else if (state.equals("Permanent")){
				this.currentComponent.addNoteComponent(this.noteComponentGenerator.createNote(title, content, new IncompleteState()));
			}
			else {
				return false;
			}
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean exportNotes() {
		boolean status = jsonManager.writeNotes(rootComponent);
		return status;
	}
	
	public boolean resetNotes() {
		try {
			rootComponent.deleteChildren();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean createNoteGroup(String title) throws OperationIsNotAllowedException {
		this.currentComponent.addNoteComponent(noteComponentGenerator.createNoteGroup(title));
		return true;
	}
	
	public int getCurrentNoteType() {
		try {
			this.currentComponent.getChildren();
			return 0;
		}catch(Exception e) {
			return 1;
		}
	}
	
	public String getCurrentNoteGroupDetails(){
		
		return this.currentComponent.getNodeDetails();
	}
	
	public String getCurrentNoteLeafDetails(){
		
		return this.currentComponent.getNodeDetails();
	}
		
	public boolean goToNote(int id) throws OperationIsNotAllowedException {
		NoteComponent newComponent = this.currentComponent.findChild(id); 
		if(newComponent == null) {
			this.previousComponent = this.currentComponent;
			this.currentComponent = newComponent;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean goToParent() {
		if(this.previousComponent == this.rootComponent) {
			return false;
		}else {
			NoteComponent temp = this.currentComponent;
			this.previousComponent = this.currentComponent;
			this.currentComponent = temp;
			return true;
		}
	}
	
	public boolean changeNoteState(int state) {
		return true;
	}
	
	


}
