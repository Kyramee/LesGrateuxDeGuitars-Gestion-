package modele;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class modeleJTableArtiste extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private final String[] titreColonnes = { "Id", "Nom", "Membre" };
	private ArrayList<Artiste> donnes;

	public modeleJTableArtiste(ArrayList<Artiste> donnes) {
		this.donnes = donnes;
	}
	
	public Artiste containt(int id) {
		for (Artiste artiste : donnes) {
			if (artiste.getId() == id) {
				return artiste;
			}
		}
		return null;
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
			return this.donnes.get(rowIndex).getNom();
		case 2:
			return this.donnes.get(rowIndex).getMembre();
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
			return Boolean.class;
		default:
			throw new IllegalArgumentException(" index de colonne invalide: " + columnIndex);
		}
	}

	public String getColumnName(int columnIndex) {
		return this.titreColonnes[columnIndex];
	}

	public void setDonnees(ArrayList<Artiste> donnes) {
		this.donnes = donnes;
		fireTableDataChanged();
	}
}
