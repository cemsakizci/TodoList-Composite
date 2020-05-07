package domain;

public class CompletedState implements INoteState {

	@Override
	public void changeToIncomplete(Note note) throws StateTransitionException {
		throw new StateTransitionException("The note cannot be changed from Completed to Incomplete state");
	}

	@Override
	public void changeToCancelled(Note note) throws StateTransitionException {
		throw new StateTransitionException("The note cannot be changed from Completed to Cancelled state");
	}

	@Override
	public void changeToPermanent(Note note) throws StateTransitionException {
		throw new StateTransitionException("The note cannot be changed from Completed to Permanent state");
	}

	@Override
	public void changeToCompleted(Note note) throws StateTransitionException {
		throw new StateTransitionException("Already in Completed state");
	}
	
}
