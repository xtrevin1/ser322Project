import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DatabaseConnect {
	
	/**user entered arguments*/
	private String url;
	private String user;
	private String password;
	private String driver;
	private static Scanner scanner = new Scanner(System.in);
	
	/**mySQL related variables*/
	private static Connection connection;
	private static ResultSet resultSet;
	private static Statement statement;
	private static PreparedStatement pS;
	
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
            if (pS != null) {
                pS.close();
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
	
	public void delete(String table) {
		System.out.println("Table " + table + " has been selected to have rows deleted from it.\n");
		ResultSetMetaData rsmd = null;
		int columnCount = 0;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM " + table);
			rsmd = resultSet.getMetaData();
		    columnCount = rsmd.getColumnCount();			
		} catch (SQLException e) {
			System.out.println("\n" + e.getMessage() + "\n");
		}
		
		char choice;

		String query = "DELETE FROM " + table + " WHERE ";
		String conditionStatement = "";
		
		
		do {
			System.out.print("Column names from " + table + ": ");
			for (int i = 1; i < columnCount + 1; i++) {
				try {
					System.out.print(rsmd.getColumnName(i));
					if (i < columnCount) {
						System.out.print(", ");
					}					
				} catch (SQLException e) {
					System.out.print("\n" + e.getMessage() + "\n");					
				}
			}
			System.out.println("\n\n  Current deletion query: DELETE FROM " + table + " WHERE " + conditionStatement + ";\n");
			deleteMenu();
			choice = scanner.nextLine().toLowerCase().charAt(0);
			System.out.println("");
			
			switch (choice) {
				case 'v':
					printTable(table);
					break;
				case 'b':
					System.out.println("Enter the condition for the WHERE clause (e.g., Name = 'John')\n");
					System.out.println("Multiple conditions can be used in the query, entered one at a time.\nDo not add the keyword 'AND'. It will be done for you.\n");
					
					String newCondition;
					newCondition = scanner.nextLine().trim();
					System.out.println();
					if (conditionStatement.equals("")) {
						conditionStatement += newCondition;
					} else {
						conditionStatement += " AND " + newCondition;
					}
					break;
				case 'x':	
					try {
						String finalQuery = query + conditionStatement + ";";
	                    System.out.println("Executing query: " + finalQuery);
	                    statement = connection.createStatement();
	                    statement.execute(finalQuery);
	                    System.out.println("\nQuery executed successfully.\n");
					} catch (SQLException e) {
						System.out.println("\n" + e.getMessage() + "\n");
					}
					query = "DELETE FROM " + table + " WHERE ";
					conditionStatement = "";
					break;
				case 's':
					Main.chooseTableDelete();
					return;
				case 'c':
					query = "DELETE FROM " + table + "WHERE ";
					conditionStatement = "";
					System.out.println("Query successfully cleared\n");
				case 'e':
					query = "DELETE FROM " + table + "WHERE ";
					conditionStatement = "";
					break;
				default:
					System.out.println(choice + " is not a valid menu option.");
			}
			
		} while (choice != 'e');
	}

	public void deleteMenu() {
		System.out.println("Choose an option:");
		System.out.println("\tv: view table");
		System.out.println("\tb: build query");
		System.out.println("\tx: execute query");
		System.out.println("\ts: switch tables");
		System.out.println("\tc: clear query");
		System.out.println("\te: exit selection");
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