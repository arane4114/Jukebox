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
import model.Jukebox;
import model.Song;

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
	
	private Jukebox box;

	/*
	 * Constructs all GUI elements and creases instances of the model and links
	 * the two together as needed.
	 */
	public JukeboxGUI() {
		
		box = new Jukebox();

		table = new JTable(box.getSongs());
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
		box.getPlayList().addCurrentSongListener(new currentSongListener());

		middlePanel.add(new JLabel("Up Next:"));
		JList<Song> playListJList = new JList<Song>(box.getPlayList());
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
				+ box.getCurrentStudent().getName() + "\n" + "Time left: "
				+ box.getCurrentStudent().getSecondsLeftInHMS() + "\n"
				+ "Number of Plays Today: " + box.getCurrentStudent().getPlaysToday());
	}

	private class loginButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (box.getStudents().login(userNameInputField.getText(),
					userPasswordField.getPassword())) {
				box.setCurrentStudent(box.getStudents().getStudentByName(userNameInputField
						.getText()));
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
				if (box.getSongs().getSongAt(selectedRow).canBePlayedAgainToday()
						&& box.getCurrentStudent().canPlay(box.getSongs().getSongAt(selectedRow))) {
					box.getPlayList().addSong(box.getSongs().getSongAt(selectedRow));
					box.getSongs().getSongAt(selectedRow).play();
					box.getCurrentStudent().play(box.getSongs().getSongAt(selectedRow));
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
