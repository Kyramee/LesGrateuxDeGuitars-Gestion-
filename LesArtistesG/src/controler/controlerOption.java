package controler;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import vue.vueJFrame;

public class controlerOption implements ActionListener {
	
	private JFrame parent;
	
	public controlerOption(JFrame parent) {
		this.parent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String option = ((JButton) e.getSource()).getText();

		switch (option) {
		case "Artiste":
			afficher(true);
			break;
		case "Album":
			afficher(false);
			break;
		case "Quitter":
			System.exit(0);
		}

	}
	
	public void afficher(Boolean vue) {
		vueJFrame frame = new vueJFrame();
		frame.vueArtisteAlbum(vue);
		this.parent.dispose();
	}
}
