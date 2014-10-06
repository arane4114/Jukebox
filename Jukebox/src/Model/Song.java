package Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Song {
	private String fileLocation;
	private String title;
	private String artist;
	private int songLength;
	private int playsToday;

	private final int MAX_PLAYS = 5;

	private List<GregorianCalendar> songPlays;

	public Song(String fileLocation, String title, String artist, int songLength) {
		this.fileLocation = fileLocation;
		this.title = title;
		this.artist = artist;
		this.songLength = songLength;
		this.playsToday = 0;
		this.songPlays = new ArrayList<GregorianCalendar>();
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public int getLength() {
		return songLength;
	}

	public boolean canBePlayedAgainToday() {
		return playsToday < MAX_PLAYS;
	}

	public void incresePlayCount() {
		playsToday++;
	}

	private boolean sameDay(GregorianCalendar today, GregorianCalendar other) {
		return today.get(Calendar.YEAR) == other.get(Calendar.YEAR)
				&& today.get(Calendar.MONTH) == other.get(Calendar.MONTH)
				&& today.get(Calendar.DAY_OF_MONTH) == other
						.get(Calendar.DAY_OF_MONTH);
	}

	public void play() {
		if (playsToday >= MAX_PLAYS) {
			return;
		}
		GregorianCalendar today = new GregorianCalendar();
		if (songPlays.size() < MAX_PLAYS) {
			songPlays.add(today);
			incresePlayCount();
		} else {
			GregorianCalendar last1 = songPlays.get(songPlays.size() - 1);
			GregorianCalendar last2 = songPlays.get(songPlays.size() - 2);
			GregorianCalendar last3 = songPlays.get(songPlays.size() - 3);
			GregorianCalendar last4 = songPlays.get(songPlays.size() - 4);
			GregorianCalendar last5 = songPlays.get(songPlays.size() - 5);
			if (sameDay(last1, today) && sameDay(last2, today) && sameDay(last3, today) && 
					sameDay(last4, today) && sameDay(last5, today)) {
				return;
			} else {
				songPlays.add(today);
				incresePlayCount();
			}
		}
	}

	public void pretendTheDateHasChanged() {
		if (songPlays.size() < 5)
			return;
		songPlays.get(songPlays.size() - 1).add(Calendar.DATE, -1);
		songPlays.get(songPlays.size() - 2).add(Calendar.DATE, -1);
		songPlays.get(songPlays.size() - 3).add(Calendar.DATE, -1);
		songPlays.get(songPlays.size() - 4).add(Calendar.DATE, -1);
		songPlays.get(songPlays.size() - 5).add(Calendar.DATE, -1);
		playsToday = 0;
	}
}
