package domain;

import java.util.ArrayList;

public class MenuController {
	private NoteComponent currentComponent;
	private NoteComponent rootComponent;	
	private JSONManager jsonManager;
	private NoteComponentGenerator noteComponentGenerator;
	
	public MenuController() {
		this.jsonManager = new JSONManager();
		this.noteComponentGenerator = new NoteComponentGenerator();
		this.rootComponent = this.noteComponentGenerator.createNoteGroup("MyNotes", null);
		this.currentComponent = this.rootComponent;
		
	}
	
	public NoteComponent getCurrentComponent() {
		return this.currentComponent;
	}
	
	
	public boolean createNote(String title, String content, String state) {
		try {
			if (state.equals("Incomplete")) {
				this.currentComponent.addNoteComponent(this.noteComponentGenerator.createNote(title, content, this.currentComponent, new IncompleteState()));
			} else if (state.equals("Permanent")){
				this.currentComponent.addNoteComponent(this.noteComponentGenerator.createNote(title, content, this.currentComponent, new PermanentState()));
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
	
	public boolean createNoteGroup(String title) {
		try {
			this.currentComponent.addNoteComponent(noteComponentGenerator.createNoteGroup(title, this.currentComponent));
		}catch(Exception e) {
			return false;
		}
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
	
	public String getCurrentNoteDetails(){
		
		return this.currentComponent.getNodeDetails();
	}
			
	public boolean goToNote(int id) {
		try{
			NoteComponent newComponent = this.currentComponent.findChild(id); 
			if(newComponent != null) {
				this.currentComponent = newComponent;
				return true;
			} else {
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean goToParent() {
		if(this.currentComponent.getParent() == null) {
			return false;
		}else {
			this.currentComponent = this.currentComponent.getParent();
			return true;
		}
	}
	
	public boolean changeNoteState(int state) {
		try {
			switch(state) {
			case 0:
				this.currentComponent.changeToIncomplete();
				break;
			case 1:
				this.currentComponent.changeToCompleted();
				break;
			case 2:
				this.currentComponent.changeToCancelled();
				break;
			default:
				this.currentComponent.changeToPermanent();
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean goToRoot(){
		try{
			this.currentComponent = this.rootComponent;
			return true;
		}catch(Exception e){
			return false;
		}
	}
	


}
