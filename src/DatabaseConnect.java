import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DatabaseConnect {
	
	/**user entered arguments*/
	private String url;
	private String user;
	private String password;
	private String driver;
	
	/**mySQL related variables*/
	private static Connection connection;
	private static ResultSet resultSet;
	private static Statement statement;
	private static PreparedStatement pS;
	private static Scanner scanner;
	
	
	public DatabaseConnect(String[] args) {
		url = args[0];
		user = args[1];
		password = args[2];
		driver = args[3];

		System.out.println("Connecting to database through url: " + url + "\n");
		
		connection = establishConnection(url, user, password, driver);
	}
	
	//opens a connection with db when class is initialized 
	public Connection establishConnection(String url, String user, String password, String driver) {
	    try {
    	    Class.forName(driver);
    	    
    	    connection = DriverManager.getConnection(url, user, password);
	    } catch (ClassNotFoundException e) {
            System.out.println("Error: JDBC driver class '" + driver + "' not found.");
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    System.out.println("Error closing database resources: " + sqle.getMessage());
                }
                System.exit(-1);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            System.exit(-1);
        }
	    
	    return connection;
	}
	
	//closes down resources when app is exited
	public void closeResources() {
	    System.out.println("Closing resources...");
	    try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing database resources: " + e.getMessage());
            System.exit(-1);
        }
	}
	
	private void prettyPrintResultSet(ResultSet rs) throws SQLException {
	    int spaces = 0;
	    
	    //get metadata for given set
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int columnCount = rsmd.getColumnCount();
	    
	    //print top of table
	    System.out.print("+");
	    for (int i = 1; i <= columnCount; i++) {
	        System.out.print("-".repeat(25));
	        if (i < columnCount) {
	            System.out.print("|");
	        }
	    }
	    System.out.println("+");
	    
	    //print table header
	    System.out.print("|");
	    for (int i = 1; i <= columnCount; i++) {
	        String columnName = rsmd.getColumnName(i);
	        //print centered
	        spaces = printSpaces(12 - ((columnName.length() / 2) < 1 ? 1 : columnName.length() / 2));
	        System.out.print(columnName);
	        printSpaces(25 - (spaces + columnName.length()));
	        System.out.print("|");
	    }
	    System.out.println();
	    
	  //print header divider
        System.out.print("|");
        for (int i = 1; i <= columnCount; i++) {
            System.out.print("-".repeat(25));
            if (i < columnCount) {
                System.out.print("|");
            }
        }
        System.out.println("|");
	    
	    //print row data
	    while (rs.next()) {
	        System.out.print("|");
	        for (int i = 1; i <= columnCount; i++) {
	            String data = rs.getString(i);
	            //print centered
	            spaces = printSpaces(12 - ((data.length() / 2) < 1 ? 1 : data.length() / 2));
	            System.out.print(data);
	            printSpaces(25 - (spaces + data.length()));
	            System.out.print("|");
	        }
	        System.out.println();
	    }
	    
	    //print bottom of table
        System.out.print("+");
        for (int i = 1; i <= columnCount; i++) {
            System.out.print("-".repeat(25));
            if (i < columnCount) {
                System.out.print("|");
            }
        }
        System.out.println("+");
	}
	
	public void insert() {
	    scanner = new Scanner(System.in);
	    int choice = 0;
        
        //array of all values of tables
        String[] choices = {"ALBUM", "ARTIST", "CREATED", "GENRE", "MADE", "PRODUCER", "RECORD_LABEL",
                            "SONG", "WORKS_FOR", "exit selection"}; 
        do {
            
            //displays choices
            System.out.println("Choose a table to insert values into:");
            for (int i = 1; i <= choices.length; i++) {
                System.out.println("\t" + i + ": " + choices[i-1]);
            }

            try {
                choice = scanner.nextInt();
                System.out.println("choice: " + choice);
                scanner.nextLine(); //eats newline character
                
                switch (choice) {
                case 1:
                    Insert.albumInsert(connection);
                    break;
                case 2:
                    Insert.artistInsert(connection);
                    break;
                case 3:
                    Insert.createdInsert(connection);
                    break;
                case 4:
                    Insert.genreInsert(connection);
                    break;
                case 5:
                    Insert.madeInsert(connection);
                    break;
                case 6:
                    Insert.producerInsert(connection);
                    break;
                case 7:
                    Insert.recordLabelInsert(connection);
                    break;
                case 8:
                    Insert.songInsert(connection);
                    break;
                case 9:
                    Insert.worksForInsert(connection);
                    break;
                case 10:
                    break;
                default:
                    System.out.println("Invalid choice. Please Input a number between 1 and " + choices.length);
                }  
                
            } catch (Exception e) {
                System.out.println("Please input a number between 1 and " + choices.length);
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                e.printStackTrace();
                System.exit(-1);
            }
        } while (choice != 10);
	}
	
	public void printTable(String table) {
	    try {
	        statement = connection.createStatement();
	        
	        resultSet = statement.executeQuery("SELECT * FROM " + table);
	        
	        System.out.println(table);
	        prettyPrintResultSet(resultSet);
	        System.out.println();
	        
	    } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
	}
	
	public int printSpaces(int count) {
		String spaces = "";
		for (int i = 0; i < count ; i++) {
			spaces += " ";
		}
		System.out.print(spaces);
		return spaces.length();
	}
}
