package model;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class Songs implements ListModel<Song>, TableModel {
	
	private ArrayList<Song> songs;
	private LinkedList<ListDataListener> listDataListeners;
	private LinkedList<TableModelListener> tableModelListeners;
	
	public Songs(){
		songs = new ArrayList<Song>();
		listDataListeners = new LinkedList<ListDataListener>();
		tableModelListeners = new LinkedList<TableModelListener>();
	}

	public void addSong(Song song){
		songs.add(song);
	}

	
	@Override
	public void addTableModelListener(TableModelListener arg0) {
		tableModelListeners.add(arg0);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex)
		{
		case 0:	
		case 1: return String.class; // first two columns are strings
		case 2:	return Integer.class; // 3rd column is Int
		}
		return null;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex)
		{
		case 0:	return "Artist";
		case 1: return "Title";
		case 2:	return "Seconds";
		}
		return "error";
	}

	@Override
	public int getRowCount() {
		return songs.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex)
		{
		case 0:	return songs.get(rowIndex).getArtist();
		case 1: return songs.get(rowIndex).getTitle();
		case 2:	return songs.get(rowIndex).getLength();
		}
		return null;
	}
	
	public Song getSongAt(int rowIndex) {
		return songs.get(rowIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// No Removing
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// No Changing	
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		listDataListeners.add(l);
		
	}

	@Override
	public Song getElementAt(int index) {
		if (index < 0 || index > songs.size()) // check for valid index
			return null;
		
		return songs.get(index);
	}

	@Override
	public int getSize() {
		return songs.size();
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		listDataListeners.remove(l);
		
	}
}
