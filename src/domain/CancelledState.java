package domain;

public class CancelledState implements INoteState {

	@Override
	public void changeToIncomplete(Note note) throws StateTransitionException {
		throw new StateTransitionException("The note cannot be changed from Cancelled to Incomplete state");
	}
	
	@Override
	public void changeToCancelled(Note note) throws StateTransitionException {
		throw new StateTransitionException("Already in Cancelled state");
	}

	@Override
	public void changeToPermanent(Note note) throws StateTransitionException {
		throw new StateTransitionException("The note cannot be changed from Cancelled to Permanent state");
	}

	@Override
	public void changeToCompleted(Note note) throws StateTransitionException {
		throw new StateTransitionException("The note cannot be changed from Cancelled to Completed state");
	}
	
}
