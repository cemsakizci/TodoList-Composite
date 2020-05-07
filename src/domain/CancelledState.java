package domain;

public class CancelledState implements INoteState {

	@Override
	public void changeToIncomplete(Note note) {
		System.out.println("The note cannot be changed from Cancelled to Incomplete state");
	}
	
	@Override
	public void changeToCancelled(Note note) {
		System.out.println("Already in Cancelled state");
	}

	@Override
	public void changeToPermanent(Note note) {
		System.out.println("The note cannot be changed from Cancelled to Permanent state");
	}

	@Override
	public void changeToCompleted(Note note) {
		System.out.println("The note cannot be changed from Cancelled to Completed state");
	}
	
}
