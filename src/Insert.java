import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Insert {
    
    static Scanner scanner = new Scanner(System.in);
    
    public static void recordLabelInsert(Connection connection) {
        int labelID = 0;
        String name = null;
        String address = null;
        String phoneNumber = null;
        
        try { 
            System.out.print("Enter labelID (int): ");
            labelID = scanner.nextInt();
            scanner.nextLine(); //eats newline character
            
            System.out.print("Enter name (String): ");
            name = scanner.nextLine();
            
            System.out.print("Enter address (String): ");
            address = scanner.nextLine();
            
            System.out.print("Enter phoneNumber (String): ");
            phoneNumber = scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Incorrect input, unable to add data. Input must be: (<int>, <String>, <String>, <String>)");
            return;
        }
        try {
            connection.setAutoCommit(false);
            PreparedStatement pS = connection.prepareStatement("INSERT INTO RECORD_LABEL VALUES (?, ?, ?, ?)");
            
            pS.setInt(1, labelID);
            pS.setString(2, name);
            pS.setString(3, address);
            pS.setString(4, phoneNumber);
            pS.executeUpdate();
            System.out.println("Your data was successfully inserted!");
            connection.commit();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Rollback successful");
                } catch (SQLException e1) {
                    System.out.println("Unable to rollback transaction");
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                connection.setAutoCommit(false);
            } catch (SQLException se) {
                System.out.println("Unable to set autocommit back");
            }
        }
    }
    
    public static void artistInsert(Connection connection) {
        int artistID = 0;
        String name = null;
        int labelID = 0;
        
        try { 
            System.out.print("Enter artistID (int): ");
            artistID = scanner.nextInt();
            scanner.nextLine(); //eats newline character
            
            System.out.print("Enter name (String): ");
            name = scanner.nextLine();
            
            System.out.print("Enter labelID (int): ");
            labelID = scanner.nextInt();
            scanner.nextLine(); //eats newline character
        } catch (InputMismatchException e) {
            System.out.println("Incorrect input, unable to add data. Input must be: (<int>, <String>, <int>");
            return;
        }
        try {
            connection.setAutoCommit(false);
            PreparedStatement pS = connection.prepareStatement("INSERT INTO ARTIST VALUES (?, ?, ?)");
            
            pS.setInt(1, artistID);
            pS.setString(2, name);
            pS.setInt(3, labelID);
            pS.executeUpdate();
            System.out.println("Your data was successfully inserted!");
            connection.commit();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Rollback successful");
                } catch (SQLException e1) {
                    System.out.println("Unable to rollback transaction");
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                connection.setAutoCommit(false);
            } catch (SQLException se) {
                System.out.println("Unable to set autocommit back");
            }
        }
    }
    
    public static void producerInsert(Connection connection) {
        int producerID = 0;
        String name = null;
        
        try { 
            System.out.print("Enter producerID (int): ");
            producerID = scanner.nextInt();
            scanner.nextLine(); //eats newline character
            
            System.out.print("Enter name (String): ");
            name = scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Incorrect input, unable to add data. Input must be: (<int>, <String>");
            return;
        }
        try {
            connection.setAutoCommit(false);
            PreparedStatement pS = connection.prepareStatement("INSERT INTO PRODUCER VALUES (?, ?)");
            
            pS.setInt(1, producerID);
            pS.setString(2, name);
            pS.executeUpdate();
            System.out.println("Your data was successfully inserted!");
            connection.commit();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Rollback successful");
                } catch (SQLException e1) {
                    System.out.println("Unable to rollback transaction");
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                connection.setAutoCommit(false);
            } catch (SQLException se) {
                System.out.println("Unable to set autocommit back");
            }
        }
    }
    
    public static void genreInsert(Connection connection) {
        int genreID = 0;
        String name = null;
        
        try { 
            System.out.print("Enter genreID (int): ");
            genreID = scanner.nextInt();
            scanner.nextLine(); //eats newline character
            
            System.out.print("Enter name (String): ");
            name = scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Incorrect input, unable to add data. Input must be: (<int>, <String>");
            return;
        }
        try {
            connection.setAutoCommit(false);
            PreparedStatement pS = connection.prepareStatement("INSERT INTO GENRE VALUES (?, ?)");
            
            pS.setInt(1, genreID);
            pS.setString(2, name);
            pS.executeUpdate();
            System.out.println("Your data was successfully inserted!");
            connection.commit();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Rollback successful");
                } catch (SQLException e1) {
                    System.out.println("Unable to rollback transaction");
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                connection.setAutoCommit(false);
            } catch (SQLException se) {
                System.out.println("Unable to set autocommit back");
            }
        }
    }
    
    public static void albumInsert(Connection connection) {
        int albumID = 0;
        String name = null;
        int labelID = 0;
        int producerID = 0;
        
        
        try { 
            System.out.print("Enter albumID (int): ");
            albumID = scanner.nextInt();
            scanner.nextLine(); //eats newline character
            
            System.out.print("Enter name (String): ");
            name = scanner.nextLine();
            
            System.out.print("Enter labelID (int): ");
            labelID = scanner.nextInt();
            scanner.nextLine(); //eats newline character
            
            System.out.print("Enter producerID (int): ");
            producerID = scanner.nextInt();
            scanner.nextLine(); //eats newline character
        } catch (InputMismatchException e) {
            System.out.println("Incorrect input, unable to add data. Input must be: (<int>, <String>, <int>, <int>");
            return;
        }
        try {
            connection.setAutoCommit(false);
            PreparedStatement pS = connection.prepareStatement("INSERT INTO ALBUM VALUES (?, ?, ?, ?)");
            
            pS.setInt(1, albumID);
            pS.setString(2, name);
            pS.setInt(3, labelID);
            pS.setInt(4, producerID);
            pS.executeUpdate();
            System.out.println("Your data was successfully inserted!");
            connection.commit();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Rollback successful");
                } catch (SQLException e1) {
                    System.out.println("Unable to rollback transaction");
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                connection.setAutoCommit(false);
            } catch (SQLException se) {
                System.out.println("Unable to set autocommit back");
            }
        }
    }
    
    public static void songInsert(Connection connection) {
        int songID = 0;
        String name = null;
        int trackLength = 0;
        int albumID = 0;
        int genreID = 0;
        int producerID = 0;
        
        
        try { 
            System.out.print("Enter songID (int): ");
            songID = scanner.nextInt();
            scanner.nextLine(); //eats newline character
            
            System.out.print("Enter name (String): ");
            name = scanner.nextLine();
            
            System.out.print("Enter tracklength in seconds (int): ");
            trackLength = scanner.nextInt();
            scanner.nextLine(); //eats newline character
            
            System.out.print("Enter albumID (int): ");
            albumID = scanner.nextInt();
            scanner.nextLine(); //eats newline character
            
            System.out.print("Enter genreID (int): ");
            genreID = scanner.nextInt();
            scanner.nextLine(); //eats newline character
            
            System.out.print("Enter producerID (int): ");
            producerID = scanner.nextInt();
            scanner.nextLine(); //eats newline character
        } catch (InputMismatchException e) {
            System.out.println("Incorrect input, unable to add data. Input must be: (<int>, <String>, <int>, <int>, <int>, <int>");
            return;
        }
        try {
            connection.setAutoCommit(false);
            PreparedStatement pS = connection.prepareStatement("INSERT INTO SONG VALUES (?, ?, ?, ?, ?, ?)");
            
            pS.setInt(1, songID);
            pS.setString(2, name);
            pS.setInt(3, trackLength);
            pS.setInt(4, albumID);
            pS.setInt(5, genreID);
            pS.setInt(6, producerID);
            pS.executeUpdate();
            System.out.println("Your data was successfully inserted!");
            connection.commit();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Rollback successful");
                } catch (SQLException e1) {
                    System.out.println("Unable to rollback transaction");
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                connection.setAutoCommit(false);
            } catch (SQLException se) {
                System.out.println("Unable to set autocommit back");
            }
        }
    }
    
    public static void createdInsert(Connection connection) {
        int artistID = 0;
        int songID = 0;
        
        try { 
            System.out.print("Enter artistID (int): ");
            artistID = scanner.nextInt();
            scanner.nextLine(); //eats newline character
            
            System.out.print("Enter songID (int): ");
            songID = scanner.nextInt();
            scanner.nextLine(); //eats newline character
        } catch (InputMismatchException e) {
            System.out.println("Incorrect input, unable to add data. Input must be: (<int>, <int>");
            return;
        }
        try {
            connection.setAutoCommit(false);
            PreparedStatement pS = connection.prepareStatement("INSERT INTO CREATED VALUES (?, ?)");
            
            pS.setInt(1, artistID);
            pS.setInt(2, songID);
            pS.executeUpdate();
            System.out.println("Your data was successfully inserted!");
            connection.commit();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Rollback successful");
                } catch (SQLException e1) {
                    System.out.println("Unable to rollback transaction");
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                connection.setAutoCommit(false);
            } catch (SQLException se) {
                System.out.println("Unable to set autocommit back");
            }
        }
    }
    
    public static void madeInsert(Connection connection) {
        int artistID = 0;
        int albumID = 0;
        
        try { 
            System.out.print("Enter artistID (int): ");
            artistID = scanner.nextInt();
            scanner.nextLine(); //eats newline character
            
            System.out.print("Enter albumID (int): ");
            albumID = scanner.nextInt();
            scanner.nextLine(); //eats newline character
        } catch (InputMismatchException e) {
            System.out.println("Incorrect input, unable to add data. Input must be: (<int>, <int>");
            return;
        }
        try {
            connection.setAutoCommit(false);
            PreparedStatement pS = connection.prepareStatement("INSERT INTO MADE VALUES (?, ?)");
            
            pS.setInt(1, artistID);
            pS.setInt(2, albumID);
            pS.executeUpdate();
            System.out.println("Your data was successfully inserted!");
            connection.commit();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Rollback successful");
                } catch (SQLException e1) {
                    System.out.println("Unable to rollback transaction");
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                connection.setAutoCommit(false);
            } catch (SQLException se) {
                System.out.println("Unable to set autocommit back");
            }
        }
    }
    
    public static void worksForInsert(Connection connection) {
        int producerID = 0;
        int labelID = 0;
        
        try { 
            System.out.print("Enter producerID (int): ");
            producerID = scanner.nextInt();
            scanner.nextLine(); //eats newline character
            
            System.out.print("Enter labelID (int): ");
            labelID = scanner.nextInt();
            scanner.nextLine(); //eats newline character
        } catch (InputMismatchException e) {
            System.out.println("Incorrect input, unable to add data. Input must be: (<int>, <int>");
            return;
        }
        try {
            connection.setAutoCommit(false);
            PreparedStatement pS = connection.prepareStatement("INSERT INTO WORKS_FOR VALUES (?, ?)");
            
            pS.setInt(1, producerID);
            pS.setInt(2, labelID);
            pS.executeUpdate();
            System.out.println("Your data was successfully inserted!");
            connection.commit();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Rollback successful");
                } catch (SQLException e1) {
                    System.out.println("Unable to rollback transaction");
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                connection.setAutoCommit(false);
            } catch (SQLException se) {
                System.out.println("Unable to set autocommit back");
            }
        }
    }
}