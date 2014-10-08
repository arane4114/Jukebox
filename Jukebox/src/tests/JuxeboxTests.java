package tests;

import static org.junit.Assert.*;
import model.Song;
import model.Songs;
import model.Student;
import model.Students;

import org.junit.Test;

public class JuxeboxTests {

	@Test
	public void testOneSong() {
		Song s = new Song("x" , "x" ,"x" , 800);
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
		
		Song r = new Song("X1" , "X2" ,"X3" , 299);
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
		
		Song z = new Song("X1" , "X2" ,"X3" , 299);
		
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
	
//	@Test
//	public void testTwoSong() {
//		Song s = new Song("flute.aif", "Flute", "Sun Microsystems", 6)
//		assertTrue(s.canBePlayedAgainToday());
//		s.play();
//		assertTrue(s.canBePlayedAgainToday());
//		s.play();
//		assertTrue(s.canBePlayedAgainToday());
//		s.play();
//		assertTrue(s.canBePlayedAgainToday());
//		s.play();
//		assertTrue(s.canBePlayedAgainToday());
//		s.play();
//		assertFalse(s.canBePlayedAgainToday());	
//		
//		assertEquals(800, s.getLength());
//		assertEquals("x", s.getFileLocation());
//		assertEquals("x", s.getTitle());
//		assertEquals("x", s.getArtist());
//		
//		Song r = new Song("X1" , "X2" ,"X3" , 299);
//		assertTrue(r.canBePlayedAgainToday());
//		r.play();
//		assertTrue(r.canBePlayedAgainToday());
//		r.play();
//		assertTrue(r.canBePlayedAgainToday());
//		r.play();
//		assertTrue(r.canBePlayedAgainToday());
//		r.play();
//		assertTrue(r.canBePlayedAgainToday());
//		r.play();
//		assertFalse(r.canBePlayedAgainToday());	
//		
//		assertEquals(299, r.getLength());
//		assertEquals("X1", r.getFileLocation());
//		assertEquals("X2", r.getTitle());
//		assertEquals("X3", r.getArtist());
//		
//		r.pretendTheDateHasChanged();
//		
//		assertTrue(r.canBePlayedAgainToday());
//		r.play();
//		assertTrue(r.canBePlayedAgainToday());
//		r.play();
//		assertTrue(r.canBePlayedAgainToday());
//		r.play();
//		assertTrue(r.canBePlayedAgainToday());
//		r.play();
//		assertTrue(r.canBePlayedAgainToday());
//		r.play();
//		assertFalse(r.canBePlayedAgainToday());	
//		
//		assertEquals(299, r.getLength());
//		assertEquals("X1", r.getFileLocation());
//		assertEquals("X2", r.getTitle());
//		assertEquals("X3", r.getArtist());
//		
//		Song z = new Song("X1" , "X2" ,"X3" , 299);
//		
//		assertTrue(z.canBePlayedAgainToday());
//		z.play();
//		assertTrue(z.canBePlayedAgainToday());
//		z.play();
//		
//		z.pretendTheDateHasChanged();
//		
//		assertTrue(z.canBePlayedAgainToday());
//		z.play();
//		assertTrue(z.canBePlayedAgainToday());
//		z.play();
//		assertTrue(z.canBePlayedAgainToday());
//		z.play();
//		assertTrue(z.canBePlayedAgainToday());
//		z.play();
//		assertTrue(z.canBePlayedAgainToday());
//		z.play();
//		assertFalse(z.canBePlayedAgainToday());	
//
//	}
	
	@Test
	public void testOneStudent() {
		Student s = new Student("BRYCE" , 123);
		
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
		Student a = new Student("BRYCE" , 123);
		Student b = new Student("BRYCE" , 321);
		Student c = new Student("ALEX" , 456);
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
		Song a = new Song("a" , "a" ,"a" , 1);
		Song b = new Song("b" , "b" ,"b" , 2);
		Song c = new Song("c" , "c" ,"c" , 3);
		Song d = new Song("d" , "d" ,"d" , 4);
		
		Songs abcd = new Songs();
		
		abcd.addSong(a);
		abcd.addSong(b);
		abcd.addSong(c);
		abcd.addSong(d);
		
	}
}
