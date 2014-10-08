/*
 * @ - Author: Abhishek Rane
 * @ - Author: Bryce Hammond
 */
package console;

/*
 * This class is the start point of the console edition of this app.
 * It loads ands plays 3 songs using the play list model.
 */

import model.PlayList;
import model.Song;

public class QueueAndPlay3SongsInOrder {

	public static String baseDir = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");
	
	/*
	 * This is the main loop for the console version of the class.
	 * It loads and plays three songs.
	 */
	public void play(){
		PlayList playlist = new PlayList();
		
		playlist.addSong(new Song(baseDir + "flute.aif", "Flute", "Sun Microsystems", 6));
		playlist.addSong(new Song(baseDir + "spacemusic.au", "spacemusic", "Sun Microsystems" , 6));
		playlist.addSong(new Song(baseDir + "tada.wav", "tada", "Sun Microsystems", 2));
		
	}
	
	/*
	 * Entry point for console version of the app. Creates an object of itself and runs it.
	 */
	public static void main(String[] args) {
		QueueAndPlay3SongsInOrder runable = new QueueAndPlay3SongsInOrder();
		runable.play();
	}
}
