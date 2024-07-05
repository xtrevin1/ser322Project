import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	private static DatabaseConnect db;
	private static Scanner scanner;
	
	/**Entry point into application*/
	public static void main(String[] args) {
		if (args.length != 4) {
			System.out.println("Wrong number of arguments provided. Usage:\n");
			System.out.println("java src.Main <url> <user> <password> <driver>");
			System.exit(1);
		}
		
		System.out.println("Welcome to Simple Music Storage (SMS)!\n");
		
		//Connect to database through DatabaseConnect class
		db = new DatabaseConnect(args);
		
		scanner = new Scanner(System.in);
		char choice;
		
		do {
			printMenu();
			choice = scanner.next().toLowerCase().charAt(0);
			System.out.println();
			
			switch (choice) {
				case '1':
					chooseTablePrint();
					break;
				case '2':
					//search
					break;
				case '3':
					db.insert();
					break;
				case '4':
					chooseTableUpdateOrDelete('u');
					break;
				case '5':
					chooseTableUpdateOrDelete('d');
					break;
				case 'q':
					break;
				default:
					System.out.println("Not a valid choice\n");
			}
			
		} while (choice != 'q');
		
	    db.closeResources();
		System.out.println("Exiting application...");
	}
	
	public static void printMenu() {
		System.out.println("Enter menu choice:");
		System.out.println("\t1: print a table in the database");
		System.out.println("\t2: search");
		System.out.println("\t3: insert data");
		System.out.println("\t4: update data");
		System.out.println("\t5: delete data");
		System.out.println("\tq: quit");
	}
	
	public static void chooseTablePrint() {
	    int choice = 0;
	    
	    //array of all values of tables
	    String[] choices = {"ALBUM", "ARTIST", "CREATED", "GENRE", "MADE", "PRODUCER", "RECORD_LABEL",
	                        "SONG", "WORKS_FOR", "print all", "exit selection"}; 
		do {
		    
		    //displays choices
		    System.out.println("Choose a table to print:");
		    for (int i = 1; i <= choices.length; i++) {
		        System.out.println("\t" + i + ": " + choices[i-1]);
		    }

			try {
    			choice = scanner.nextInt() - 1;
    			System.out.println();
    			
    			//do action based off user choice
    			if (choice < 9 && choice >= 0) {
    			    db.printTable(choices[choice]);
    			} else if (choice == 9){
    			    for (int i = 0; i < choices.length - 2; i++) {
    			        db.printTable(choices[i]);
    			    }
    			} else if (choice == 10) {
    			    
    			} else {
    			    System.out.println("Not a valid choice. Try aqain.");
    			}
			} catch (InputMismatchException e) {
			    System.out.println("Please input a number between 1 and " + choices.length);
			    scanner.nextLine();
			}
			
		} while (choice != 10);
	}
	
	public static void chooseTableUpdateOrDelete(char selection) {
		int choice = 0;
	    
	    //array of all values of tables
	    String[] choices = {"ALBUM", "ARTIST", "CREATED", "GENRE", "MADE", "PRODUCER", "RECORD_LABEL",
	                        "SONG", "WORKS_FOR", "exit selection"}; 
		do {
		    
		    System.out.print("Choose a table to ");
		    
		    if (selection == 'd') {
		    	System.out.println("delete data from:");
		    } else if (selection == 'u') {
		    	System.out.println("update data in:");
		    }
		    
		    //displays choices
		    for (int i = 1; i <= choices.length; i++) {
		        System.out.println("\t" + i + ": " + choices[i-1]);
		    }

			try {
    			choice = scanner.nextInt() - 1;
    			System.out.println();
    			//do action based off user choice
    			if (choice < 9 && choice >= 0) {
    			    if (selection == 'd') {
    				db.delete(choices[choice]);
    			    } else if (selection == 'u') {
    				db.update(choices[choice]);
    			    }
    				return;
    			} else if (choice == 9) {
    				return;
    			} else {
    			    System.out.println("Not a valid choice. Try aqain.\n");
    			}
			} catch (InputMismatchException e) {
			    System.out.println("\nPlease input a number between 1 and " + choices.length + ".\n");
			    scanner.nextLine();
			}
			
		} while (choice != 10);
	}
}
