package view;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.PlayList;
import model.Song;
import model.Songs;

public class JukeboxGUI extends JFrame{
	private Songs songs;
	private JTable table;
	private JButton play;
	private PlayList playList;
	
	private class SelectedSong implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			int selectedRow = table.getSelectedRow();
			int selectedColumn = table.getSelectedColumn();
			
			if (selectedColumn != -1 && selectedRow != -1)
			{
				if(songs.getSongAt(selectedRow).canBePlayedAgainToday()){
					playList.addSong(songs.getSongAt(selectedRow));
					songs.getSongAt(selectedRow).play();
				}
			}	
		}
	}
	
	public JukeboxGUI()
	{
		
		playList = new PlayList();

		songs = new Songs();
		songs.addSong(new Song("BlueRidgeMountainMist.mp3", "Blue Ridge Mountain Mist", "Ralph Schuckett", 38));
		songs.addSong(new Song("DeterminedTumbao.mp3", "Determined Tumbao", "FreePlay Music", 20));
		songs.addSong(new Song("flute.aif", "Flute", "Sun Microsystems", 5));
		songs.addSong(new Song("spacemusic.au", "Spacemusic", "Unknown", 6));
		songs.addSong(new Song("SwingCheese.mp3", "Swing Cheese", "FreePlay Music", 15));
		songs.addSong(new Song("tada.wav", "Tada", "Microsoft", 2));
		songs.addSong(new Song("UntameableFire.mp3", "Untameable Fire", "Pierre Langer", 282));


				table = new JTable(songs);
				table.setRowSorter(new TableRowSorter<TableModel>(table.getModel())); // needed for sorting
				
				play = new JButton("Play");
				play.addActionListener(new SelectedSong());

				// left side
				JPanel center = new JPanel();
				center.setLayout(new BorderLayout());
				
				// add the table to the center of the left side
				center.add(new JScrollPane(table), BorderLayout.CENTER);
				
				JPanel top = new JPanel();
				top.add(new JLabel("Songs Table")); 
				
				// add centered label to top of left side
				center.add(top, BorderLayout.NORTH);
				
				
				
				JPanel bottom = new JPanel();
				bottom.add(play);
				center.add(bottom, BorderLayout.SOUTH);
			
				this.add(center);

				
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
