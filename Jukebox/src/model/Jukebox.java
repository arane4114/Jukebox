/*
 * @ - Author: Abhishek Rane
 * @ - Author: Bryce Hammond
 */
package model;

import java.io.Serializable;

/*
 * This class is the brains of the Jukebox app. As it is Serializable it can be written and read from a disk with no problems.
 */
public class Jukebox implements Serializable {

	private Student currentStudent;
	private PlayList playList;
	private Songs songs;
	private Students students;
	private boolean loggedIn;

	/*
	 * If a new jukebox is to be created from scratch, this method will init the
	 * Jukebox, load all the songs and users and set it to a state where there
	 * is no current user logged in.
	 */
	public Jukebox() {

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
		students.addStudent(new Student("Ali", "1111"));
		students.addStudent(new Student("Chris", "2222"));
		students.addStudent(new Student("River", "3333"));
		students.addStudent(new Student("Ryan", "4444"));

		loggedIn = false;

	}

	/*
	 * Gets the list of valid songs. The GUI will call this as it needs to
	 * display a table of all songs.
	 */
	public Songs getSongs() {
		return songs;
	}

	/*
	 * It gets the currently logged in student.
	 */
	public Student getCurrentStudent() {
		return currentStudent;
	}

	/*
	 * Attempts to login a student that is passed to it. If the name and
	 * password combination is valid it returns true and logs the student in.
	 * Else it returns false.
	 */
	public boolean logInStudent(String studentName, char[] password) {
		if (students.login(studentName, password)) {
			currentStudent = students.getStudentByName(studentName);
			loggedIn = true;
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Plays a song that is located at a particular row in the songs list.
	 * Precondition: The song can be played.
	 */
	public void playSong(int songRow) {
		playList.addSong(getSongs().getSongAt(songRow));
		songs.getSongAt(songRow).play();
		currentStudent.play(getSongs().getSongAt(songRow));
	}

	/*
	 * Logs the currently logged in student out.
	 */
	public void logout() {
		currentStudent = null;
		loggedIn = false;
	}

	/*
	 * Checks if a student is currently logged in.
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}

	/*
	 * Returns the playlist object.
	 */
	public PlayList getPlayList() {
		return playList;
	}

	/*
	 * If the Jukebox was loaded from a file all transient variables must be
	 * Reinitialized. This method tells all objects that have transient variables
	 * to ready those variables.
	 */
	public void wasLoadedFromFile() {
		playList.wasLoadedFromFile();
		songs.wasLoadedFromFile();
	}

}
