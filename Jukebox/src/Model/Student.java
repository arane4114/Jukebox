package Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Student {

	private int numberID;
	private String name;
	private int secondsPlayed;
	private int playsToday;
	
	private final int MAX_PLAY_TIME = 90000;
	private final int MAX_PLAYS = 2;
	
	private List<GregorianCalendar> dayPlays;
	
	public Student(String name, int numberID) {
		this.name = name;
		this.numberID = numberID;
		this.secondsPlayed = 0;
		this.playsToday = 0;
		this.dayPlays = new ArrayList<GregorianCalendar>();
	}
	
	public String getName(){
		return name;
	}
	
	public int getNumberID(){
		return numberID;
	}
	
	public int getSecondsPlayed(){
		return secondsPlayed;
	}
	
	public int getSecondsLeft(){
		return MAX_PLAY_TIME - secondsPlayed;
	}
	
	public int getPlaysToday(){
		return playsToday;
	}
	
	public void incresePlayCount() {
		playsToday++;
	}
	
	public boolean canPlay(){
		return playsToday < MAX_PLAYS;
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
		for(int i = 1; i < dayPlays.size(); i ++){
			dayPlays.get(dayPlays.size() - i).add(Calendar.DATE, -1);
		}
		playsToday = 0;
	}
}
