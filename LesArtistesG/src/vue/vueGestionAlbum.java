package vue;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controler.controlerSysteme;
import modele.Artiste;

public class vueGestionAlbum extends JPanel {
	private static final long serialVersionUID = 1L;

	private String[] nomEtiquette = { "Id", "Titre", "Prix", "Genre", "Ann\u00E9es de sortie", "Maison de distribution",
			"Image url", "Artiste" };

	private JLabel[] etiquette = new JLabel[nomEtiquette.length];
	private JLabel[] erreur = new JLabel[nomEtiquette.length];
	private JTextField[] jText = new JTextField[nomEtiquette.length - 1];
	private JLabel image;
	private JComboBox<String> artiste;

	public vueGestionAlbum() {
		super(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();

		initParam(constraint);
		initLabel(constraint, 0, 0);
		constraint.gridy = 0;
		initLabel(constraint, 4, 3);

		constraint.gridy = 4;
		constraint.gridx = 2;
		add(new JLabel("Image"), constraint);

		this.image = new JLabel("");
		constraint.gridx = 3;
		add(this.image, constraint);

		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 1;
		constraint.gridy = 0;

		initJText(constraint);
		iniJComboBox(constraint);
	}

	private void iniJComboBox(GridBagConstraints constraint) {
		controlerSysteme cs = new controlerSysteme();

		constraint.gridy = 3;
		constraint.gridx = 4;
		this.artiste = new JComboBox<>();
		add(artiste, constraint);

		this.artiste.addItem("");

		for (Artiste artiste : cs.getTabArtiste()) {
			this.artiste.addItem(artiste.getNom());
		}
	}

	private void initParam(GridBagConstraints constraint) {
		constraint.fill = GridBagConstraints.NONE;
		constraint.weightx = 1;
		constraint.weighty = 1;
		constraint.gridheight = 1;
		constraint.gridwidth = 1;
		constraint.insets = new Insets(5, 5, 5, 5);
		constraint.gridx = 0;
		constraint.gridy = 0;
	}

	private void initJText(GridBagConstraints constraint) {
		int index = 0;
		for (; index < 4; index++) {
			this.jText[index] = new JTextField();

			add(this.jText[index], constraint);
			constraint.gridy++;
		}

		constraint.gridx = 4;
		constraint.gridy = 0;

		for (; index < jText.length; index++) {
			this.jText[index] = new JTextField();

			add(this.jText[index], constraint);
			constraint.gridy++;
		}
	}

	private void initLabel(GridBagConstraints constraint, int index, int x) {
		constraint.gridx = x;

		for (int i = index; i < index + 4; i++) {
			this.etiquette[i] = new JLabel(nomEtiquette[i]);
			this.erreur[i] = new JLabel();
			this.erreur[i].setForeground(Color.RED);

			add(this.etiquette[i], constraint);
			constraint.gridx = x + 2;
			add(this.erreur[i], constraint);
			constraint.gridx = x;
			constraint.gridy++;
		}
	}

	public void setErreur(int index, String erreur) {
		this.erreur[index].setText(erreur);
	}

	public JTextField getJText(int index) {
		return this.jText[index];
	}

	public void effacerChamp() {
		for (JTextField text : jText) {
			text.setText("");
		}
		
		this.artiste.setSelectedIndex(0);
		
		effacerErreur();
	}

	public void effacerErreur() {
		for (JLabel erreur : erreur) {
			erreur.setText("");
		}
	}

	public JComboBox<String> getArtiste() {
		return this.artiste;
	}
	
	public void setImage(String url) {
		try {
			this.image.setIcon(new ImageIcon(getClass().getResource("../images/" + url)));
		} catch (Exception e) {
			this.image.setIcon(new ImageIcon(getClass().getResource("../images/erreur.jpeg")));
		}
		
	}
}
