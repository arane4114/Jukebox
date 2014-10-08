package model;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import songPlayer.EndOfSongEvent;
import songPlayer.EndOfSongListener;
import songPlayer.SongPlayer;

public class PlayList {
	private List<Song> list;
	private boolean isPlaying;
	private final int TWO_SECONDS = 2;

	public PlayList() {
		list = new LinkedList<Song>();
		isPlaying = false;
	}

	public void addSong(Song song) {
		list.add(song);
		System.out.println("Added " + song.getTitle());
		if(!isPlaying){
			playNextSong();
		}
	}

	private void songHasEnded(){
		System.out.println("Song has ended called");
		if(list.isEmpty()){
			this.isPlaying = false;
		}
		else{
			this.isPlaying = false;
			System.out.println("Waiting for 2 seconds.");
			try {
				TimeUnit.SECONDS.sleep(TWO_SECONDS);
				playNextSong();
			}catch(InterruptedException e){
				// Should Never Happen
			}
		}
	}

	private void playNextSong(){
		if(!list.isEmpty() && !isPlaying){
			isPlaying = true;
			Song currentSong = list.get(0);
			list.remove(0);
			System.out.println("Playing " + currentSong.getTitle());
			SongPlayer.playFile(new EndOfSongListenerObject(), currentSong.getFileLocation());
		}
	}
	
	private class EndOfSongListenerObject implements EndOfSongListener {
		public void songFinishedPlaying(EndOfSongEvent eosEvent) {
			System.out.println("End of song event reached");
			songHasEnded();
		}
	}
}
