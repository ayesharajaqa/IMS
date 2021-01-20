package Utilities;

public class ErrorHandler {
	
	//Accepts an exception and prints it to the console
	
	public static void errorHandler(Exception exception) {
		
		System.out.println("Whoops! It looks like an error has occured. Please try again, or inform an administrator.");
		
		exception.printStackTrace();

	}
	
}
