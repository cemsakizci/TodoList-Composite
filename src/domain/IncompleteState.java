package domain;

public class IncompleteState implements INoteState {

	@Override
	public void changeToIncomplete(Note note) {
		System.out.println("Already in Incomplete state");
	}

	@Override
	public void changeToCancelled(Note note) {
		note.setState(new CancelledState());	
	}

	@Override
	public void changeToPermanent(Note note) {
		System.out.println("The note cannot be changed from Incomplete to Permanent state");
	}

	@Override
	public void changeToCompleted(Note note) {
		note.setState(new CompletedState());
	}
	
}
