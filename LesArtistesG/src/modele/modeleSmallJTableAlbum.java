package modele;


import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class modeleSmallJTableAlbum extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private final String[] titreColonnes = { "Titre", "Sortie" };
	private ArrayList<Album> donnes;

	public modeleSmallJTableAlbum(ArrayList<Album> donnes) {
		this.donnes = donnes;
	}
	

	@Override
	public int getRowCount() {
		return this.donnes.size();
	}

	@Override
	public int getColumnCount() {
		return this.titreColonnes.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return this.donnes.get(rowIndex).getTitre();
		case 1:
			return this.donnes.get(rowIndex).getDate();
		default:
			throw new IllegalArgumentException(" index de colonne invalide: " + columnIndex);
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
		case 1:
			return String.class;
		default:
			throw new IllegalArgumentException(" index de colonne invalide: " + columnIndex);
		}
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return this.titreColonnes[columnIndex];
	}

	public void setDonnees(ArrayList<Album> donnes) {
		this.donnes = donnes;
		fireTableDataChanged();
	}
}
