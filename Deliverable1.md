# Consolidated Version: Deliverable 1: Music Database

## Application Name
Simple Music Storage (SMS)

## Application Domain
Music storage/playback

## Application Requirements
### General Requirements
- Ability to add, delete, and edit existing entries.
- Database can hold ‘X’ amount of songs.
- Database can hold 4 unique genres of songs.

### Song Requirements
- Each song has a unique ID.
- Each song has 1 to many artists.
- Each song has 1 name.
- Each song belongs to 1 album.
- Each song has 1 genre.
- Each song has a track length.
- Each song has 1 producer.

### Artist Requirements
- Artists are identified by their unique ID.
- Each artist has a name.
- Each artist is signed to 1 record label.

### Album Requirements
- Albums are identified by the album ID and the artists.
- Each album has 1 to many artists.
- Each album has 1 name.
- Each album has 1 producer.
- Each album has 1 label.

### Producer Requirements
- Producers are identified by their unique ID.
- Each producer has 1 name.
- Each producer has 1 record label.

### Record Label Requirements
- Record labels are identified by their unique ID.
- Each record label has 1 name.
- Each record label has 1 address.
- Each record label has 1 phone number.

### Genre Requirements
- Genres are identified by their unique name.
- Each genre has a name.

## Description
### Entities and Attributes
- **SONG (SongID, ArtistID, Name, AlbumID, Genre, TrackLength, ProducerID)**
- **ARTIST (ArtistID, Name, LabelID)**
- **ALBUM (AlbumID, ArtistID, Name, ProducerID, LabelID)**
- **PRODUCER (ProducerID, Name, LabelID)**
- **RECORD_LABEL (LabelID, Name, Address, Phone)**
- **GENRE (Name)**

### Design
The music database will provide a storage hub for users to index music playback. Consumers may search the database by specifying any of the entities to find music that matches their query.

---

## All Original Information

### Deliverable 1: Music Database
**Application Name:** “Insert Name Here”  
**Application Domain:** Music storage/playback

### Requirements
- Ability to add, delete, and edit existing entries.
- Database can hold ‘X’ amount of songs.
- Database can hold 4 unique genres of songs.

### Entity Requirements
#### SONG
- **PK:** (Song_Name, Artist, Song_ID)
- **Other Attributes:** ALBUM, Track_Length, Genre, MUSIC_PRODUCER
- A song may have one or more (collabs) MUSIC_ARTISTs.
- A song may only be on one ALBUM.
- A song may only be produced by one MUSIC_PRODUCER.

#### MUSIC_ARTIST
- **PK:** (ID, Name, RECORD_LABEL)
- **Other Attributes:** ALBUM
- A music artist may only be signed to one RECORD_LABEL.
- A music artist may have zero or more ALBUMs.

#### MUSIC_PRODUCER
- **PK:** (ID, Name, RECORD_LABEL)
- **Other Attributes:** ALBUM
- A music producer may only be with one RECORD_LABEL.
- A music producer may have contributed to zero or more ALBUMs.

#### CONSUMER
- **PK:** (ID, Name)
- **Other Attributes:**  

#### RECORD_LABEL
- **PK:** (ID, Name, Address)
- **Other Attributes:** ALBUMs, MUSIC_ARTISTs
- A record label may have zero or more MUSIC_ARTISTs.
- A record label may have zero or more ALBUMs.

#### ALBUM
- **PK:** (ID, MUSIC_ARTIST, Name)
- **Other Attributes:** MUSIC_PRODUCER, RECORD_LABEL, SONGs
- An album may have one or more (collabs) MUSIC_ARTISTs.
- An album may be produced by one MUSIC_PRODUCER only.
- An album may be put out by one RECORD_LABEL only.
- An album may have 5 or more SONGs.

### Entities
- **Song ID:** Primary Key = Song_ID
  - Song should contain information regarding the song name, genre, length of song, etc.

- **Music Producer:** Primary Key = Music_Producer
  - Music producer should contain information regarding name of producer, production company.

- **Music Artist:** Primary Key = Music_Artist
  - Music artist should contain information regarding name of artist, genre, producer, production company.

- **Consumer:** Primary Key = Consumer
  - Consumer should contain information regarding consumer ID, list of songs.

- **Music Album:** Primary Key = Music_Album
  - Music Album should contain information about song name, how many songs, genre, music producer, production company, music artist.

### Description
**Design:** The music database will provide a storage hub for users to index music playback. Upon the consumer request to access a specific music title, the database will reference the appropriate music ID to ultimately be played by the consumer.

### Requirements Statement

**Landon:**  
**Application Domain:** Music  
**Application Name/Project Title:** MusicDB: Music database that provides information about songs, artists, albums, producers, record labels, and genres.

### Requirements
- Songs are identified by an ID and their artists.
- Each song has a unique ID.
- Each song has 1 to many artists.
- Each song has 1 name.
- Each song belongs to 1 album.
- Each song has 1 genre.
- Each song has a track length.
- Each song has 1 producer.

- Artists are identified by their unique ID.
- Each artist has a name.
- Each artist is signed to 1 record label.

- Albums are identified by the album ID and the artists.
- Each album has 1 to many artists.
- Each album has 1 name.
- Each album has 1 producer.
- Each album has 1 label.

- Producers are identified by their unique ID.
- Each producer has 1 name.
- Each producer has 1 record label.

- Record labels are identified by their unique ID.
- Each record label has 1 name.
- Each record label has 1 address.
- Each record label has 1 phone number.

- Genres are identified by their unique name.
- Each genre has a name.

### Entities
#### SONG
- **PK:** (Song_id, Artist_id)
- **Other Attributes:** Name, Album_id, Genre, Track_length, Producer_id

#### ARTIST
- **PK:** (Artist_id)
- **Other Attributes:** Name, Label_id

#### ALBUM
- **PK:** (Album_id, Artist_id)
- **Other Attributes:** Name, Producer_id, Label_id

#### PRODUCER
- **PK:** (Producer_id)
- **Other Attributes:** Name, Label_id

#### RECORD_LABEL
- **PK:** (Label_id)
- **Other Attributes:** Name, Address, Phone

#### GENRE
- **PK:** (Name)
