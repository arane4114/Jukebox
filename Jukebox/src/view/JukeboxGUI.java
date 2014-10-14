/*
 * @ - Author: Abhishek Rane
 * @ - Author: Bryce Hammond
 */

package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.CurrentSongListener;
import model.PlayList;
import model.Song;
import model.Songs;
import model.Student;
import model.Students;

/*
 * This class provides the GUI for our app.
 * It creates all the visual parts, and listens for song selections. 
 * It passes song selection information to the model.
 */
public class JukeboxGUI extends JFrame {
	private JTable table;
	private JButton play;
	private JButton login;
	private JLabel currentSongLabel;
	private JTextArea currentUserInfoArea;
	private JTextField userNameInputField;
	private JPasswordField userPasswordField;

	private Student currentStudent;
	private PlayList playList;
	private Songs songs;
	private Students students;

	/*
	 * Constructs all GUI elements and creases instances of the model and links
	 * the two together as needed.
	 */
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

		students = new Students();
		students.addStudent(new Student("Ali", 1111));
		students.addStudent(new Student("Chris", 2222));
		students.addStudent(new Student("River", 3333));
		students.addStudent(new Student("Ryan", 4444));

		table = new JTable(songs);
		table.setRowSorter(new TableRowSorter<TableModel>(table.getModel()));

		play = new JButton("Play");
		play.addActionListener(new SelectedSong());

		this.setLayout(new GridLayout(1, 3));
		this.setTitle("Jukebox");

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());

		leftPanel.add(new JScrollPane(table), BorderLayout.CENTER);

		JPanel top = new JPanel();
		top.add(new JLabel("Songs Table"));

		leftPanel.add(top, BorderLayout.NORTH);

		JPanel bottom = new JPanel();
		bottom.add(play);
		leftPanel.add(bottom, BorderLayout.SOUTH);

		this.add(leftPanel);

		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));

		middlePanel.add(new JLabel("Now Playing:"));
		currentSongLabel = new JLabel("No Song Playing");
		middlePanel.add(currentSongLabel);
		playList.addCurrentSongListener(new currentSongListener());

		middlePanel.add(new JLabel("Up Next:"));
		JList<Song> playListJList = new JList<Song>(playList);
		middlePanel.add(new JScrollPane(playListJList));

		this.add(middlePanel);

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		rightPanel.add(new JLabel("Current User Info"));
		currentUserInfoArea = new JTextArea();
		currentUserInfoArea.setEditable(false);
		currentUserInfoArea.setSize(200, 200);
		rightPanel.add(currentUserInfoArea);

		userNameInputField = new JTextField("User name");
		rightPanel.add(userNameInputField);

		userPasswordField = new JPasswordField("ID");
		rightPanel.add(userPasswordField);

		login = new JButton();
		login.addActionListener(new loginButtonListener());
		login.setText("Login");
		rightPanel.add(login, BorderLayout.CENTER);

		this.add(rightPanel);

		this.setSize(900, 550);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void updateCurrentStudentData() {
		this.currentUserInfoArea.setText("User Name: "
				+ currentStudent.getName() + "\n" + "Time left: "
				+ currentStudent.getSecondsLeftInHMS() + "\n"
				+ "Number of Plays Today: " + currentStudent.getPlaysToday());
	}

	private class loginButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (students.login(userNameInputField.getText(),
					userPasswordField.getPassword())) {
				currentStudent = students.getStudentByName(userNameInputField
						.getText());
				updateCurrentStudentData();
			} else {
				JOptionPane.showMessageDialog(new JFrame(),
						"The user name and password combination is invalid.");
			}
		}

	}

	private class currentSongListener implements CurrentSongListener {

		@Override
		public void noSongIsPlaying() {
			currentSongLabel.setText("No Song Playing");
		}

		@Override
		public void newCurrentSongIs(Song song) {
			currentSongLabel.setText(song.toString());

		}

	}

	/*
	 * Listens for the play button to be pressed. It then gets the song
	 * selected, validates if it can be played and if so sends it to the
	 * playlist.
	 */
	private class SelectedSong implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			int selectedRow = table.getSelectedRow();
			int selectedColumn = table.getSelectedColumn();

			if (selectedColumn != -1 && selectedRow != -1) {
				if (songs.getSongAt(selectedRow).canBePlayedAgainToday()
						&& currentStudent.canPlay(songs.getSongAt(selectedRow))) {
					playList.addSong(songs.getSongAt(selectedRow));
					songs.getSongAt(selectedRow).play();
					currentStudent.play(songs.getSongAt(selectedRow));
					updateCurrentStudentData();
				}
			}
		}
	}

	/*
	 * Entry point for the console version of the app.
	 */
	public static void main(String[] args) {
		new JukeboxGUI();
	}
}
