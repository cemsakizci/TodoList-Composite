package domain;

public class IncompleteState implements INoteState {

	@Override
	public void changeToIncomplete(Note note) throws StateTransitionException {
		throw new StateTransitionException ("Already in Incomplete state");
	}

	@Override
	public void changeToCancelled(Note note) {
		note.setState(new CancelledState());	
	}

	@Override
	public void changeToPermanent(Note note) throws StateTransitionException {
		throw new StateTransitionException("The note cannot be changed from Incomplete to Permanent state");
	}

	@Override
	public void changeToCompleted(Note note) {
		note.setState(new CompletedState());
	}
	
	public String toString() {
		return "Incomplete";
	}
}
