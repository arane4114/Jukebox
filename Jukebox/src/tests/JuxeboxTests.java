package tests;

import static org.junit.Assert.*;

import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.PlayList;
import model.Song;
import model.Songs;
import model.Student;
import model.Students;

import org.junit.Test;

public class JuxeboxTests {

	@Test
	public void testOneSong() {
		Song s = new Song("x", "x", "x", 800);
		assertTrue(s.canBePlayedAgainToday());
		s.play();
		assertTrue(s.canBePlayedAgainToday());
		s.play();
		assertTrue(s.canBePlayedAgainToday());
		s.play();
		assertTrue(s.canBePlayedAgainToday());
		s.play();
		assertTrue(s.canBePlayedAgainToday());
		s.play();
		assertFalse(s.canBePlayedAgainToday());

		assertEquals(800, s.getLength());
		assertEquals("x", s.getFileLocation());
		assertEquals("x", s.getTitle());
		assertEquals("x", s.getArtist());

		Song r = new Song("X1", "X2", "X3", 299);
		assertTrue(r.canBePlayedAgainToday());
		r.play();
		assertTrue(r.canBePlayedAgainToday());
		r.play();
		assertTrue(r.canBePlayedAgainToday());
		r.play();
		assertTrue(r.canBePlayedAgainToday());
		r.play();
		assertTrue(r.canBePlayedAgainToday());
		r.play();
		assertFalse(r.canBePlayedAgainToday());

		assertEquals(299, r.getLength());
		assertEquals("X1", r.getFileLocation());
		assertEquals("X2", r.getTitle());
		assertEquals("X3", r.getArtist());

		r.pretendTheDateHasChanged();

		assertTrue(r.canBePlayedAgainToday());
		r.play();
		assertTrue(r.canBePlayedAgainToday());
		r.play();
		assertTrue(r.canBePlayedAgainToday());
		r.play();
		assertTrue(r.canBePlayedAgainToday());
		r.play();
		assertTrue(r.canBePlayedAgainToday());
		r.play();
		assertFalse(r.canBePlayedAgainToday());

		assertEquals(299, r.getLength());
		assertEquals("X1", r.getFileLocation());
		assertEquals("X2", r.getTitle());
		assertEquals("X3", r.getArtist());

		Song z = new Song("X1", "X2", "X3", 299);

		assertTrue(z.canBePlayedAgainToday());
		z.play();
		assertTrue(z.canBePlayedAgainToday());
		z.play();

		z.pretendTheDateHasChanged();

		assertTrue(z.canBePlayedAgainToday());
		z.play();
		assertTrue(z.canBePlayedAgainToday());
		z.play();
		assertTrue(z.canBePlayedAgainToday());
		z.play();
		assertTrue(z.canBePlayedAgainToday());
		z.play();
		assertTrue(z.canBePlayedAgainToday());
		z.play();
		assertFalse(z.canBePlayedAgainToday());

	}

	@Test
	public void testOneStudent() {
		Student s = new Student("BRYCE", 123);

		assertEquals(0, s.getSecondsPlayed());
		assertEquals(90000, s.getSecondsLeft());
		assertEquals(0, s.getPlaysToday());

		assertTrue(s.canPlay());
		s.play();
		assertTrue(s.canPlay());
		s.play();
		assertFalse(s.canPlay());
		s.play();
		assertFalse(s.canPlay());
		s.play();
		assertFalse(s.canPlay());
		s.play();
		assertFalse(s.canPlay());

		s.pretendTheDateHasChanged();

		assertTrue(s.canPlay());
		s.play();
		assertTrue(s.canPlay());
		s.play();
		assertFalse(s.canPlay());
		s.play();
		assertFalse(s.canPlay());
		s.play();
		assertFalse(s.canPlay());
		s.play();
		assertFalse(s.canPlay());

		assertEquals("BRYCE", s.getName());
		assertEquals(123, s.getNumberID());
	}

	@Test
	public void testOneStudents() {
		Student a = new Student("BRYCE", 123);
		Student b = new Student("BRYCE", 321);
		Student c = new Student("ALEX", 456);
		Student d = new Student("Bob", 654);

		Students abcd = new Students();

		abcd.addStudent(a);
		abcd.addStudent(b);
		abcd.addStudent(c);
		abcd.addStudent(d);

		assertTrue(abcd.contains(123));
		assertTrue(abcd.contains(321));
		assertTrue(abcd.contains(456));
		assertTrue(abcd.contains(654));
		assertFalse(abcd.contains(1234));

		assertEquals(a, abcd.getStudent(123));
		assertEquals(b, abcd.getStudent(321));
		assertEquals(c, abcd.getStudent(456));
		assertEquals(d, abcd.getStudent(654));
		assertEquals(null, abcd.getStudent(1243));
	}

	@Test 
	public void testOneSongs() {
			
		String baseDir = System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "songfiles"
				+ System.getProperty("file.separator");
		
		Songs songs = new Songs();

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

		JTable table = new JTable(songs);
		table.setRowSorter(new TableRowSorter<TableModel>(table.getModel()));

		assertEquals(String.class, table.getColumnClass(0));
		assertEquals(String.class, table.getColumnClass(1));
		assertEquals(null, table.getColumnClass(-1));

		assertEquals("error", table.getColumnName(-1));

		assertEquals("Ralph Schuckett", table.getValueAt(0, 0));
		assertEquals("Blue Ridge Mountain Mist", table.getValueAt(0, 1));
		assertEquals(38, table.getValueAt(0, 2));

		assertEquals(songs.getElementAt(0), songs.getSongAt(0));

		assertFalse(table.isCellEditable(0, 0));

		assertEquals(7, songs.getSize());
	}

	@Test
	public void testOnePlayList() {

		PlayList playList = new PlayList();
		
		String baseDir = System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "songfiles"
				+ System.getProperty("file.separator");

		playList.addSong(new Song(baseDir + "BlueRidgeMountainMist.mp3",
				"Blue Ridge Mountain Mist", "Ralph Schuckett", 38));
		playList.addSong(new Song(baseDir + "DeterminedTumbao.mp3", "Determined Tumbao",
				"FreePlay Music", 20));
		playList.addSong(new Song(baseDir + "flute.aif", "Flute", "Sun Microsystems", 5));
		playList.addSong(new Song(baseDir + "spacemusic.au", "Spacemusic", "Unknown", 6));
		playList.addSong(new Song(baseDir + "SwingCheese.mp3", "Swing Cheese",
				"FreePlay Music", 15));
		playList.addSong(new Song(baseDir + "tada.wav", "Tada", "Microsoft", 2));
		playList.addSong(new Song(baseDir + "UntameableFire.mp3", "Untameable Fire",
				"Pierre Langer", 282));
	}
}
