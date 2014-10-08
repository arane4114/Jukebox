package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.PlayList;
import model.Song;
import model.Songs;

public class JukeboxGUI extends JFrame {
	private Songs songs;
	private JTable table;
	private JButton play;
	private PlayList playList;

	private class SelectedSong implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			int selectedRow = table.getSelectedRow();
			int selectedColumn = table.getSelectedColumn();

			if (selectedColumn != -1 && selectedRow != -1) {
				if (songs.getSongAt(selectedRow).canBePlayedAgainToday()) {
					playList.addSong(songs.getSongAt(selectedRow));
					songs.getSongAt(selectedRow).play();
				}
			}
		}
	}

	public JukeboxGUI() {

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

		table = new JTable(songs);
		table.setRowSorter(new TableRowSorter<TableModel>(table.getModel())); 

		play = new JButton("Play");
		play.addActionListener(new SelectedSong());

		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());

		center.add(new JScrollPane(table), BorderLayout.CENTER);

		JPanel top = new JPanel();
		top.add(new JLabel("Songs Table"));

		center.add(top, BorderLayout.NORTH);

		JPanel bottom = new JPanel();
		bottom.add(play);
		center.add(bottom, BorderLayout.SOUTH);

		this.add(center);

		this.setSize(600, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		JOptionPane
				.showMessageDialog(
						new JFrame(),
						"This GUI shows the behavior of individual songs.\n"
								+ "( a song can be played 5 times a day and mutiple songs can be queued )\n"
								+ "Through JUnit testing we have shown a individual student can only play two songs a day.\n"
								+ "You are not logged in to a student currently.");
	}

	public static void main(String[] args) {
		new JukeboxGUI();
	}
}
