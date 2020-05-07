package domain;

public class PermanentState implements INoteState {

	@Override
	public void changeToIncomplete(Note note) {
		System.out.println("The note cannot be changed from Permanent to Incomplete state");
	}

	@Override
	public void changeToCancelled(Note note) {
		note.setState(new CancelledState());	
	}

	@Override
	public void changeToPermanent(Note note) {
		System.out.println("Already in Permanent state");
	}

	@Override
	public void changeToCompleted(Note note) {
		System.out.println("The note cannot be changed from Permanent to Completed state");
	}
	
}
