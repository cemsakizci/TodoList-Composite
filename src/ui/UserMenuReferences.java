package ui;
public class UserMenuReferences{
	/**
	 * Type of user input value that is undesired for the program
	 */
	public enum ErrorType {
		/**
		 * Input that unrecognised by the system
		 */
		UNRECOGNISED_TOKEN, 
		/**
		 * Input that either null or has only spaces
		 */
		NULL_TOKEN;
	}
	
	/**
	 * Messages used to inform user about user preferred process
	 */
	public enum Messages{
		
		INCORRECT_INPUT,
		INCONSISTENT_SELECTION,
		UNRECOGNIZED_ERROR,
		UNRECOGNIZED_TOKEN,
		NULL_TOKEN,
		CREATE_FAIL,
		CREATE_SUCCESS,
		GO_TO_NOTES_FAIL,
		GO_TO_NOTES_SUCCESS,
		EXPORT_FAIL,
		EXPORT_SUCCESS,
		RESET_FAIL,
		RESET_SUCCESS,
		CANCEL_MESSAGE,
		EXIT_MESSAGE;
		
		
		String message_contents[] = {
		"System Error::Incorrect Selection. Please Make a Valid Selection!",
		"System Critical Error::Inconsistent Selection Detected!",
		"System Critical Error::Unrecognized Error Occured!",
		"System Error::Unrecognized Token!",
		"System Error::Null Token!",
		"System Message::Error | Create Failed!",
		"System Message::Create is Successfull!",
		"System Message::Error | Go To Notes Failed!",
		"System Message::No Remaining Note in This Branch!",
		"System Message::Error | Export Failed!",
		"System Message::Export is Successfull!",
		"System Message::Error | Reset Failed",
		"System Message::Reset is Successfull!",
		"System Message::Operation Cancelled!",
		"System Message::Program Terminated!"};
		
		@Override
		public String toString(){
			return message_contents[ordinal()];
		}
	}
	
	/**
	 * Print menus
	 */
	public enum Menus{
		MAIN_MENU,
		CREATE_MENU,
		STATUS_MENU,
		CONFIRM_MENU;
		String menu_contents[] = {
				"1. Create..\n"
				+ "2. Go to existing notes\n"
				+ "3. Export notes as JSON\n"
				+ "4. Reset all notes\n"
				+ "5. Exit",
				
				"1. Note\n"
				+ "2. Note Group\n"
				+ "3. Cancel",
				
				"1. Incomplete\n"
				+ "2. Permanent",
						
				"1. Confirm\n"
				+ "2. Cancel"
		};
		int menu_limits[] = {
				5, 3, 2, 2
		};
		@Override
		public String toString(){
			return menu_contents[ordinal()];
		}
		/**
		 * Returns the number of the menu selection
		 * @return numberOfSelections
		 */
		public int getLimit(){
			return menu_limits[ordinal()];
		}
	}

}