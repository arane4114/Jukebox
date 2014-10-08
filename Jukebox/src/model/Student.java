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

	public Student(String name, long numberID) {
		this.name = name;
		this.numberID = numberID;
		this.secondsPlayed = 0;
		this.playsToday = 0;
		this.dayPlays = new ArrayList<GregorianCalendar>();
	}

	public String getName() {
		return name;
	}

	public long getNumberID() {
		return numberID;
	}

	public int getSecondsPlayed() {
		return secondsPlayed;
	}

	public int getSecondsLeft() {
		return MAX_PLAY_TIME - secondsPlayed;
	}

	public int getPlaysToday() {
		return playsToday;
	}

	public void incresePlayCount() {
		playsToday++;
	}

	public boolean canPlay() {
		dateCheck();
		return playsToday < MAX_PLAYS;
	}

	private void dateCheck() {
		if (playsToday > 0) {
			GregorianCalendar today = new GregorianCalendar();
			GregorianCalendar last1 = dayPlays.get(dayPlays.size() - 1);
			if (!sameDay(today, last1)) {
				this.playsToday = 0;
			}
		}
	}

	private boolean sameDay(GregorianCalendar today, GregorianCalendar other) {
		return today.get(Calendar.YEAR) == other.get(Calendar.YEAR)
				&& today.get(Calendar.MONTH) == other.get(Calendar.MONTH)
				&& today.get(Calendar.DAY_OF_MONTH) == other
						.get(Calendar.DAY_OF_MONTH);
	}

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

	public void pretendTheDateHasChanged() {
		for (int i = 1; i < dayPlays.size(); i++) {
			dayPlays.get(dayPlays.size() - i).add(Calendar.DATE, -1);
		}
	}
}
