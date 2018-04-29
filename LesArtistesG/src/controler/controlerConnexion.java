package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vue.vueConnexion;
import vue.vueJFrame;

public class controlerConnexion implements ActionListener {

	private controlerSysteme cs;
	private vueConnexion vue;
	private vueJFrame parent;

	public controlerConnexion(vueJFrame parent, vueConnexion vue) {
		this.parent = parent;
		this.cs = parent.getCs();
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (((JButton) e.getSource()).getText().equals("Quitter")) {
			System.exit(0);
		} else {
			if (cs.hasDataBase()) {
				if (this.vue.getTextNom().isEmpty() | this.vue.getTextPassword().isEmpty()) {
					JOptionPane.showMessageDialog(parent, "Le formulaire n'est pas remplis correctement", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				} else if (cs.checkUser(vue.getTextNom(), vue.getTextPassword())) {
					vueJFrame v = new vueJFrame(cs);
					v.vueOption();
					parent.dispose();
				} else {
					JOptionPane.showMessageDialog(parent, "Le nom d'utilisateur ou le mot de passe est invalide",
							"Erreur", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(parent, "Il n'y a pas de base de donn\u00E9es connect\u00E9e", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
