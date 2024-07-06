import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Search {
    
    static Scanner scanner = new Scanner(System.in);
    
    public static ResultSet search(Connection connection, String key) {
        String[] choices;
        String[] songChoices = {"TITLE", "ALBUM", "GENRE", "ARTIST", "PRODUCER", "RECORD LABEL", "EXIT"}; 
        String[] albumChoices = {"TITLE", "ARTIST", "PRODUCER", "RECORD LABEL", "EXIT"};
        String[] artistChoices = {"NAME", "LABEL", "ALBUM", "EXIT"};
        int choice = 0;
        String[] queries;
        String[] songQueries = {"SELECT * FROM SONG WHERE Name = ?;",
                            "SELECT SONG.SongID, SONG.Name, SONG.TrackLength, ALBUM.Name FROM SONG, ALBUM WHERE SONG.AlbumID = ALBUM.AlbumID AND ALBUM.Name = ?;",
                            "SELECT SONG.SongID, SONG.Name, SONG.TrackLength, GENRE.Name FROM SONG, GENRE WHERE SONG.GenreID = GENRE.GenreID AND GENRE.Name = ?;",
                            "SELECT SONG.SongID, SONG.Name, SONG.TrackLength, ARTIST.Name FROM SONG, ARTIST, CREATED WHERE SONG.SongID = CREATED.SongID AND CREATED.ArtistID = ARTIST.ArtistID AND Artist.Name = ?;",
                            "SELECT SONG.SongID, SONG.Name, SONG.TrackLength, Producer.Name FROM SONG, PRODUCER WHERE SONG.ProducerID = PRODUCER.ProducerID AND PRODUCER.Name = ?;",
                            "SELECT SONG.SongID, SONG.Name, SONG.TrackLength, RECORD_LABEL.Name FROM SONG, RECORD_LABEL, WORKS_FOR"
                            + " WHERE SONG.ProducerID = WORKS_FOR.ProducerID AND WORKS_FOR.LabelID = RECORD_LABEL.LabelID AND RECORD_LABEL.Name = ?;"};
        String[] albumQueries = {"SELECT * FROM ALBUM WHERE Name = ?",
                            "SELECT ALBUM.AlbumID, ALBUM.Name, ARTIST.Name FROM ALBUM, ARTIST, MADE WHERE ALBUM.AlbumID = MADE.AlbumID AND ARTIST.ArtistID = MADE.ArtistID AND ARTIST.Name = ?",
                            "SELECT ALBUM.AlbumID, ALBUM.Name, PRODUCER.Name FROM ALBUM, PRODUCER WHERE ALBUM.ProducerID = PRODUCER.ProducerID AND PRODUCER.Name = ?",
                            "SELECT ALBUM.AlbumID, ALBUM.Name, RECORD_LABEL.Name FROM ALBUM, RECORD_LABEL WHERE Album.LabelID = RECORD_LABEL.LabelID AND RECORD_LABEL.Name = ?"};
        String[] artistQueries = {"SELECT * FROM ARTIST WHERE ARTIST.Name = ?",
                            "SELECT ARTIST.ArtistID, ARTIST.Name, RECORD_LABEL.Name FROM ARTIST, RECORD_LABEL WHERE ARTIST.LabelID = RECORD_LABEL.LabelID AND RECORD_LABEL.Name = ?",
                            "SELECT ARTIST.ArtistID, ARTIST.Name, ALBUM.Name FROM ARTIST, ALBUM, MADE WHERE ARTIST.ArtistID = MADE.ArtistID AND ALBUM.AlbumID = MADE.AlbumID AND ALBUM.Name = ?"};
        PreparedStatement query = null;
        ResultSet result = null;
        
        if (key.equals("song")) {
            choices = songChoices;
            queries = songQueries;
        } else if (key.equals("album")) {
            choices = albumChoices;
            queries = albumQueries;
        } else if (key.equals("artist")) {
            choices = artistChoices;
            queries = artistQueries;
        } else {
            System.out.println("There was an error with the key");
            return null;
        }
        
        do {
            System.out.println("Choose the number of which entry you'd like to use to search for the " + key);
            
            for (int i = 1; i <= choices.length; i++) {
                System.out.println("\t" + i + ": " + choices[i-1]);
            }
            
            choice = scanner.nextInt() - 1;
            scanner.nextLine(); //eats newline character
            try {
                if (choice >= 0 && choice < choices.length - 1) {
                    query = connection.prepareStatement(queries[choice]);
                    System.out.println("Please input your value for " + choices[choice]);
                    String value = scanner.nextLine();
                    query.setString(1, value);
                    result = query.executeQuery();
                    return result;
                } else if (choice != choices.length - 1) {
                    System.out.println("Invalid choice. Please input a number between 1 and " + choices.length + "\n");
                }
            } catch (SQLException sqle) {
                System.out.println("Unable to process that transaction try again");
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            } catch (InputMismatchException ime) {
                System.out.println("Please input a number between 1 and " + choices.length + "\n");
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
        } while (choice != choices.length - 1);
        return null;
    }
    
}