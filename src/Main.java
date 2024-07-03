import java.util.Scanner;

public class Main {
	
	private static DatabaseConnect db;
	private static Scanner scanner;
	
	private static char choice;
	
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
					//insert
					break;
				case '4':
					//update
					break;
				case '5':
					//delete
					break;
				case 'q':
					break;
				default:
					System.out.println("Not a valid choice\n");
			}
			
		} while (choice != 'q');
		
		scanner.close();
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
		do {
			System.out.println("Choose a table to print:");
			System.out.println("\t1: ALBUM");
			System.out.println("\t2: ARTIST");
			System.out.println("\t3: CREATED");
			System.out.println("\t4: GENRE");
			System.out.println("\t5: MADE");
			System.out.println("\t6: PRODUCER");
			System.out.println("\t7: RECORD_LABEL");
			System.out.println("\t8: SONG");
			System.out.println("\t9: WORKS_FOR");
			System.out.println("\ta: print all");
			System.out.println("\te: exit selection");
			
			choice = scanner.next().toLowerCase().charAt(0);
			System.out.println();
			
			switch (choice) {
				case '1':
					db.printAlbumTable();
					break;
				case '2':
					db.printArtistTable();
					break;
				case '3':
					db.printCreatedTable();
					break;
				case '4':
					db.printGenreTable();
					break;
				case '5':
					db.printMadeTable();
					break;
				case '6':
					db.printProducerTable();
					break;
				case '7':
					db.printRecordLabelTable();
					break;
				case '8':
					db.printSongTable();
					break;
				case '9':
					db.printWorksForTable();
					break;
				case 'a':
					db.printAll();
					break;
				case 'e':
					break;
				default:
					System.out.println("Not a valid choice. Try aqain.");
					
			}
		} while (choice != 'e');
	}
}
