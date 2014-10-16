/*
 * @ - Author: Abhishek Rane
 * @ - Author: Bryce Hammond
 */
package model;

/*
 * This interface allows the playlist to tell a GUI element what the current song it and when it updates.
 */
public interface CurrentSongListener {
	public void noSongIsPlaying();

	public void newCurrentSongIs(Song song);
}
