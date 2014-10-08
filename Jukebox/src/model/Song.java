package model;

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

	/*
	 * Song constructor.
	 * Takes File location , title , artist , songLength
	 */
	public Song(String fileLocation, String title, String artist, int songLength) {
		this.fileLocation = fileLocation;
		this.title = title;
		this.artist = artist;
		this.songLength = songLength;
		this.playsToday = 0;
		this.songPlays = new ArrayList<GregorianCalendar>();
	}

	/*
	 * Getter for file location.
	 */
	public String getFileLocation() {
		return fileLocation;
	}

	/*
	 * Getter for title.
	 */
	public String getTitle() {
		return title;
	}

	/*
	 * Getter for artist.
	 */
	public String getArtist() {
		return artist;
	}

	/*
	 * Getter for length.
	 */
	public int getLength() {
		return songLength;
	}

	/*
	 * Returns true if the song can be played again today.
	 * Returns false if the song can not be played again today.
	 */
	public boolean canBePlayedAgainToday() {
		dateCheck();
		return playsToday < MAX_PLAYS;
	}

	/*
	 * Add 1 to the playsToday.
	 */
	public void incresePlayCount() {
		playsToday++;
	}

	/*
	 * Getter of playsToday.
	 */
	public int getPlaysToday() {
		return playsToday;
	}

	/*
	 * Checks if the date has changed since the last time the 
	 * song was played. If it is a new data set playsToday = 0;
	 * If it is not a new day do nothing.
	 */
	private void dateCheck() {
		if (playsToday > 0) {
			GregorianCalendar today = new GregorianCalendar();
			GregorianCalendar last1 = songPlays.get(songPlays.size() - 1);
			if (!sameDay(today, last1)) {
				this.playsToday = 0;
			}
		}
	}

	/*
	 * Returns a true or false if the two days passed in are the same or not.
	 */
	private boolean sameDay(GregorianCalendar today, GregorianCalendar other) {
		return today.get(Calendar.YEAR) == other.get(Calendar.YEAR)
				&& today.get(Calendar.MONTH) == other.get(Calendar.MONTH)
				&& today.get(Calendar.DAY_OF_MONTH) == other
						.get(Calendar.DAY_OF_MONTH);
	}

	/*
	 * Tell the song that it has been played.
	 * Play still check if the song can be played.
	 * Adds play dates to list.
	 * Increases playToday.
	 */
	public void play() {
		dateCheck();
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
			if (sameDay(last1, today) && sameDay(last2, today)
					&& sameDay(last3, today) && sameDay(last4, today)
					&& sameDay(last5, today)) {
				return;
			} else {
				songPlays.add(today);
				incresePlayCount();
			}
		}
	}

	/*
	 * Simulates if the date has changed.
	 */
	public void pretendTheDateHasChanged() {
		for (int i = 1; i < songPlays.size(); i++) {
			songPlays.get(songPlays.size() - i).add(Calendar.DATE, -1);
		}
	}
}
