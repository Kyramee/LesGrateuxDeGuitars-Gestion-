package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controler.controlerAjouterArtiste;

public class vueAjouterArtiste extends JPanel {
	private static final long serialVersionUID = 1L;

	private String[] nomEtiquette = { "Nom", "Membre", "Url de la photo" };
	private String[] nomRadio = { "Oui", "Non" };

	private JLabel[] etiquette = new JLabel[3];
	private JLabel[] erreur = new JLabel[3];
	private JTextField[] jText = new JTextField[2];
	private JRadioButton[] jRadio = new JRadioButton[2];
	private JFrame parent;

	public vueAjouterArtiste(JFrame parent) {
		super(new BorderLayout());
		this.parent = parent;
		add(centerPanel(), BorderLayout.CENTER);
		add(southPanel(), BorderLayout.SOUTH);
	}
	
	public void close() {
		this.parent.dispose();
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

	public JPanel centerPanel() {
		JPanel jp = new JPanel(new GridBagLayout());
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
			jp.add(this.etiquette[i], constraint);
			constraint.gridy++;
		}

		constraint.gridx = 4;
		constraint.gridy = 0;

		for (int i = 0; i < this.erreur.length; i++) {
			this.erreur[i] = new JLabel();
			this.erreur[i].setForeground(Color.RED);
			jp.add(this.erreur[i], constraint);
			constraint.gridy++;
		}


		constraint.gridx = 1;
		constraint.gridy = 1;
		ButtonGroup bg = new ButtonGroup();
		
		for (int i = 0; i < this.jRadio.length; i++) {
			this.jRadio[i] = new JRadioButton(this.nomRadio[i]);
			bg.add(this.jRadio[i]);
			jp.add(this.jRadio[i], constraint);
			constraint.gridx++;
		}
		
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 1;
		constraint.gridy = 0;

		for (int i = 0; i < this.jText.length; i++) {
			this.jText[i] = new JTextField();
			constraint.gridwidth = 2;
			jp.add(this.jText[i], constraint);
			constraint.gridy = 2;
		}
		
		return jp;
	}

	public JPanel southPanel() {
		JPanel jp = new JPanel();
		Dimension d = new Dimension(100, 40);
		JButton ajouter = new JButton("Ajouter");
		ajouter.setPreferredSize(d);
		ajouter.addActionListener(new controlerAjouterArtiste(this));
		jp.add(ajouter);

		return jp;
	}
}
