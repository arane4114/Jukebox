package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.Song;
import model.Songs;
import model.Student;

public class JukeboxGUI extends JFrame{
	private Songs songs;
	private JTable table;
	private JList<Song> list;
	private JButton play;
	
	private class SelectedSong implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Song selected = list.getSelectedValue();
			if (selected != null)
			{
				selected.play();
			}	
		}
	}
	
	public JukeboxGUI()
	{

		songs = new Songs();
		songs.addSong(new Song("BlueRidgeMoutainMist.mp3", "Blue Ridge Moutain Mist", "Blue Ridge Moutain Mist", 100));
		songs.addSong(new Song("DeterminedTumbao.mp3", "Determined Tumbao", "Determined Tumbao", 100));
		songs.addSong(new Song("flute.aif", "Flute", "Flute", 100));
		songs.addSong(new Song("spacemusic.au", "Spacemusic", "Spacemusic", 100));
		songs.addSong(new Song("SwingCheese.mp3", "Swing Cheese", "Swing Cheese", 100));
		songs.addSong(new Song("tada.wav", "Tada", "Tada", 100));
		songs.addSong(new Song("UntameableFire.mp3", "Untameable Fire", "Untameable Fire", 100));

		// create the JTable from the StudentList
				table = new JTable(songs);
				table.setRowSorter(new TableRowSorter<TableModel>(table.getModel())); // needed for sorting
				
				// create the JList from the StudentList
				list = new JList<Song>(songs);
				
				// create add/ remove buttons, add listeners
				play = new JButton("Play");
				play.addActionListener(new SelectedSong());
				
				
				// left side
				JPanel center = new JPanel();
				center.setLayout(new BorderLayout());
				
				// add the table to the center of the left side
				center.add(new JScrollPane(table), BorderLayout.CENTER);
				
				// top = centered label
				JPanel top = new JPanel();
				top.add(new JLabel("Songs Table")); 
				
				// add centered label to top of left side
				center.add(top, BorderLayout.NORTH);
				
				this.add(center);
//				
//				JPanel bottom = new JPanel();
//				
//				play.add(bottom, BorderLayout.SOUTH);
//				this.add(play);
				
				// frame setup
				this.setSize(600, 300);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setVisible(true);
	}
	
	
	public static void main(String[] args)
	{
		new JukeboxGUI();
	}
}
