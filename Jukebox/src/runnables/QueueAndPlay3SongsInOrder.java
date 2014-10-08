package runnables;

import model.PlayList;
import model.Song;

public class QueueAndPlay3SongsInOrder {

//	public static String baseDir = System.getProperty("user.dir")
//			+ System.getProperty("file.separator") + "songfiles"
//			+ System.getProperty("file.separator");

	public void play(){
		PlayList playlist = new PlayList();
		
//		playlist.addSong(new Song(baseDir + "flute.aif", "Flute", "Sun Microsystems", 6));
//		playlist.addSong(new Song(baseDir + "spacemusic.au", "spacemusic", "Sun Microsystems" , 6));
//		playlist.addSong(new Song(baseDir + "tada.wav", "tada", "Sun Microsystems", 2));
		
		playlist.addSong(new Song("flute.aif", "Flute", "Sun Microsystems", 6));
		playlist.addSong(new Song("spacemusic.au", "spacemusic", "Sun Microsystems" , 6));
		playlist.addSong(new Song("tada.wav", "tada", "Sun Microsystems", 2));
	}
	
	public static void main(String[] args) {
		QueueAndPlay3SongsInOrder runable = new QueueAndPlay3SongsInOrder();
		runable.play();
	}
}
