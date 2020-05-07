package ui;
import java.util.ArrayList;
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
		int userInput = getUserInput(limit);
		if(userInput == -1){userInput = getValidUserInput(UserMenuReferences.Menus.CONFIRM_MENU, limit);}
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
		int userInput = getUserInput(limit);
		if(userInput == -1){userInput = this.getValidUserInput(UserMenuReferences.Menus.CREATE_MENU, limit);}
		else if(userInput == 3){
			if(this.cancel()){return UserMenuReferences.Messages.CANCEL_MESSAGE.toString();}
			return this.create();
		}
		if (isClearActive){this.clear();}
		String title;
		System.out.print("Title: ");
		title = getUserInput();
		while(title == UserMenuReferences.ErrorType.UNRECOGNISED_TOKEN.toString()){
			System.out.println(UserMenuReferences.Messages.UNRECOGNIZED_TOKEN);
			System.out.print("Title: ");
			title = getUserInput();
		}
		while(title == UserMenuReferences.ErrorType.NULL_TOKEN.toString()){
			System.out.println(UserMenuReferences.Messages.NULL_TOKEN);
			System.out.print("Title: ");
			title = getUserInput();
		}
		
		if(userInput == 1){
			
			String content;
			String status;
			
			System.out.print("Content: ");
			content = getUserInput();
			while(content == UserMenuReferences.ErrorType.UNRECOGNISED_TOKEN.toString()){
				System.out.println(UserMenuReferences.Messages.UNRECOGNIZED_TOKEN);
				System.out.print("Content: ");
				content = getUserInput();
			}
			while(content == UserMenuReferences.ErrorType.NULL_TOKEN.toString()){
				System.out.println(UserMenuReferences.Messages.NULL_TOKEN);
				System.out.print("Content: ");
				content = getUserInput();
			}
			
			System.out.println("Status: ");
			System.out.println(UserMenuReferences.Menus.STATUS_MENU);
			limit = UserMenuReferences.Menus.STATUS_MENU.getLimit();
			userInput = this.getUserInput(limit);
			if(userInput == -1){userInput = this.getValidUserInput(UserMenuReferences.Menus.STATUS_MENU, limit);}
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
			userInput = this.getUserInput(limit);
			if(userInput == -1){userInput = this.getValidUserInput(UserMenuReferences.Menus.CONFIRM_MENU, limit);}
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
			userInput = this.getUserInput(limit);
			if(userInput == -1){userInput = this.getValidUserInput(UserMenuReferences.Menus.CONFIRM_MENU, limit);}
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
		}else{
			return UserMenuReferences.Messages.INCONSISTENT_SELECTION.toString();
		}
	}
	
	/**
	 * Displays existing note and note groups
	 * @return operationMessage
	 */
	
	private String goToNotes(){
		
		return "zAAAAAAAAAAAAAAAA";
		
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
			int userInput = this.getUserInput(limit);
			if(userInput == -1){userInput = this.getValidUserInput(UserMenuReferences.Menus.CONFIRM_MENU, limit);}
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
		int userInput = getUserInput(limit);
		if(userInput == -1){userInput = this.getValidUserInput(UserMenuReferences.Menus.MAIN_MENU, limit);}
		
		// Submenu Display
		switch(userInput){
			case 1:
				message = this.create();
				break;
			case 2:
				message = this.goToNotes();
				break;
			case 3:
				message = this.exportJSON();
				break;
			case 4:
				message = this.reset();
				break;
			case 5:
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
	 * Gets integer user input to be used for selection
	 * @param limit
	 * @return userSelection
	 */
	private int getUserInput(int limit){
		String input = scanner.nextLine();
		try{
			int inputInt = Integer.valueOf(input);
			if(limit == 0){
				return inputInt;
			}
			if(inputInt < 1 || inputInt > limit){
				return -1;
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
	private String getUserInput(){
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
	
	
	/**
	 * Converts invalid user input to valid user input by asking user a new input for specified menu
	 * @param displayID
	 * @return
	 */
	private int getValidUserInput(UserMenuReferences.Menus menu, int limit){
		int userInput = -1;
		while(userInput == -1){
			System.out.println(UserMenuReferences.Messages.INCORRECT_INPUT);
			System.out.println(menu);
			userInput = getUserInput(limit);
		}
		return userInput;
	}
	

	
	
}

