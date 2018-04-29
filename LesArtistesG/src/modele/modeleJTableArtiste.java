package modele;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import controler.controlerSysteme;
import vue.vueGestionArtiste;

public class modeleJTableArtiste extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private final String[] titreColonnes = { "Id", "Nom", "Membre" };
	private ArrayList<Artiste> donnes;
	private vueGestionArtiste vue;

	public modeleJTableArtiste(ArrayList<Artiste> donnes, vueGestionArtiste vue) {
		this.donnes = donnes;
		this.vue = vue;
	}

	public Artiste containt(String id) {
		for (Artiste artiste : donnes) {
			if (artiste.getId().equals(id)) {
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

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex != 0;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Artiste row = this.donnes.get(rowIndex);

		switch (columnIndex) {
		case 1:

			if (((String) aValue).length() > 30) {
				JOptionPane.showMessageDialog(vue, "Le nom doit avoir moins de 30 caract\u00E8re", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			} else {
				controlerSysteme cs = new controlerSysteme();
				if (cs.containtArtiste((String) aValue)) {
					JOptionPane.showMessageDialog(this.vue,
							"L'artiste " + (String) aValue + " est d\u00E9j\u00E0 pr\u00E9sent", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				} else {
					row.setNom((String) aValue);
					cs.modifierArtisteNom(row.getId(), (String) aValue);
					this.vue.getJText(0).setText((String) aValue);
				}
			}

			break;
		case 2:
			row.setMembre((Boolean) aValue);
			controlerSysteme cs = new controlerSysteme();
			cs.modifierArtisteMembre(row.getId(), (Boolean) aValue); 
			
			if ((Boolean) aValue) {
				this.vue.getRadio(0).setSelected(true);
			} else {
				this.vue.getRadio(1).setSelected(true);
			}
			
			break;
		default:
			break;
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
