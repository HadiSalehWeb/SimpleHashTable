package uebungsblatt3;

public class SongImplementation implements Song {
    String songName;
    String[] artists;
    String albumName;

    public SongImplementation(String songName, String[] artists, String albumName) {
        this.songName = songName;
        this.artists = artists;
        this.albumName = albumName;
    }

    @Override
    public String getSongName() {
        return songName;
    }

    @Override
    public String getAlbumName() {
        return albumName;
    }

    @Override
    public String[] getArtists() {
        return artists;
    }

    @Override
    public String toString() {
        String artistsString = "";

        for (int i = 0; i < artists.length; i++) {
            artistsString += artists[i];
            if (i < artists.length - 1)
                artistsString += ";";
        }

        return '"' + songName + '"' + " from album " + '"' + albumName + '"' + " by " + '"' + artistsString + '"';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SongImplementation))
            return false;
        SongImplementation song = (SongImplementation) obj;

        if (!songName.equals(song.songName) || !albumName.equals(song.albumName) || artists.length != song.artists.length)
            return false;

        for (int i = 0; i < artists.length; i++)
            if (!artists[i].equals(song.artists[i]))
                return false;
        return true;
    }
}
