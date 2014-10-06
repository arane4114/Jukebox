package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Song;

public class JuxeboxTests {

	@Test
	public void testOneSong() {
		Song s = new Song("x" , "x" ,"x" , "X");
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
		
		Song r = new Song("x" , "x" ,"x" , "X");
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
	}
}
