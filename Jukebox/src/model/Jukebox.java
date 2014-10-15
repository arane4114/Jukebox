package model;

public class Jukebox {

	private Student currentStudent;
	private PlayList playList;
	private Songs songs;
	private Students students;
	private boolean loggedIn;
	
	public Jukebox(){
		
		String baseDir = System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "songfiles"
				+ System.getProperty("file.separator");

		playList = new PlayList();

		songs = new Songs();
		songs.addSong(new Song(baseDir + "BlueRidgeMountainMist.mp3",
				"Blue Ridge Mountain Mist", "Ralph Schuckett", 38));
		songs.addSong(new Song(baseDir + "DeterminedTumbao.mp3",
				"Determined Tumbao", "FreePlay Music", 20));
		songs.addSong(new Song(baseDir + "flute.aif", "Flute",
				"Sun Microsystems", 5));
		songs.addSong(new Song(baseDir + "spacemusic.au", "Spacemusic",
				"Unknown", 6));
		songs.addSong(new Song(baseDir + "SwingCheese.mp3", "Swing Cheese",
				"FreePlay Music", 15));
		songs.addSong(new Song(baseDir + "tada.wav", "Tada", "Microsoft", 2));
		songs.addSong(new Song(baseDir + "UntameableFire.mp3",
				"Untameable Fire", "Pierre Langer", 282));

		students = new Students();
		students.addStudent(new Student("Ali", 1111));
		students.addStudent(new Student("Chris", 2222));
		students.addStudent(new Student("River", 3333));
		students.addStudent(new Student("Ryan", 4444));
		
		loggedIn = false;
		
	}
	
	public Songs getSongs(){
		return songs;
	}
	
	public Student getCurrentStudent(){
		return currentStudent;	
	}
	
	public void setCurrentStudent(Student student){
		currentStudent = student;
		loggedIn = true;
	}
	
	public void logout(){
		currentStudent = null;
		loggedIn = false;
	}
	
	public boolean getLoggedIn(){
		return loggedIn;
	}
	
	public PlayList getPlayList(){
		return playList;
	}
	
	public Students getStudents(){
		return students;
	}
	
}
