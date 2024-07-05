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
	    if (rs == null) {
	        return;
	    }
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
	
	public void search() {
	    int choice = 0;
	    
	    do {
    	    System.out.println("Choose the number of which entry you'd like to search for (e.g. 1 for song)");
    	    System.out.println("\t1: Song");
    	    System.out.println("\t2: Artist");
    	    System.out.println("\t3: Album");
    	    System.out.println("\t4: Quit");
    	    
    	    try {
        	    choice = scanner.nextInt();
        	    scanner.nextLine(); //eats newline character
        	    
        	    switch (choice) {
        	    case 1:
        	        prettyPrintResultSet(Search.search(connection, "song"));
        	        break;
        	    case 2:
        	        prettyPrintResultSet(Search.search(connection, "artist"));
        	        break;
        	    case 3:
        	        prettyPrintResultSet(Search.search(connection, "album"));
        	        break;
        	    case 4:
        	        break;
        	    default:
        	        System.out.println("Invalid choice. Please input a number between 1 and 4" + "\n");
        	    }
    	    } catch (SQLException sqle) {
    	        System.out.println("Unable to print results, please try again.\n");
    	        if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
    	    } catch (InputMismatchException ime) {
                System.out.println("Please input a number between 1 and 4" + "\n");
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
    	    
	    } while (choice != 4);
	}
	
	public void insert() {
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
            }
        } while (choice != 10);
	}
	
	public void update(String table) {
		System.out.println("Table " + table + " has been selected to have its rows updated.\n");
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
		
		String query = "UPDATE " + table + " SET ";
		String setStatement = "";
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
			System.out.println("\n\n  Current update query: UPDATE " + table + 
					"\n                        SET " + setStatement +
					"\n                        WHERE " + conditionStatement + ";\n");
			updateMenu();
			choice = scanner.nextLine().toLowerCase().charAt(0);
			System.out.println("");
			
			switch (choice) {
				case 'v':
					printTable(table);
					break;	
				case 's':
					System.out.println("Enter the assignments for the SET clause (e.g., Age = 19 or Name = 'John')\n");
					System.out.println("Multiple assignments can be used in the query, entered one at a time.\nDo not add commas between assignment. " +
									   "It will be done for you.\n");
					
					String newSetStatment;
					newSetStatment = scanner.nextLine().trim();
					System.out.println();
					if (setStatement.equals("")) {
						setStatement += newSetStatment;
					} else {
						setStatement += ", " + newSetStatment;
					}
					break;
					
				case 'w':
					System.out.println("Enter the condition for the WHERE clause (e.g., Name = 'John')\n");
					System.out.println("Multiple conditions can be used in the query, entered one at a time." +
								       "\nDo not add the keyword 'AND'. It will be done for you.\n");
					
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
						String finalQuery = query + setStatement + " WHERE " + conditionStatement + ";";
	                    System.out.println("Executing query: " + finalQuery);
	                    statement = connection.createStatement();
	                    statement.execute(finalQuery);
	                    System.out.println("\nQuery executed successfully.\n");
					} catch (SQLException e) {
						System.out.println("\n" + e.getMessage() + "\n");
					}
					query = "UPDATE " + table + " SET ";
					setStatement = "";
					conditionStatement = "";
					break;
				case 't':
					//switch table that will be updated
					Main.chooseTableUpdateOrDelete('u');
					return;
				case 'c':
					//clear current query
					query = "UPDATE " + table + " SET ";
					setStatement = "";
					conditionStatement = "";
					System.out.println("Query successfully cleared\n");
				case 'e':
					//exit update menu
					break;
				default:
					System.out.println(choice + " is not a valid menu option.");
			}
			
		} while (choice != 'e');
	}
	
	//menu for update menu function
	public void updateMenu() {
		System.out.println("Choose an option:");
		System.out.println("\tv: view table");
		System.out.println("\ts: add to set clause");
		System.out.println("\tw: add to where clause");
		System.out.println("\tx: execute query");
		System.out.println("\tt: switch tables");
		System.out.println("\tc: clear query");
		System.out.println("\te: exit selection");
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
			System.out.println("\n\n  Current deletion query: DELETE FROM " + table + 
					"\n                          WHERE " + conditionStatement + ";\n");
			deleteMenu();
			choice = scanner.nextLine().toLowerCase().charAt(0);
			System.out.println("");
			
			switch (choice) {
				case 'v':
					//view current table
					printTable(table);
					break;
				case 'w':
					//build the WHERE clause for the sql query
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
					//execute query then clear query
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
				case 't':
					//switch to another table to delete data from 'd' is for delete
					Main.chooseTableUpdateOrDelete('d');
					return;
				case 'c':
					//reset query
					query = "DELETE FROM " + table + "WHERE ";
					conditionStatement = "";
					System.out.println("Query successfully cleared\n");
				case 'e':
					//exit delete menu
					break;
				default:
					System.out.println(choice + " is not a valid menu option.");
			}
			
		} while (choice != 'e');
	}

	//menu for delete data function
	public void deleteMenu() {
		System.out.println("Choose an option:");
		System.out.println("\tv: view table");
		System.out.println("\tw: add to where clause");
		System.out.println("\tx: execute query");
		System.out.println("\tt: switch tables");
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