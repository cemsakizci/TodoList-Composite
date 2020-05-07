package domain;

public interface INoteState {

	/**
	 * Request for current state to change its state to Incomplete.
	 * @param note is the context that behaves differently according to its state.
	 */
	public void changeToIncomplete(Note note) throws StateTransitionException;
	
	/**
	 * Request for current state to change its state to Cancelled.
	 * @param note is the context that behaves differently according to its state.
	 */
	public void changeToCancelled(Note note) throws StateTransitionException;
	
	/**
	 * Request for current state to change its state to Permanent.
	 * @param note is the context that behaves differently according to its state.
	 */
	public void changeToPermanent(Note note) throws StateTransitionException;
	
	/**
	 * Request for current state to change its state to Completed.
	 * @param note is the context that behaves differently according to its state.
	 */
	public void changeToCompleted(Note note) throws StateTransitionException;
	
}
