package domain;

public class MenuController {
private NoteComponent currentComponent;
private NoteComponent rootComponent;
private JSONManager jsonManager;
private NoteComponentGenerator noteComponentGenerator;

public MenuController(NoteComponent rootComponent, JSONManager jsonManager, NoteComponentGenerator noteComponentGenerator) {
	this.currentComponent = rootComponent;
	this.rootComponent = rootComponent;
	this.jsonManager = jsonManager;
	this.noteComponentGenerator = noteComponentGenerator;
}

public NoteComponent getCurrentComponent() {
	return this.currentComponent;
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
	noteComponentGenerator.createNoteGroup(title);
	return true;
}


}
