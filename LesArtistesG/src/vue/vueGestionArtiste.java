package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import modele.Album;
import modele.modeleSmallJTableAlbum;

public class vueGestionArtiste extends JPanel {
	private static final long serialVersionUID = 1L;

	private String[] nomEtiquette = { "Id", "Nom", "Membre", "Url de la photo", "Photo et album" };
	private String[] nomRadio = { "Oui", "Non" };

	private JLabel photo;
	private modeleSmallJTableAlbum album;

	private JLabel[] etiquette = new JLabel[nomEtiquette.length];
	private JLabel[] erreur = new JLabel[nomEtiquette.length - 1];
	private JTextField[] jText = new JTextField[3];
	private JRadioButton[] jRadio = new JRadioButton[2];

	public vueGestionArtiste() {
		super( new GridBagLayout() );
		GridBagConstraints constraint = new GridBagConstraints();

		settingNomEtiquette( constraint );
		settingErreur( constraint );
		settingButtonGroup( constraint );
		settingJText( constraint );

		this.photo = new JLabel();
		constraint.gridx = 1;
		constraint.gridy = 4;
		add( this.photo, constraint );

		constraint.gridx = 3;
		constraint.fill = GridBagConstraints.NONE;

		this.album = new modeleSmallJTableAlbum( new ArrayList<>() );
		JScrollPane sp = new JScrollPane( new JTable( this.album ) );
		sp.setPreferredSize( new Dimension( 200, 80 ) );

		add( sp, constraint );
	}

	private void settingJText( GridBagConstraints constraint ) {
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 2;
		constraint.gridy = 1;

		for ( int i = 0; i < this.jText.length; i++ ) {
			this.jText[i] = new JTextField();
			constraint.gridwidth = 2;
			add( this.jText[i], constraint );
			constraint.gridy = 3;
		}

		this.jText[2] = new JTextField();
		constraint.gridy = 0;
		add( this.jText[2], constraint );
	}

	private void settingButtonGroup( GridBagConstraints constraint ) {
		constraint.gridx = 2;
		constraint.gridy = 2;
		ButtonGroup bg = new ButtonGroup();

		for ( int i = 0; i < this.jRadio.length; i++ ) {
			this.jRadio[i] = new JRadioButton( this.nomRadio[i] );
			bg.add( this.jRadio[i] );
			add( this.jRadio[i], constraint );
			constraint.gridx++;
		}
	}

	private void settingErreur( GridBagConstraints constraint ) {
		constraint.gridx = 4;
		constraint.gridy = 0;

		for ( int i = 0; i < this.erreur.length; i++ ) {
			this.erreur[i] = new JLabel();
			this.erreur[i].setForeground( Color.RED );
			add( this.erreur[i], constraint );
			constraint.gridy++;
		}
	}

	private void settingNomEtiquette( GridBagConstraints constraint ) {
		constraint.fill = GridBagConstraints.NONE;
		constraint.weightx = 1;
		constraint.weighty = 1;
		constraint.gridheight = 1;
		constraint.gridwidth = 1;
		constraint.insets = new Insets( 5, 5, 5, 5 );
		constraint.gridx = 0;
		constraint.gridy = 0;

		for ( int i = 0; i < this.etiquette.length; i++ ) {
			this.etiquette[i] = new JLabel( this.nomEtiquette[i] + " :" );
			add( this.etiquette[i], constraint );
			constraint.gridy++;
		}
	}

	public void setErreur( int index, String erreur ) {
		this.erreur[index].setText( erreur );
	}

	public JTextField getJText( int index ) {
		return this.jText[index];
	}

	public JRadioButton getRadio( int index ) {
		return this.jRadio[index];
	}

	public void effacerChamp() {
		for ( JTextField text : jText ) {
			text.setText( "" );
		}

		effacerErreur();

		for ( JRadioButton radio : jRadio ) {
			radio.setSelected( false );
		}
	}

	public void effacerErreur() {
		for ( JLabel erreur : erreur ) {
			erreur.setText( "" );
		}
	}

	public void setPhoto( String url ) {
		try {
			this.photo.setIcon( new ImageIcon( getClass().getResource( "../images_Artiste/" + url ) ) );
		} catch ( Exception e ) {
			this.photo.setIcon( new ImageIcon( getClass().getResource( "../images/erreur.jpeg" ) ) );
		}

	}

	public void setDonnesAlbum( ArrayList<Album> donnes ) {
		this.album.setDonnees( donnes );
	}
}