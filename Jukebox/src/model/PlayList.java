/*
 * @ - Author: Abhishek Rane
 * @ - Author: Bryce Hammond
 */

package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import songPlayer.EndOfSongEvent;
import songPlayer.EndOfSongListener;
import songPlayer.SongPlayer;

/*
 * This class models a playist. It stores songs and plays them in FIFO order. 
 * It can be shown as a list in a  and it implements CurrentSongModel 
 * so it can tell the UI when a new song is being played.
 */
public class PlayList implements ListModel<Song>, CurrentSongModel,
		Serializable {
	private List<Song> songList;
	private transient List<ListDataListener> listDataListeners;
	private transient List<CurrentSongListener> currentSongListeners;

	private boolean isPlaying;
	private final int TWO_SECONDS = 2000;
	private Song currentSong;

	/*
	 * If a new playlist is to be created, it is initialized from here.
	 */
	public PlayList() {
		songList = new LinkedList<Song>();
		listDataListeners = new LinkedList<ListDataListener>();
		currentSongListeners = new LinkedList<CurrentSongListener>();

		isPlaying = false;
		currentSong = null;
	}

	/*
	 * Adds a new song to the list and notifies all observers that the list has
	 * changed.
	 */
	public void addSong(Song song) {
		songList.add(song);
		changedSongList();
		if (!isPlaying) {
			playNextSong();
		}
	}

	/*
	 * Tells all current song listeners that no song is playing. If there are
	 * more songs in the queue, it will wait for 2 seconds and then play it.
	 */
	private void songHasEnded() {
		for (CurrentSongListener l : currentSongListeners)
			l.noSongIsPlaying();
		if (songList.isEmpty()) {
			this.isPlaying = false;
		} else {
			this.isPlaying = false;
			try {
				Thread.sleep(TWO_SECONDS);
				playNextSong();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Play sthe next song in the list and notifies all observers about the change.
	 */
	private void playNextSong() {
		if (!songList.isEmpty() && !isPlaying) {
			isPlaying = true;
			currentSong = songList.get(0);
			songList.remove(0);
			changedSongList();
			for (CurrentSongListener l : currentSongListeners)
				l.newCurrentSongIs(currentSong);
			SongPlayer.playFile(new EndOfSongListenerObject(),
					currentSong.getFileLocation());
		}
	}
	
	/*
	 * Tells all list data listeners that something changed. 
	 */
	private void changedSongList() {
		for (ListDataListener l : listDataListeners)
			l.contentsChanged(new ListDataEvent(this,
					ListDataEvent.CONTENTS_CHANGED, 0, songList.size()));
	}
	/*
	 * Creates a end of song listener that will notify this class when the currently playing song ends.
	 */
	private class EndOfSongListenerObject implements EndOfSongListener {
		public void songFinishedPlaying(EndOfSongEvent eosEvent) {
			songHasEnded();
		}
	}
	
	/*
	 * Returns the current number of songs in queue.
	 */
	@Override
	public int getSize() {
		return songList.size();
	}

	/*
	 * Gets the song at a particular index.
	 */
	@Override
	public Song getElementAt(int index) {
		return songList.get(index);
	}
	
	/*
	 * Adds a list data listener to the list of list data listeners. 
	 */
	@Override
	public void addListDataListener(ListDataListener l) {
		listDataListeners.add(l);
	}
	
	/*
	 * Removes a list data listener from the list of list data listeners. 
	 */
	@Override
	public void removeListDataListener(ListDataListener l) {
		listDataListeners.remove(l);
	}
	
	/*
	 * Adds a current song to the list of list current song listeners. 
	 */
	@Override
	public void addCurrentSongListener(CurrentSongListener l) {
		currentSongListeners.add(l);
	}
	
	/*
	 * Removes a current song from the list of list current song listeners. 
	 */
	@Override
	public void removeCurrentSongListener(CurrentSongListener l) {
		currentSongListeners.remove(l);
	}
	
	/*
	 * As these lists cant be serialized, if the state is restored, they need to be manually reinitialized.
	 * The UI elements will add themselves as needed.
	 */
	public void wasLoadedFromFile() {
		listDataListeners = new LinkedList<ListDataListener>();
		currentSongListeners = new LinkedList<CurrentSongListener>();
	}
}
