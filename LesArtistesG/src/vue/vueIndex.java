package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import controler.controlerIndexArtiste;
import modele.modeleJTableArtiste;

public class vueIndex extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private final String[] nomBouton = {"Ajouter", "Modifier", "Supprimer", "Rechercher", "Quitter"};
	private JButton[] bouton = new JButton[5];
	
	
	public vueIndex() {
		super(new GridBagLayout());

		GridBagConstraints constraint = new GridBagConstraints();
		
		constraint.fill = GridBagConstraints.BOTH;
		constraint.weightx = 1;
		constraint.weighty = 1;
		constraint.gridheight = 1;
		constraint.gridwidth = 1;
		constraint.insets = new Insets(5, 5, 5, 5);
		constraint.gridx = 0;
		constraint.gridy = 0;
		
		for(int i = 0; i < 5; i++) {
			this.bouton[i] = new JButton(this.nomBouton[i]);
			this.add(this.bouton[i], constraint);
			constraint.gridy++;
		}
	}
	
	public void setListenerArtiste(vueJFrame parent, vueGestionArtiste vueArtiste, modeleJTableArtiste modele) {
		
		for(int i = 0; i < 5; i++) {
			this.bouton[i].addActionListener(new controlerIndexArtiste(parent, vueArtiste, modele));
		}
	}
}
