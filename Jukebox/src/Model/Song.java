package Model;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Song {
	private String fileLocation;
	private String title;
	private String artist;
	private String genre;
	private int playsToday;
	
	private final static int MAX_PLAYS = 5;
	
	private List<GregorianCalendar> songPlays;
	
	public Song (String fileLocation, String title, String artist, String genre){
		this.fileLocation = fileLocation;
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		playsToday = 0;
		songPlays = new ArrayList<GregorianCalendar>();
	}
	
	public String getFileLocation(){
		return fileLocation;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getArtist(){
		return artist;
	}
	
	public String getGenre(){
		return genre;
	}
	
	public boolean canBePlayedAgainToday(){
		return playsToday > MAX_PLAYS;
	}
	
	public void incresePlayCount(){
		playsToday ++;
	}
	
	private boolean sameDay(GregorianCalendar today, GregorianCalendar other) {
	    return today.get(Calendar.YEAR) == other.get(Calendar.YEAR)
	        && today.get(Calendar.MONTH) == other.get(Calendar.MONTH)
	        && today.get(Calendar.DAY_OF_MONTH) == other.get(Calendar.DAY_OF_MONTH);
	}

	public void play() {
	    if(playsToday <= MAX_PLAYS)
	      return;
	    GregorianCalendar today = new GregorianCalendar();
	    if (songPlays.size() < MAX_PLAYS) {
	    	songPlays.add(today);
	    	playsToday++;
	    } else {
	      GregorianCalendar last1 = songPlays.get(songPlays.size() - 1);
	      GregorianCalendar last2 = songPlays.get(songPlays.size() - 2);
	      if (sameDay(last1, today) && sameDay(last2, today))
	        return;
	      else {
	    	  songPlays.add(today);
	    	  playsToday++;
	      }
	    }
	}
}
