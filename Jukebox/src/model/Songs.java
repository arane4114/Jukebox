/*
 * @ - Author: Abhishek Rane
 * @ - Author: Bryce Hammond
 */
package model;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/*
 * This is out collection class that forms the model that our GUI can read from.
 */
public class Songs implements ListModel<Song>, TableModel {

	private ArrayList<Song> songs;
	private LinkedList<ListDataListener> listDataListeners;
	private LinkedList<TableModelListener> tableModelListeners;

	/*
	 * Songs constructor.
	 * Takes no arguments.
	 */
	public Songs() {
		songs = new ArrayList<Song>();
		listDataListeners = new LinkedList<ListDataListener>();
		tableModelListeners = new LinkedList<TableModelListener>();
	}

	/*
	 * Adds song to current list.
	 */
	public void addSong(Song song) {
		songs.add(song);
	}

	/*
	 * Adds table listeners. 
	 */
	@Override
	public void addTableModelListener(TableModelListener arg0) {
		tableModelListeners.add(arg0);
	}

	/*
	 * Lets table know what will be held in each Column.
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
		case 1:
			return String.class; // first two columns are strings
		case 2:
			return Integer.class; // 3rd column is Int
		}
		return null;
	}

	/*
	 * Returns amount of Columns.
	 */
	@Override
	public int getColumnCount() {
		return 3;
	}

	/*
	 * Lets Table know the Column names.
	 */
	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Artist";
		case 1:
			return "Title";
		case 2:
			return "Seconds";
		}
		return "error";
	}

	/*
	 * Returns the number of Rows.
	 * Number of rows = size of songs.
	 */
	@Override
	public int getRowCount() {
		return songs.size();
	}

	/*
	 * Returns value at a row and column index.
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return songs.get(rowIndex).getArtist();
		case 1:
			return songs.get(rowIndex).getTitle();
		case 2:
			return songs.get(rowIndex).getLength();
		}
		return null;
	}

	/*
	 * Gets song that row represents. 
	 */
	public Song getSongAt(int rowIndex) {
		return songs.get(rowIndex);
	}

	/*
	 * Cells are not Editable.
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	/*
	 * No removing.
	 */
	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// No Removing
	}

	/*
	 * No setting values. 
	 */
	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// No Changing
	}

	/*
	 * Adding list data listeners.
	 */
	@Override
	public void addListDataListener(ListDataListener l) {
		listDataListeners.add(l);

	}

	/*
	 * Returns song at a index.
	 */
	@Override
	public Song getElementAt(int index) {
		if (index < 0 || index > songs.size()) // check for valid index
			return null;

		return songs.get(index);
	}

	/*
	 * Getter for row size of table.
	 */
	@Override
	public int getSize() {
		return songs.size();
	}

	/*
	 * Remove list data listener.
	 */
	@Override
	public void removeListDataListener(ListDataListener l) {
		listDataListeners.remove(l);

	}
}
