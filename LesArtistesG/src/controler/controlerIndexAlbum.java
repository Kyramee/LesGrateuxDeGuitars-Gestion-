package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import modele.modeleJTableAlbum;
import vue.vueGestionAlbum;
import vue.vueJFrame;

public class controlerIndexAlbum implements ActionListener {

	private vueJFrame parent;
	private vueGestionAlbum vue;
	private modeleJTableAlbum modele;
	private controlerSysteme cs;

	public controlerIndexAlbum( vueJFrame parent, vueGestionAlbum vue, modeleJTableAlbum modele ) {
		this.parent = parent;
		this.vue = vue;
		this.modele = modele;
		this.cs = parent.getCs();
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		String id = this.vue.getJText( 0 ).getText();
		String titre = this.vue.getJText( 1 ).getText();
		String prix = this.vue.getJText( 2 ).getText();
		String genre = this.vue.getJText( 3 ).getText();
		String anneeSortie = this.vue.getJText( 4 ).getText();
		String maisonDistribution = this.vue.getJText( 5 ).getText();
		String imageUrl = this.vue.getJText( 6 ).getText();
		String artiste = trouverArtiste();

		switch ( ( (JButton) e.getSource() ).getText() ) {
		case "Ajouter":
			ajouter( titre, prix, genre, anneeSortie, maisonDistribution, imageUrl, artiste );
			break;
		case "Modifier":
			modifier( id, titre, prix, genre, anneeSortie, maisonDistribution, imageUrl, artiste );
			break;
		case "Supprimer":
			supprimer( id );
			break;
		case "Rechercher":
			rechercher( id, titre, prix, genre, anneeSortie, maisonDistribution, imageUrl, artiste );
			break;
		case "Quitter":
			quitter();
		}
	}

	private void quitter() {
		vueJFrame vf = new vueJFrame( this.cs );
		vf.vueOption();
		this.parent.dispose();
	}

	private String trouverArtiste() {
		String artiste;
		if ( this.vue.getArtiste().getSelectedIndex() == 0 ) {
			artiste = "";
		} else {
			controlerSysteme cs = new controlerSysteme();
			cs.accessTabArtiste( "", this.vue.getArtiste().getSelectedItem().toString(), 2 );
			artiste = cs.getTabArtiste().get( 0 ).getId();
		}
		this.vue.effacerErreur();
		return artiste;
	}

	private void rechercher( String id, String titre, String prix, String genre, String anneeSortie,
			String maisonDistribution, String imageUrl, String artisteId ) {
		if ( checkNumber( id, prix ) ) {
			controlerSysteme cs = new controlerSysteme();
			cs.accessTabAlbum( id, titre, prix, genre, anneeSortie, maisonDistribution, imageUrl, artisteId );
			this.modele.setDonnees( cs.getTabAlbum() );
			this.vue.effacerErreur();
		}
	}

	private void supprimer( String id ) {
		if ( checkId( id ) ) {
			if ( JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog( parent,
					"Voulez-vous supprimer l'album avec l'id " + id + "?", "Confirmation supprimer",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE ) ) {
				this.cs.supprimer( "Album", Integer.parseInt( this.vue.getJText( 0 ).getText() ) );
				this.modele.setDonnees( cs.getTabAlbum() );
				this.vue.effacerChamp();
			}
		}
	}

	private void modifier( String id, String titre, String prix, String genre, String anneeSortie,
			String maisonDistribution, String imageUrl, String artisteId ) {
		if ( checkId( id ) && checkInfo() ) {
			this.cs.modifierAlbum( id, titre, prix, genre, anneeSortie, maisonDistribution, imageUrl, artisteId );
			this.vue.setImage( imageUrl );
			this.vue.effacerChamp();
		}
	}

	private void ajouter( String titre, String prix, String genre, String anneeSortie, String maisonDistribution,
			String imageUrl, String artisteId ) {
		if ( checkInfo() ) {
			if ( this.cs.containtAlbum( titre ) ) {
				JOptionPane.showMessageDialog( parent, "L'album " + titre + " est d\u00E9j\u00E0 pr\u00E9sent",
						"Erreur", JOptionPane.ERROR_MESSAGE );
			} else {
				this.cs.ajouterAlbum( titre, prix, genre, anneeSortie, maisonDistribution, imageUrl, artisteId );
				this.modele.setDonnees( cs.getTabAlbum() );
				this.vue.effacerChamp();
			}

		}
	}

	private boolean checkNumber( String id, String prix ) {
		Boolean ok = true;

		if ( id.matches( "^[0-9]*$" ) | id.isEmpty() ) {
			this.vue.setErreur( 0, "" );
		} else {
			this.vue.setErreur( 0, "Le id doit \u00EAtre un nombre" );
			ok = false;
		}

		if ( prix.matches( "^\\d+$" ) | prix.isEmpty() ) {
			this.vue.setErreur( 2, "" );
		} else {
			this.vue.setErreur( 2, "Le prix doit \u00EAtre un nombre" );
			ok = false;
		}

		return ok;
	}

	private boolean checkId( String id ) {
		Boolean ok = true;

		if ( id.isEmpty() ) {
			this.vue.setErreur( 0, "Remplir le champ" );
			ok = false;
		} else if ( id.matches( "^[0-9]+$" ) ) {
			if ( this.modele.containt( id ) == null ) {
				this.vue.setErreur( 0, "Le id doit n'est pas valide" );
				ok = false;
			} else {
				this.vue.setErreur( 0, "" );
			}
		} else {
			this.vue.setErreur( 0, "Le id doit \u00EAtre un nombre" );
			ok = false;
		}

		return ok;
	}

	private Boolean checkInfo() {
		Boolean[] verif = new Boolean[7];
		int index = 1;

		verif[index - 1] = checkTitre( index++ );
		verif[index - 1] = checkPrix( index++ );
		verif[index - 1] = checkGenre( index++ );
		verif[index - 1] = checkDate( index++ );
		verif[index - 1] = checkMaison( index++ );
		verif[index - 1] = checkUrl( index++ );
		verif[index - 1] = checkArtiste( index );

		for ( Boolean bool : verif ) {
			if ( !bool ) {
				return false;
			}
		}
		return true;
	}

	private Boolean checkArtiste( int index ) {
		Boolean ok = true;

		if ( this.vue.getArtiste().getSelectedItem().toString().isEmpty() ) {
			this.vue.setErreur( index, "S\u00E9lectionnez un artiste" );
			ok = false;
		} else {
			this.vue.setErreur( index, "" );
		}

		return ok;
	}

	private Boolean checkUrl( int index ) {
		Boolean ok = true;

		if ( this.vue.getJText( index ).getText().isEmpty() ) {
			this.vue.setErreur( index, "Remplir le champ" );
			ok = false;
		} else if ( this.vue.getJText( index ).getText().length() > 30 ) {
			this.vue.setErreur( index, "L'url doit avoir moins de 255 caract\\u00E8" );
			ok = false;
		} else {
			this.vue.setErreur( index, "" );
		}
		return ok;
	}

	private Boolean checkMaison( int index ) {
		Boolean ok = true;

		if ( this.vue.getJText( index ).getText().isEmpty() ) {
			this.vue.setErreur( index, "Remplir le champ" );
			ok = false;
		} else if ( this.vue.getJText( index ).getText().length() > 30 ) {
			this.vue.setErreur( index, "La maison de distribution doit avoir moins de 30 caract\u00E8re" );
			ok = false;
		} else {
			this.vue.setErreur( index, "" );
		}
		return ok;
	}

	private Boolean checkDate( int index ) {
		Boolean ok = true;

		if ( this.vue.getJText( index ).getText().isEmpty() ) {
			this.vue.setErreur( index, "Remplir le champ" );
			ok = false;
		} else if ( this.vue.getJText( index ).getText()
				.matches( "^[12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$" ) ) {
			this.vue.setErreur( index, "" );
		} else {
			this.vue.setErreur( index, "La date doit \u00EAtre valide et du format AAAA-MM-JJ" );
			ok = false;

		}
		return ok;
	}

	private Boolean checkGenre( int index ) {
		Boolean ok = true;

		if ( this.vue.getJText( index ).getText().isEmpty() ) {
			this.vue.setErreur( index, "Remplir le champ" );
			ok = false;
		} else if ( this.vue.getJText( index ).getText().length() > 30 ) {
			this.vue.setErreur( index, "Le genre doit avoir moins de 30 caract\u00E8re" );
			ok = false;
		} else {
			this.vue.setErreur( index, "" );
		}
		return ok;
	}

	private Boolean checkPrix( int index ) {
		Boolean ok = true;

		if ( this.vue.getJText( index ).getText().isEmpty() ) {
			this.vue.setErreur( index, "Remplir le champ" );
			ok = false;
		} else if ( this.vue.getJText( index ).getText().matches( "^\\d+$" ) ) {
			this.vue.setErreur( index, "" );
		} else {
			this.vue.setErreur( index, "Le champ doit \u00EAtre un chiffre" );
			ok = false;
		}
		return ok;
	}

	private Boolean checkTitre( int index ) {
		Boolean ok = true;

		if ( this.vue.getJText( index ).getText().isEmpty() ) {
			this.vue.setErreur( index, "Remplir le champ" );
			ok = false;
		} else if ( this.vue.getJText( index ).getText().length() > 30 ) {
			this.vue.setErreur( index, "Le titre doit avoir moins de 30 caract\u00E8re" );
			ok = false;
		} else {
			this.vue.setErreur( index, "" );
		}
		return ok;
	}
}