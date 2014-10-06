package Model;

public class Song {
	private String fileLocation;
	private String title;
	private String artist;
	private String genre;
	
	public Song (String fileLocation, String title, String artist, String genre){
		this.fileLocation = fileLocation;
		this.title = title;
		this.artist = artist;
		this.genre = genre;
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
		return true;
	}
	
	public void incresePlayCount(){
		
	}
}
