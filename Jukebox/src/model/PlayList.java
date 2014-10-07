package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Timer;

import songPlayer.EndOfSongEvent;
import songPlayer.EndOfSongListener;
import songPlayer.SongPlayer;

public class PlayList {
	private List<Song> list;
	private boolean isPlaying;
	private Timer timer;

	public PlayList() {
		list = new LinkedList<Song>();
		isPlaying = false;
	}

	public void addSong(Song song) {
		list.add(song);
		System.out.println("Added " + song.getTitle());
		if(list.size() == 1 && !isPlaying){
			playNextSong();
		}
	}

	private void songHasEnded(){
		System.out.println("Song has ended called");
		if(list.isEmpty()){
			this.isPlaying = false;
		}
		else{
			timer = new Timer(2000, new TimerListener());
			timer.setRepeats(false);
			timer.start();
			System.out.println("Waiting for 2 seconds.");
		}
	}
	
	private void playNextSong(){
		isPlaying = true;
		Song currentSong = list.get(0);
		list.remove(0);
		System.out.println("Playing " + currentSong.getTitle());
		SongPlayer.playFile(new EndOfSongListenerObject(), currentSong.getFileLocation());
	}
	
	private class TimerListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			playNextSong();
		}
	}
	
	private class EndOfSongListenerObject implements EndOfSongListener {
		public void songFinishedPlaying(EndOfSongEvent eosEvent) {
			System.out.println("End of song event reached");
			songHasEnded();
		}
	}

}
