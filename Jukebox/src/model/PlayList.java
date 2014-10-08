package model;

import java.util.LinkedList;

import java.util.List;

import songPlayer.EndOfSongEvent;
import songPlayer.EndOfSongListener;
import songPlayer.SongPlayer;

public class PlayList {
	private List<Song> list;
	private boolean isPlaying;
	private final int TWO_SECONDS = 2000;
	
	public static String baseDir = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");

	public PlayList() {
		list = new LinkedList<Song>();
		isPlaying = false;
	}

	public void addSong(Song song) {
		list.add(song);
		if(!isPlaying){
			playNextSong();
		}
	}

	private void songHasEnded(){
		if(list.isEmpty()){
			this.isPlaying = false;
		}
		else{
			this.isPlaying = false;
			try {
				Thread.sleep(TWO_SECONDS);
				playNextSong();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	private void playNextSong(){
		if(!list.isEmpty() && !isPlaying){
			isPlaying = true;
			Song currentSong = list.get(0);
			list.remove(0);
			SongPlayer.playFile(new EndOfSongListenerObject(), baseDir + currentSong.getFileLocation());
		}
	}
	
	private class EndOfSongListenerObject implements EndOfSongListener {
		public void songFinishedPlaying(EndOfSongEvent eosEvent) {
			songHasEnded();
		}
	}
}
