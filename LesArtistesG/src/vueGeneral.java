import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class vueGeneral extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public vueGeneral(JFrame parent) {
		super(new GridBagLayout());
		String[] bouton = {"Ajouter", "Modifier", "Supprimer", "Rechercher", "Quitter"};
		
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
			JButton b = new JButton(bouton[i]);
			add(b, constraint);
			constraint.gridy++;
		}
	}
}
