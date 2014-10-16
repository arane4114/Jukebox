/*
 * @ - Author: Abhishek Rane
 * @ - Author: Bryce Hammond
 */
package model;

/*
 * This allows a GUI element to safely add itself to a model object that will tell it when a new song is playing. 
 */
public interface CurrentSongModel {
	public void addCurrentSongListener(CurrentSongListener l);

	public void removeCurrentSongListener(CurrentSongListener l);
}
