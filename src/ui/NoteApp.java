package ui;

public class NoteApp {

	public static void main(String[] args) {
		// If you want to activate clear previous menus feature,
        // you need to call UserMenu with true boolean parameter
        // UserMenu ui = new UserMenu(true);
        // In Eclipse clear method does not work because Eclipse does not allow but terminal
		
		UserMenu userMenu = new UserMenu();
		userMenu.run();
	}

}
