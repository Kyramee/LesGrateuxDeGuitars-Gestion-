package vue;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class vueGestionArtiste extends JPanel {
	private static final long serialVersionUID = 1L;

	private String[] nomEtiquette = { "Id", "Nom", "Membre", "Url de la photo" };
	private String[] nomRadio = { "Oui", "Non" };

	private JLabel[] etiquette = new JLabel[nomEtiquette.length];
	private JLabel[] erreur = new JLabel[nomEtiquette.length];
	private JTextField[] jText = new JTextField[3];
	private JRadioButton[] jRadio = new JRadioButton[2];

	public vueGestionArtiste() {
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

		for (int i = 0; i < this.etiquette.length; i++) {
			this.etiquette[i] = new JLabel(this.nomEtiquette[i] + " :");
			add(this.etiquette[i], constraint);
			constraint.gridy++;
		}

		constraint.gridx = 4;
		constraint.gridy = 0;

		for (int i = 0; i < this.erreur.length; i++) {
			this.erreur[i] = new JLabel();
			this.erreur[i].setForeground(Color.RED);
			add(this.erreur[i], constraint);
			constraint.gridy++;
		}

		constraint.gridx = 2;
		constraint.gridy = 2;
		ButtonGroup bg = new ButtonGroup();

		for (int i = 0; i < this.jRadio.length; i++) {
			this.jRadio[i] = new JRadioButton(this.nomRadio[i]);
			bg.add(this.jRadio[i]);
			add(this.jRadio[i], constraint);
			constraint.gridx++;
		}

		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 2;
		constraint.gridy = 1;

		for (int i = 0; i < this.jText.length; i++) {
			this.jText[i] = new JTextField();
			constraint.gridwidth = 2;
			add(this.jText[i], constraint);
			constraint.gridy = 3;
		}

		this.jText[2] = new JTextField();
		constraint.gridy = 0;
		add(this.jText[2], constraint);
	}

	public void setErreur(int index, String erreur) {
		this.erreur[index].setText(erreur);
	}

	public JTextField getJText(int index) {
		return this.jText[index];
	}

	public JRadioButton getRadio(int index) {
		return this.jRadio[index];
	}

	public void effacerChamp() {
		for (JTextField text : jText) {
			text.setText("");
		}

		effacerErreur();

		for (JRadioButton radio : jRadio) {
			radio.setSelected(false);
		}
	}

	public void effacerErreur() {
		for (JLabel erreur : erreur) {
			erreur.setText("");
		}
	}
}