package modele;


import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class modeleJTableAlbum extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private final String[] titreColonnes = { "Id", "Titre", "Prix", "Genre", "Date de Sortie", "Distribution" };
	private ArrayList<Album> donnes;

	public modeleJTableAlbum(ArrayList<Album> donnes) {
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
				return this.donnes.get(rowIndex).getId();
			case 1:
				return this.donnes.get(rowIndex).getTitre();
			case 2:
				return this.donnes.get(rowIndex).getPrix();
			case 3:
				return this.donnes.get(rowIndex).getGenre();
			case 4:
				return this.donnes.get(rowIndex).getDate();
			case 5:
				return this.donnes.get(rowIndex).getMaison();
			default:
				throw new IllegalArgumentException(" index de colonne invalide: " + columnIndex);
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0:
				return Integer.class;
			case 1:
				return String.class;
			case 2:
				return Double.class;
			case 3:
			case 4:
			case 5:
				return String.class;
			default:
				throw new IllegalArgumentException(" index de colonne invalide: " + columnIndex);
		}
	}

	public String getColumnName(int columnIndex) {
		return this.titreColonnes[columnIndex];
	}
	
	public Album containt(String id) {
		for (Album album : donnes) {
			if (album.getId() == Integer.parseInt(id)) {
				return album;
			}
		}
		return null;
	}
	
	public void setDonnees(ArrayList<Album> donnes) {
		this.donnes = donnes;
		fireTableDataChanged();
	}
}
