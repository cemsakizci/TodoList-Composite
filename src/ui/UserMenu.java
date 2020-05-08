package ui;
import java.util.Scanner;
import domain.MenuController;

public class UserMenu {
	private MenuController controller;
	private Scanner scanner;
	private boolean isClearActive;
	
	public UserMenu(){
		this(false);
	}
	
	public UserMenu(boolean flag){
		controller = new MenuController();
		scanner = new Scanner(System.in);
		isClearActive = flag;
	}
	
	/**
	 * Clears the displayed screen.
	 * This method may not work in some IDE (Eclipse etc.), but terminal.
	 * This method does not cause any problem when does not work
	 * Since it causes some empty box symbols in terminal in Eclipse, 
	 * for current project it is set as 'don't work'
	 */
	private void clear(){
		try {
	        if (System.getProperty("os.name").contains("Windows"))
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        else
	            Runtime.getRuntime().exec("clear");
	    } catch (Exception e) {
	    	// Clear does not work for current system
	    	// Eclipse does not support console clear process
	    }
	}
	
	/**
	 * Asks user to cancel an operation
	 * @return userAnswer
	 */
	private boolean cancel(){
		System.out.println("Do you want to cancel?");
		System.out.println(UserMenuReferences.Menus.CONFIRM_MENU);
		int limit = UserMenuReferences.Menus.CONFIRM_MENU.getLimit();
		int userInput = getUserInput(UserMenuReferences.Menus.CONFIRM_MENU, 1, limit);
		if(userInput == 1){return true;}
		return false;
		
		
	}
	
	/**
	 * Creates new note or note group
	 * @return operationMessage
	 */
	private String create(){
		if (isClearActive){this.clear();}
		int limit = UserMenuReferences.Menus.CREATE_MENU.getLimit();
		System.out.println(UserMenuReferences.Menus.CREATE_MENU);
		int userInput = getUserInput(UserMenuReferences.Menus.CREATE_MENU, UserMenuReferences.SpecialSelections.BACK_SIGN.getValue(), limit);
		if(userInput == UserMenuReferences.SpecialSelections.BACK_SIGN.getValue()){
			return "";
		}
		if (isClearActive){this.clear();}
		String title;
		System.out.print("Title: ");
		title = getUserInputString();
		while(title == UserMenuReferences.ErrorType.UNRECOGNISED_TOKEN.toString()){
			System.out.println(UserMenuReferences.Messages.UNRECOGNIZED_TOKEN);
			System.out.print("Title: ");
			title = getUserInputString();
		}
		while(title == UserMenuReferences.ErrorType.NULL_TOKEN.toString()){
			System.out.println(UserMenuReferences.Messages.NULL_TOKEN);
			System.out.print("Title: ");
			title = getUserInputString();
		}
		
		if(userInput == 1){
			
			String content;
			String status;
			
			System.out.print("Content: ");
			content = getUserInputString();
			while(content == UserMenuReferences.ErrorType.UNRECOGNISED_TOKEN.toString()){
				System.out.println(UserMenuReferences.Messages.UNRECOGNIZED_TOKEN);
				System.out.print("Content: ");
				content = getUserInputString();
			}
			while(content == UserMenuReferences.ErrorType.NULL_TOKEN.toString()){
				System.out.println(UserMenuReferences.Messages.NULL_TOKEN);
				System.out.print("Content: ");
				content = getUserInputString();
			}
			
			System.out.println("Status: ");
			System.out.println(UserMenuReferences.Menus.STATUS_MENU);
			limit = UserMenuReferences.Menus.STATUS_MENU.getLimit();
			userInput = this.getUserInput(UserMenuReferences.Menus.STATUS_MENU, 1, limit);
			if(userInput == 1){ // Incomplete
				status = "Incomplete";
			}else if(userInput == 2){
				status = "Permanent";
			}else{
				status = UserMenuReferences.ErrorType.UNRECOGNISED_TOKEN.toString();
				System.out.println(UserMenuReferences.Messages.INCONSISTENT_SELECTION);
				System.exit(1);
			}
			System.out.println("Do you want to create specified note?");
			System.out.println(UserMenuReferences.Menus.CONFIRM_MENU);
			limit = UserMenuReferences.Menus.CONFIRM_MENU.getLimit();
			userInput = this.getUserInput(UserMenuReferences.Menus.CONFIRM_MENU, 1, limit);
			if(userInput == 2){return UserMenuReferences.Messages.CANCEL_MESSAGE.toString();}
			
			try{
				if(status == UserMenuReferences.ErrorType.UNRECOGNISED_TOKEN.toString()){
					System.out.println(UserMenuReferences.Messages.CREATE_FAIL);
					return UserMenuReferences.Messages.CREATE_FAIL
							+ "\n" + UserMenuReferences.Messages.UNRECOGNIZED_TOKEN;
				}
				boolean result = this.controller.createNote(title, content, status);
				if(result){
					return UserMenuReferences.Messages.CREATE_SUCCESS.toString();
				}else{
					return UserMenuReferences.Messages.CREATE_FAIL.toString();
				}
			}catch(Exception e){
				return UserMenuReferences.Messages.CREATE_FAIL.toString() 
						+ "\n" + UserMenuReferences.Messages.UNRECOGNIZED_ERROR;
			}
		}else if(userInput == 2){
			System.out.println("Do you want to create specified note group?");
			System.out.println(UserMenuReferences.Menus.CONFIRM_MENU);
			limit = UserMenuReferences.Menus.CONFIRM_MENU.getLimit();
			userInput = this.getUserInput(UserMenuReferences.Menus.CONFIRM_MENU, 1, limit);
			if(userInput == 2){return UserMenuReferences.Messages.CANCEL_MESSAGE.toString();}
			
			try{
				boolean result = this.controller.createNoteGroup(title);
				if(result){
					return UserMenuReferences.Messages.CREATE_SUCCESS.toString();
				}else{
					return UserMenuReferences.Messages.CREATE_FAIL.toString();
				}
			}catch(Exception e){
				return UserMenuReferences.Messages.CREATE_FAIL.toString() 
						+ "\n" + UserMenuReferences.Messages.UNRECOGNIZED_ERROR.toString();
			}
		
		}else if(userInput == UserMenuReferences.SpecialSelections.BACK_SIGN.getValue()){
			return "";
		}
		else{
			return UserMenuReferences.Messages.INCONSISTENT_SELECTION.toString();
		}
	}
	
	/**
	 * Displays existing note and note groups
	 * @return operationMessage
	 */
	private String goToNotes(){
		
		int type = controller.getCurrentNoteType();
		
		if(type == 1){ // note
			String details = controller.getCurrentNoteLeafDetails();
			System.out.println(details);
			System.out.println(UserMenuReferences.Menus.NOTE_MENU);
			int limit = UserMenuReferences.Menus.NOTE_MENU.getLimit();
			int userInput = getUserInput(UserMenuReferences.Menus.NOTE_MENU, UserMenuReferences.SpecialSelections.EXIT_SIGN.getValue(), limit);
			if(userInput == 1){
				if(controller.changeNoteState(0)){
					System.out.println(UserMenuReferences.Messages.CHANGE_SUCCESS);
				}else{
					System.out.println(UserMenuReferences.Messages.CHANGE_FAIL);
				}
				return this.goToNotes();
			}else if (userInput == 2){
				if(controller.changeNoteState(1)){
					System.out.println(UserMenuReferences.Messages.CHANGE_SUCCESS);
				}else{
					System.out.println(UserMenuReferences.Messages.CHANGE_FAIL);
				}
				return this.goToNotes();
			}else if (userInput == 3){
				if(controller.changeNoteState(2)){
					System.out.println(UserMenuReferences.Messages.CHANGE_SUCCESS);
				}else{
					System.out.println(UserMenuReferences.Messages.CHANGE_FAIL);
				}
				return this.goToNotes();
			}else if(userInput == 4){
				if(controller.changeNoteState(3)){
					System.out.println(UserMenuReferences.Messages.CHANGE_SUCCESS);
				}else{
					System.out.println(UserMenuReferences.Messages.CHANGE_FAIL);
				}
				return this.goToNotes();
			} else if(userInput == UserMenuReferences.SpecialSelections.BACK_SIGN.getValue()){
				if(controller.goToParent() == false){
					System.out.println(UserMenuReferences.Messages.BACK_FAIL);
				}
				return this.goToNotes();
			} else if(userInput == UserMenuReferences.SpecialSelections.EXIT_SIGN.getValue()){
				if(this.cancel()){
					return UserMenuReferences.Messages.CANCEL_MESSAGE.toString();
				}
				return this.goToNotes();
			}
			else{return UserMenuReferences.Messages.UNRECOGNIZED_ERROR.toString();}
		} else if (type == 0){ // note group
			String currentMenu = controller.getCurrentNoteGroupDetails();
			if(currentMenu == null || currentMenu == ""){
				System.out.println(UserMenuReferences.Messages.EMPTY_GROUP);
			}
			currentMenu += UserMenuReferences.Menus.NOTEGROUP_MENU;
			System.out.println(currentMenu);
			int userInput = getUserInputInteger();
			if(userInput == UserMenuReferences.SpecialSelections.CREATE_SIGN.getValue()){
				String resultMessage = this.create();
				if(isClearActive){this.clear();}
				if(resultMessage != null || resultMessage != ""){
					System.out.println(resultMessage);
				}
				return this.goToNotes();
			} else if(userInput == UserMenuReferences.SpecialSelections.BACK_SIGN.getValue()){
				if(isClearActive){this.clear();}
				
				if(controller.goToParent() == false){
					System.out.println(UserMenuReferences.Messages.BACK_FAIL);
				}
				return this.goToNotes();
			} else if(userInput == UserMenuReferences.SpecialSelections.EXIT_SIGN.getValue()){
				if(this.cancel()){
					return UserMenuReferences.Messages.CANCEL_MESSAGE.toString();
				} else{
					return this.goToNotes();
				}
				
			}
			
			try{
				if(controller.goToNote(userInput) == false){
					System.out.println(UserMenuReferences.Messages.INCORRECT_INPUT);
				}
			}catch(Exception e){
				System.out.println(UserMenuReferences.Messages.INCORRECT_INPUT);
			}
			
			return this.goToNotes();
			
			
		} else{
			return UserMenuReferences.Messages.UNRECOGNIZED_ERROR.toString();
		}
		
		
	} 
	
	/**
	 * Exports existing note and note groups into a JSON File
	 * @return operationMessage
	 */
	private String exportJSON(){
		try{
			boolean result = controller.exportNotes();
			if(result){
				return UserMenuReferences.Messages.EXPORT_SUCCESS.toString();
			}else{
				return UserMenuReferences.Messages.EXPORT_FAIL.toString();
			}
		}catch(Exception e){
			return UserMenuReferences.Messages.EXPORT_FAIL.toString() 
					+ "\n" + UserMenuReferences.Messages.UNRECOGNIZED_ERROR.toString();
		}
	}
	
	/**
	 * Delete all created note and note groups
	 * @return operationMessage
	 */
	private String reset(){
		try{
			boolean result = controller.resetNotes();
			if(result){
				return UserMenuReferences.Messages.RESET_SUCCESS.toString();
			}else{
				return UserMenuReferences.Messages.RESET_FAIL.toString();
			}
		}catch(Exception e){
			return UserMenuReferences.Messages.RESET_FAIL.toString() 
					+ "\n" + UserMenuReferences.Messages.UNRECOGNIZED_ERROR.toString();
		}
	}
	
	/**
	 * Terminates the program
	 * @return operationMessage
	 */
	private String exit(){
		try{
			System.out.println("Do you want to exit?");
			System.out.println(UserMenuReferences.Menus.CONFIRM_MENU);
			int limit = UserMenuReferences.Menus.CONFIRM_MENU.getLimit();
			int userInput = this.getUserInput(UserMenuReferences.Menus.CONFIRM_MENU, 1, limit);
			if(userInput == 1){
				return UserMenuReferences.Messages.EXIT_MESSAGE.toString();
			}else if(userInput == 2){
				return UserMenuReferences.Messages.CANCEL_MESSAGE.toString();
			}else{
				return UserMenuReferences.Messages.INCONSISTENT_SELECTION.toString();
			}
		}catch(Exception e){
			return UserMenuReferences.Messages.UNRECOGNIZED_ERROR.toString();
		}
	}
	
	/**
	 * Executes UserMenu
	 */
	public void run(){
		
		String message;
		// Main Menu Display
		System.out.println(UserMenuReferences.Menus.MAIN_MENU);
		int limit = UserMenuReferences.Menus.MAIN_MENU.getLimit();
		int userInput = getUserInput(UserMenuReferences.Menus.MAIN_MENU, 1, limit);
		
		// Submenu Display
		switch(userInput){
			case 1:
				message = this.goToNotes();
				if(controller.goToRoot() == false){
					message = UserMenuReferences.Messages.UNRECOGNIZED_ERROR.toString();
				};
				break;
			case 2:
				message = this.exportJSON();
				break;
			case 3:
				message = this.reset();
				break;
			case 4:
				message = this.exit();
				break;
			default:
				message = UserMenuReferences.Messages.INCONSISTENT_SELECTION.toString();
		}
		if(message == UserMenuReferences.Messages.EXIT_MESSAGE.toString()){
			System.out.println(message);
			System.exit(0);
		}
		if(message == UserMenuReferences.Messages.UNRECOGNIZED_ERROR.toString()){
			System.out.println(message);
			System.exit(1);
		}
		if (isClearActive){this.clear();}
		System.out.println(message);
		this.run();
	}
	
	
	/**
	 * Gets integer user input to be used for selection by checking validity of the selection
	 * @param limit
	 * @return userSelection
	 */
	private int getUserInput(UserMenuReferences.Menus menu ,int start, int end){
		String input = scanner.nextLine();
		try{
			int inputInt;
			if(input.equals("+")){ // Create
				inputInt = UserMenuReferences.SpecialSelections.CREATE_SIGN.getValue();
			} 
			else if(input.equals("<")){
				inputInt =  UserMenuReferences.SpecialSelections.BACK_SIGN.getValue();
			} else if (input.equals("-")){
				inputInt = UserMenuReferences.SpecialSelections.EXIT_SIGN.getValue();
			}else{
				try{
					inputInt = Integer.valueOf(input);
				}
				catch(Exception e){
					System.out.println(UserMenuReferences.Messages.INCORRECT_INPUT);
					System.out.println(menu);
					return getUserInput(menu, start, end);
				}
			}
			
			if(inputInt >= start || inputInt <= end || inputInt != 0){
				return inputInt;
			}else{
				System.out.println(UserMenuReferences.Messages.INCORRECT_INPUT);
				System.out.println(menu);
				return getUserInput(menu, start, end);
			}
		}catch(Exception e){
			return -1;
		}
	}
	
	
	private int getUserInputInteger(){
		try{
			String input = scanner.nextLine();
			int inputInt;
			if(input.equals("+")){ // Create
				inputInt = UserMenuReferences.SpecialSelections.CREATE_SIGN.getValue();
			} 
			else if(input.equals("<")){
				inputInt =  UserMenuReferences.SpecialSelections.BACK_SIGN.getValue();
			} else if (input.equals("-")){
				inputInt = UserMenuReferences.SpecialSelections.EXIT_SIGN.getValue();
			} else{
				return Integer.valueOf(input);
			}
			return inputInt;
			
		}catch(Exception e){
			return -1;
		}
	}
	
	/**
	 * Gets string user input for any purpose
	 * @return userInput
	 */
	private String getUserInputString(){
		try{
			String userInput = scanner.nextLine();
			if(userInput.trim().isEmpty()){
				return UserMenuReferences.ErrorType.NULL_TOKEN.toString();
			}
			return userInput;
		}catch(Exception e){
			return UserMenuReferences.ErrorType.UNRECOGNISED_TOKEN.toString();
		}
	}
	
	
	
}

