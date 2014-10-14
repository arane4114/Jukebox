package model;

public interface CurrentSongListener {
	public void noSongIsPlaying();
	public void newCurrentSongIs(Song song);
}
