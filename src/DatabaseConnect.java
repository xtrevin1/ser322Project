import java.sql.*;

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
	private static PreparedStatement preparedStatement;
	
	
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
	
	public void printAlbumTable() {
		try {
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(
					"SELECT * FROM ALBUM");
			
			System.out.println("ALBUM\n");
			System.out.println("AlbumID |         Name         | LabelID | ProducerID");
			System.out.println("-----------------------------------------------------");
			while (resultSet.next()) {
				printSpaces(7 - String.valueOf(resultSet.getInt(1)).length());
				System.out.print(resultSet.getInt(1) + " | ");
				
				printSpaces(20 - resultSet.getString(2).length());
				System.out.print(resultSet.getString(2) + " | ");
				
				printSpaces(7 - String.valueOf(resultSet.getInt(3)).length());
				System.out.print(resultSet.getInt(3) + " | ");
				
				printSpaces(10 - String.valueOf(resultSet.getInt(4)).length());
				System.out.println(resultSet.getInt(4));
			}
			System.out.println();
		} catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
		}
	}
	
	public void printArtistTable() {
		try {
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(
					"SELECT * FROM ARTIST");
			
			System.out.println("ARTIST\n");
			System.out.println("ArtistID |         Name         | LabelID");
			System.out.println("-----------------------------------------");
			while (resultSet.next()) {
				printSpaces(8 - String.valueOf(resultSet.getInt(1)).length());
				System.out.print(resultSet.getInt(1) + " | ");
				
				printSpaces(20 - resultSet.getString(2).length());
				System.out.print(resultSet.getString(2) + " | ");
				
				printSpaces(7 - String.valueOf(resultSet.getInt(3)).length());
				System.out.println(resultSet.getInt(3));
			}
			System.out.println();
			
		} catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
		} 
	}
	
	public void printCreatedTable() {
		try {
		    statement = connection.createStatement();
			
			resultSet = statement.executeQuery(
					"SELECT * FROM CREATED");
			
			System.out.println("CREATED\n");
			System.out.println("ArtistID | SongID");
			System.out.println("-----------------");
			while (resultSet.next()) {
				printSpaces(8- String.valueOf(resultSet.getInt(1)).length());
				System.out.print(resultSet.getInt(1) + " | ");
								
				printSpaces(6 - String.valueOf(resultSet.getInt(2)).length());
				System.out.println(resultSet.getInt(2));
			}
			System.out.println();
		} catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
		} 	
	}
	
	public void printGenreTable() {
		try {
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(
					"SELECT * FROM GENRE");
			
			System.out.println("GENRE\n");
			System.out.println("GenreID |         Name         ");
			System.out.println("-------------------------------");
			while (resultSet.next()) {
				printSpaces(7 - String.valueOf(resultSet.getInt(1)).length());
				System.out.print(resultSet.getInt(1) + " | ");
				
				printSpaces(21 - resultSet.getString(2).length());
				System.out.println(resultSet.getString(2));
			}
			System.out.println();
		} catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
		} 
	}

	public void printMadeTable() {
		try {
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(
					"SELECT * FROM MADE");
			
			System.out.println("MADE\n");
			System.out.println("ArtistID | AlbumID");
			System.out.println("------------------");
			while (resultSet.next()) {
				printSpaces(8 - String.valueOf(resultSet.getInt(1)).length());
				System.out.print(resultSet.getInt(1) + " | ");
				
				printSpaces(7 - String.valueOf(resultSet.getInt(2)).length());
				System.out.println(resultSet.getInt(2));
			}
			System.out.println();
		} catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
		}
	}
	
	public void printProducerTable() {
		try {
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(
					"SELECT * FROM PRODUCER");
			
			System.out.println("PRODUCER\n");
			System.out.println("ProducerID |         Name         ");
			System.out.println("----------------------------------");
			while (resultSet.next()) {
				printSpaces(10 - String.valueOf(resultSet.getInt(1)).length());
				System.out.print(resultSet.getInt(1) + " | ");
				
				printSpaces(21 - resultSet.getString(2).length());
				System.out.println(resultSet.getString(2));
			}
			System.out.println();
		} catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
		}
	}

	public void printRecordLabelTable() {
		try {
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(
					"SELECT * FROM RECORD_LABEL");
			
			System.out.println("RECORD_LABEL\n");
			System.out.println("LabelID |         Name         |          Adress          |    PhoneNumber");
			System.out.println("--------------------------------------------------------------------------");
			while (resultSet.next()) {
				printSpaces(7 - String.valueOf(resultSet.getInt(1)).length());
				System.out.print(resultSet.getInt(1) + " | ");
				
				printSpaces(20 - resultSet.getString(2).length());
				System.out.print(resultSet.getString(2) + " | ");
				
				printSpaces(24 - resultSet.getString(3).length());
				System.out.print(resultSet.getString(3) + " | ");
				
				printSpaces(14 - resultSet.getString(4).length());
				System.out.println(resultSet.getString(4));
			}
			System.out.println();
		} catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
		}
	}
	
	public void printSongTable() {
		try {
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(
					"SELECT * FROM SONG");
			
			System.out.println("SONG\n");
			System.out.println("SongID |         Name         | TrackLength | AlbumID | GenreID | ProducerID");
			System.out.println("----------------------------------------------------------------------------");
			while (resultSet.next()) {
				printSpaces(6 - String.valueOf(resultSet.getInt(1)).length());
				System.out.print(resultSet.getInt(1) + " | ");
				
				printSpaces(20 - resultSet.getString(2).length());
				System.out.print(resultSet.getString(2) + " | ");
				
				printSpaces(11 - (resultSet.getTime(3).toString().length()));
				System.out.print(resultSet.getTime(3) + " | ");
				
				printSpaces(7 - String.valueOf(resultSet.getInt(4)).length());
				System.out.print(resultSet.getInt(4) + " | ");
				
				printSpaces(7 - String.valueOf(resultSet.getInt(5)).length());
				System.out.print(resultSet.getInt(5) + " | ");
				
				printSpaces(10 - String.valueOf(resultSet.getInt(6)).length());
				System.out.println(resultSet.getInt(6));
			}
			System.out.println();
		} catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
		}
	}
	
	public void printWorksForTable() {
		try {
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(
					"SELECT * FROM WORKS_FOR");
			
			System.out.println("WORKS_FOR\n");
			System.out.println("ProducerID | LabelID");
			System.out.println("--------------------");
			while (resultSet.next()) {
				printSpaces(10 - String.valueOf(resultSet.getInt(1)).length());
				System.out.print(resultSet.getInt(1) + " | ");
				
				printSpaces(7 - String.valueOf(resultSet.getInt(2)).length());
				System.out.println(resultSet.getInt(2));
			}
			System.out.println();
		} catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
		}
	}
	
	public void printAll() {
		printAlbumTable();
		System.out.println("\n");
		printArtistTable();
		System.out.println("\n");
		printCreatedTable();
		System.out.println("\n");
		printGenreTable();
		System.out.println("\n");
		printMadeTable();
		System.out.println("\n");
		printProducerTable();
		System.out.println("\n");
		printRecordLabelTable();
		System.out.println("\n");
		printSongTable();
		System.out.println("\n");
		printWorksForTable();
	}
	
	public void printSpaces(int count) {
		String spaces = "";
		for (int i = 0; i < count ; i++) {
			spaces += " ";
		}
		System.out.print(spaces);
	}

}
