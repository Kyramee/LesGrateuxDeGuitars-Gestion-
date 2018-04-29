package modele;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import controler.controlerSysteme;
import vue.vueGestionAlbum;

public class modeleJTableAlbum extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private final String[] titreColonnes = { "Id", "Titre", "Prix", "Genre", "Date de Sortie", "Distribution" };
	private ArrayList<Album> donnes;
	private vueGestionAlbum vue;

	public modeleJTableAlbum( ArrayList<Album> donnes, vueGestionAlbum vue ) {
		this.donnes = donnes;
		this.vue = vue;
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
	public Object getValueAt( int rowIndex, int columnIndex ) {
		switch ( columnIndex ) {
		case 0:
			return this.donnes.get( rowIndex ).getId();
		case 1:
			return this.donnes.get( rowIndex ).getTitre();
		case 2:
			return this.donnes.get( rowIndex ).getPrix();
		case 3:
			return this.donnes.get( rowIndex ).getGenre();
		case 4:
			return this.donnes.get( rowIndex ).getDate();
		case 5:
			return this.donnes.get( rowIndex ).getMaison();
		default:
			throw new IllegalArgumentException( " index de colonne invalide: " + columnIndex );
		}
	}

	@Override
	public Class<?> getColumnClass( int columnIndex ) {
		switch ( columnIndex ) {
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return Integer.class;
		case 3:
		case 4:
		case 5:
			return String.class;
		default:
			throw new IllegalArgumentException( " index de colonne invalide: " + columnIndex );
		}
	}

	@Override
	public boolean isCellEditable( int rowIndex, int columnIndex ) {
		return columnIndex != 0;
	}

	@Override
	public void setValueAt( Object aValue, int rowIndex, int columnIndex ) {
		Album row = this.donnes.get( rowIndex );

		switch ( columnIndex ) {
		case 1:
			valueCaseOne( aValue, row );
			break;
		case 2:
			valueCaseTwo( aValue, row );
			break;
		case 3:
			valueCaseThree( aValue, row );
			break;
		case 4:
			valueCaseFour( aValue, row );
			break;
		case 5:
			valueCaseFive( aValue, row );
			break;
		}
	}

	private void valueCaseOne( Object aValue, Album row ) {
		if ( ( (String) aValue ).length() > 30 ) {
			JOptionPane.showMessageDialog( vue, "Le titre doit avoir moins de 30 caract\u00E8re", "Erreur",
					JOptionPane.ERROR_MESSAGE );
		} else {
			controlerSysteme cs = new controlerSysteme();
			if ( cs.containtAlbum( (String) aValue ) ) {
				JOptionPane.showMessageDialog( this.vue,
						"L'album " + (String) aValue + " est d\u00E9j\u00E0 pr\u00E9sent", "Erreur",
						JOptionPane.ERROR_MESSAGE );
			} else {
				row.setTitre( (String) aValue );
				cs.modifierAlbumTitre( row.getId(), (String) aValue );
				this.vue.getJText( 1 ).setText( (String) aValue );
			}
		}
	}

	private void valueCaseTwo( Object aValue, Album row ) {
		if ( String.valueOf( aValue ).matches( "^\\d+$" ) ) {
			controlerSysteme cs = new controlerSysteme();
			row.setPrix( String.valueOf( aValue ) );
			cs.modifierAlbumPrix( row.getId(), String.valueOf( aValue ) );
			this.vue.getJText( 2 ).setText( String.valueOf( aValue ) );
		} else {
			JOptionPane.showMessageDialog( this.vue, "Le prix doit \u00EAtre un chiffreS", "Erreur",
					JOptionPane.ERROR_MESSAGE );
		}
	}

	private void valueCaseThree( Object aValue, Album row ) {
		if ( ( (String) aValue ).length() > 30 ) {
			JOptionPane.showMessageDialog( vue, "Le genre doit avoir moins de 30 caract\u00E8re", "Erreur",
					JOptionPane.ERROR_MESSAGE );
		} else {
			controlerSysteme cs = new controlerSysteme();
			row.setGenre( (String) aValue );
			cs.modifierAlbumGenre( row.getId(), (String) aValue );
			this.vue.getJText( 3 ).setText( (String) aValue );
		}
	}

	private void valueCaseFour( Object aValue, Album row ) {
		if ( ( (String) aValue ).matches( "^[12]\\\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\\\d|3[01])$" ) ) {
			controlerSysteme cs = new controlerSysteme();
			row.setDate( (String) aValue );
			cs.modifierAlbumPrix( row.getId(), (String) aValue );
			this.vue.getJText( 4 ).setText( (String) aValue );
		} else {
			JOptionPane.showMessageDialog( this.vue, "Le prix doit \u00EAtre du format AAAA-MM-JJ", "Erreur",
					JOptionPane.ERROR_MESSAGE );
		}
	}

	private void valueCaseFive( Object aValue, Album row ) {
		if ( ( (String) aValue ).length() > 30 ) {
			JOptionPane.showMessageDialog( vue, "La maison de distribution doit avoir moins de 30 caract\u00E8re",
					"Erreur", JOptionPane.ERROR_MESSAGE );
		} else {
			controlerSysteme cs = new controlerSysteme();
			row.setMaison( (String) aValue );
			cs.modifierAlbumGenre( row.getId(), (String) aValue );
			this.vue.getJText( 5 ).setText( (String) aValue );
		}
	}

	public String getColumnName( int columnIndex ) {
		return this.titreColonnes[columnIndex];
	}

	public Album containt( String id ) {
		for ( Album album : donnes ) {
			if ( album.getId().equals( id ) ) {
				return album;
			}
		}
		return null;
	}

	public void setDonnees( ArrayList<Album> donnes ) {
		this.donnes = donnes;
		fireTableDataChanged();
	}
}
