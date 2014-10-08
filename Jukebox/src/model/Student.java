package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Student {

	private long numberID;
	private String name;
	private int secondsPlayed;
	private int playsToday;

	private final int MAX_PLAY_TIME = 90000;
	private final int MAX_PLAYS = 2;

	private List<GregorianCalendar> dayPlays;

	/*
	 * Constructor for student.
	 * Student takes a name and ID
	 */
	public Student(String name, long numberID) {
		this.name = name;
		this.numberID = numberID;
		this.secondsPlayed = 0;
		this.playsToday = 0;
		this.dayPlays = new ArrayList<GregorianCalendar>();
	}

	/*
	 * Getter for Student name.
	 */
	public String getName() {
		return name;
	}

	/*
	 * Getter for Student ID number.
	 */
	public long getNumberID() {
		return numberID;
	}

	/*
	 * Getter for the amount of seconds a student has played.
	 */
	public int getSecondsPlayed() {
		return secondsPlayed;
	}

	/*
	 * Getter for amount of seconds a student has left.
	 */
	public int getSecondsLeft() {
		return MAX_PLAY_TIME - secondsPlayed;
	}

	/*
	 * Getter for plays today.
	 */
	public int getPlaysToday() {
		return playsToday;
	}

	/*
	 * Increases play count by 1.
	 */
	public void incresePlayCount() {
		playsToday++;
	}

	/*
	 * Returns a boolean.
	 * If the another song can be played or not by that student.
	 */
	public boolean canPlay() {
		dateCheck();
		return playsToday < MAX_PLAYS;
	}

	/*
	 * Checks if the date has changed since the last time the 
	 * song was played. If it is a new data set playsToday = 0;
	 * If it is not a new day do nothing.
	 */
	private void dateCheck() {
		if (playsToday > 0) {
			GregorianCalendar today = new GregorianCalendar();
			GregorianCalendar last1 = dayPlays.get(dayPlays.size() - 1);
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
		if (dayPlays.size() < MAX_PLAYS) {
			dayPlays.add(today);
			incresePlayCount();
		} else {
			GregorianCalendar last1 = dayPlays.get(dayPlays.size() - 1);
			GregorianCalendar last2 = dayPlays.get(dayPlays.size() - 2);
			if (sameDay(last1, today) && sameDay(last2, today)) {
				return;
			} else {
				dayPlays.add(today);
				incresePlayCount();
			}
		}
	}
	
	/*
	 * Simulates if the date has changed.
	 */
	public void pretendTheDateHasChanged() {
		for (int i = 1; i < dayPlays.size(); i++) {
			dayPlays.get(dayPlays.size() - i).add(Calendar.DATE, -1);
		}
	}
}
