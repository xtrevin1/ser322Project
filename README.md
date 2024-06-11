# MusicDB: Music Database Project

## Project Overview
This project involves creating a comprehensive database system for managing music-related data, including songs, artists, albums, producers, record labels, and genres. The database will be embedded in a Java application with a user interface to facilitate music storage, retrieval, and playback.

## Application Domain
Music storage and playback

## Project Deliverables
### Deliverable 1: Proposal
- **Application Domain:** Music
- **Project Title:** MusicDB
- **Application Requirements:**
  - Ability to add, delete, and edit existing entries.
  - Database can hold ‘X’ amount of songs.
  - Database can hold 4 unique genres of songs.
- **Description and Requirements Statement:**
  - The database will provide a storage hub for users to index music playback. Users can search the database by specifying any entities to find music that matches their query.

### Deliverable 2: System Analysis and Modeling
- **Entity Requirements:**
  - **SONG:**
    - **Primary Key:** (Song_Name, Artist, Song_ID)
    - **Attributes:** ALBUM, Track_Length, Genre, MUSIC_PRODUCER
    - A song may have one or more (collabs) MUSIC_ARTISTs.
    - A song may only be on one ALBUM.
    - A song may only be produced by one MUSIC_PRODUCER.
  - **MUSIC_ARTIST:**
    - **Primary Key:** (ID, Name, RECORD_LABEL)
    - **Attributes:** ALBUM
    - A music artist may only be signed to one RECORD_LABEL.
    - A music artist may have zero or more ALBUMs.
  - **MUSIC_PRODUCER:**
    - **Primary Key:** (ID, Name, RECORD_LABEL)
    - **Attributes:** ALBUM
    - A music producer may only be with one RECORD_LABEL.
    - A music producer may have contributed to zero or more ALBUMs.
  - **GENRE:**
    - **Primary Key:** (Name)
  - **RECORD_LABEL:**
    - **Primary Key:** (ID, Name, Address)
    - **Attributes:** ALBUMs, MUSIC_ARTISTs
    - A record label may have zero or more MUSIC_ARTISTs.
    - A record label may have zero or more ALBUMs.
  - **ALBUM:**
    - **Primary Key:** (ID, MUSIC_ARTIST, Name)
    - **Attributes:** MUSIC_PRODUCER, RECORD_LABEL, SONGs
    - An album may have one or more (collabs) MUSIC_ARTISTs.
    - An album may be produced by one MUSIC_PRODUCER only.
    - An album may be put out by one RECORD_LABEL only.
    - An album may have 5 or more SONGs.

### Deliverable 3: Database Implementation
- Convert the E-R model into a relational model and implement the database tables.
- Populate the tables with appropriate data.
- Write SQL queries to demonstrate the usefulness of the database.
  - **Entities and Attributes:**
    - **SONG (SongID, ArtistID, Name, AlbumID, Genre, TrackLength, ProducerID)**
    - **ARTIST (ArtistID, Name, LabelID)**
    - **ALBUM (AlbumID, ArtistID, Name, ProducerID, LabelID)**
    - **PRODUCER (ProducerID, Name, LabelID)**
    - **RECORD_LABEL (LabelID, Name, Address, Phone)**
    - **GENRE (Name)**

### Deliverable 4: Application Implementation and Demo/Presentation
- Build a Java-based application that uses the database.
- Provide a UI for entering query parameters and displaying results.
- The application must display a menu and return to that menu after each action without relaunching the application.
- **Deliverables:**
  - Updated E-R diagram(s) (if any updates were made).
  - Complete source code of Java application.
  - README with instructions on creating the database and setting up the application.
  - Video demonstrating the application and explaining the structure of the code.
