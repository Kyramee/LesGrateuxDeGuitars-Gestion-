package vue;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class vueGestionAlbum extends JPanel {
	private static final long serialVersionUID = 1L;

	private String[] nomEtiquette = { "Id", "Titre", "Prix", "Genre", "Ann\u00E9es de sortie",
			"Maison de distribution", "Image url", "Artiste" };

	private JLabel[] etiquette = new JLabel[nomEtiquette.length];
	private JLabel[] erreur = new JLabel[nomEtiquette.length];
	private JTextField[] jText = new JTextField[nomEtiquette.length];

	public vueGestionAlbum() {
		super(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();

		constraint.fill = GridBagConstraints.NONE;
		constraint.weightx = 1;
		constraint.weighty = 1;
		constraint.gridheight = 1;
		constraint.gridwidth = 1;
		constraint.insets = new Insets(5, 5, 5, 5);
		constraint.gridx = 0;
		constraint.gridy = 0;

		for (int i = 0; i < this.nomEtiquette.length; i++) {
			this.etiquette[i] = new JLabel(nomEtiquette[i]);
			this.erreur[i] = new JLabel("");
			this.erreur[i].setForeground(Color.RED);
			
			add(this.etiquette[i], constraint);
			constraint.gridx = 2;
			add(this.erreur[i], constraint);
			constraint.gridx = 0;
			constraint.gridy++;
		}
		
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 1;
		constraint.gridy = 0;
		
		for (int i = 0; i < this.nomEtiquette.length; i++) {
			this.jText[i] = new JTextField();
			
			add(this.jText[i], constraint);
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

		effacerErreur();
	}

	public void effacerErreur() {
		for (JLabel erreur : erreur) {
			erreur.setText("");
		}
	}
}
