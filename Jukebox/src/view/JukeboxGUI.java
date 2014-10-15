/*
 * @ - Author: Abhishek Rane
 * @ - Author: Bryce Hammond
 */

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
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
	private JButton logout;
	private JLabel currentSongLabel;
	private JTextArea currentUserInfoArea;
	private JTextField userNameInputField;
	private JPasswordField userPasswordField;
	
	private Jukebox jukebox;

	/*
	 * Constructs all GUI elements and creases instances of the model and links
	 * the two together as needed.
	 */
	public JukeboxGUI() {
		
		jukebox = new Jukebox();

		table = new JTable(jukebox.getSongs());
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
		jukebox.getPlayList().addCurrentSongListener(new currentSongListener());

		middlePanel.add(new JLabel("Up Next:"));
		JList<Song> playListJList = new JList<Song>(jukebox.getPlayList());
		middlePanel.add(new JScrollPane(playListJList));

		middlePanel.add(Box.createRigidArea(new Dimension(5,36)));
		
		this.add(middlePanel);

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		rightPanel.add(new JLabel("Current User Info"));
		
		rightPanel.add(Box.createRigidArea(new Dimension(5, 32)));
		
		currentUserInfoArea = new JTextArea(100, 1);
		currentUserInfoArea.setEditable(false);
		rightPanel.add(currentUserInfoArea);
		
		updateCurrentStudentData();
		
		rightPanel.add(Box.createRigidArea(new Dimension(5,5)));
		
		userNameInputField = new JTextField("User name");
		rightPanel.add(userNameInputField);

		userPasswordField = new JPasswordField("XXXX");
		rightPanel.add(userPasswordField);
		
		JPanel loginLogout = new JPanel();

		login = new JButton();
		login.addActionListener(new loginButtonListener());
		login.setText("Login");
		loginLogout.add(login, BorderLayout.WEST);
		
		logout = new JButton();
		logout.addActionListener(new logoutButtonListener());
		logout.setText("Logout");
		loginLogout.add(logout, BorderLayout.EAST);
		
		rightPanel.add(loginLogout);	
		
		this.add(rightPanel);

		this.setSize(900, 550);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/*
	 * Up dates all current data for the currently logged in student.
	 */
	private void updateCurrentStudentData() {
		if(jukebox.getLoggedIn() == true){
			this.currentUserInfoArea.setText("User Name: "
					+ jukebox.getCurrentStudent().getName() + "\n" + "Time left: "
					+ jukebox.getCurrentStudent().getSecondsLeftInHMS() + "\n"
					+ "Number of Plays Today: " + jukebox.getCurrentStudent().getPlaysToday() + "\n"
					+ "Number of Plays Left Today: " + jukebox.getCurrentStudent().getPlaysLeft());
		}else{
			this.currentUserInfoArea.setText("User Name: No User Currently Logged On"
					+ "\n" + "Time left: "
					+ "--:--:--" + "\n"
					+ "Number of Plays Today: --\n"
					+ "Number of Plays Left Today: --");
		}
	}

	/*
	 * Listens for a login attempt. Then runs the logic to see if it is a valid user.
	 */
	private class loginButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (jukebox.getStudents().login(userNameInputField.getText(),
					userPasswordField.getPassword())) {
				jukebox.setCurrentStudent(jukebox.getStudents().getStudentByName(userNameInputField
						.getText()));
				updateCurrentStudentData();
				userNameInputField.setText("User name");
				userPasswordField.setText("XXXX");
			} else {
				JOptionPane.showMessageDialog(new JFrame(),
						"The user name and password combination is invalid.");
				userNameInputField.setText("User name");
				userPasswordField.setText("XXXX");
			}
		}
	}

	/*
	 * Listens for a logout attempt. Then clears fields and sets
	 * current user to Null.
	 */
	private class logoutButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(jukebox.getLoggedIn() == true){
				jukebox.logout();
				updateCurrentStudentData();
				userNameInputField.setText("User name");
				userPasswordField.setText("XXXX");
			}
		}
	}
	
	/*
	 * Listens for the current songs status. It then gets the tells
	 * the user that information..
	 */
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
				if(jukebox.getLoggedIn()){
					if (jukebox.getSongs().getSongAt(selectedRow).canBePlayedAgainToday()
							&& jukebox.getCurrentStudent().canPlay(jukebox.getSongs().getSongAt(selectedRow))) {
						jukebox.getPlayList().addSong(jukebox.getSongs().getSongAt(selectedRow));
						jukebox.getSongs().getSongAt(selectedRow).play();
						jukebox.getCurrentStudent().play(jukebox.getSongs().getSongAt(selectedRow));
						updateCurrentStudentData();
					}
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
