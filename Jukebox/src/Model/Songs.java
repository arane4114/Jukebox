package Model;

import java.util.ArrayList;
import java.util.List;

public class Songs {
	
	List<Song> songs = new ArrayList<Song>();
	
	public Songs(){
		songs = new ArrayList<Song>();
	}

	public void addSong(Song song){
		songs.add(song);
	}
}
