package runnables;

import model.PlayList;
import model.Song;
import model.Songs;

public class QueueThroughPlay3Songs {

//	public static String baseDir = System.getProperty("user.dir")
//			+ System.getProperty("file.separator") + "songfiles"
//			+ System.getProperty("file.separator");

	public void play(){
		Songs songs = new Songs();
		
//		playlist.addSong(new Song(baseDir + "flute.aif", "Flute", "Sun Microsystems", 6));
//		playlist.addSong(new Song(baseDir + "spacemusic.au", "spacemusic", "Sun Microsystems" , 6));
//		playlist.addSong(new Song(baseDir + "tada.wav", "tada", "Sun Microsystems", 2));
		
		songs.addSong(new Song("flute.aif", "Flute", "Sun Microsystems", 6));
		songs.addSong(new Song("spacemusic.au", "spacemusic", "Sun Microsystems" , 6));
		songs.addSong(new Song("tada.wav", "tada", "Sun Microsystems", 2));
	}
	
	public static void main(String[] args) {
		QueueAndPlay3SongsInOrder runable = new QueueAndPlay3SongsInOrder();
		runable.play();
	}
}