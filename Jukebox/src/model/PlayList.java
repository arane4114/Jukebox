/*
 * @ - Author: Abhishek Rane
 * @ - Author: Bryce Hammond
 */

package model;

import java.util.LinkedList;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import songPlayer.EndOfSongEvent;
import songPlayer.EndOfSongListener;
import songPlayer.SongPlayer;

public class PlayList implements ListModel<Song>, CurrentSongModel {
	private List<Song> songList;
	private List<ListDataListener> listDataListeners;
	private List<CurrentSongListener> currentSongListeners;

	private boolean isPlaying;
	private final int TWO_SECONDS = 2000;
	private Song currentSong;

	public PlayList() {
		songList = new LinkedList<Song>();
		listDataListeners = new LinkedList<ListDataListener>();
		currentSongListeners = new LinkedList<CurrentSongListener>();

		isPlaying = false;
		currentSong = null;
	}

	public void addSong(Song song) {
		songList.add(song);
		changedSongList();
		if (!isPlaying) {
			playNextSong();
		}
	}

	private void songHasEnded() {
		for(CurrentSongListener l : currentSongListeners)
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

	private void playNextSong() {
		if (!songList.isEmpty() && !isPlaying) {
			isPlaying = true;
			currentSong = songList.get(0);
			songList.remove(0);
			changedSongList();
			for(CurrentSongListener l : currentSongListeners)
				l.newCurrentSongIs(currentSong);
			SongPlayer.playFile(new EndOfSongListenerObject(),
					currentSong.getFileLocation());
		}
	}

	private void changedSongList() {
		for (ListDataListener l : listDataListeners)
			l.contentsChanged(new ListDataEvent(this,
					ListDataEvent.CONTENTS_CHANGED, 0, songList.size()));
	}

	private class EndOfSongListenerObject implements EndOfSongListener {
		public void songFinishedPlaying(EndOfSongEvent eosEvent) {
			songHasEnded();
		}
	}

	@Override
	public int getSize() {
		return songList.size();
	}

	@Override
	public Song getElementAt(int index) {
		return songList.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		listDataListeners.add(l);
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		listDataListeners.remove(l);
	}

	@Override
	public void addCurrentSongListener(CurrentSongListener l) {
		currentSongListeners.add(l);
	}

	@Override
	public void removeCurrentSongListener(CurrentSongListener l) {
		currentSongListeners.remove(l);
	}
}
