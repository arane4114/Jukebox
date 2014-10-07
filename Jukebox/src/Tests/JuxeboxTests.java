package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Song;
import Model.Student;

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
}
