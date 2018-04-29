package controler;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.vueJFrame;

public class controlerOption implements ActionListener {
	
	private vueJFrame parent;
	
	public controlerOption(vueJFrame parent) {
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
		vueJFrame frame = new vueJFrame(this.parent.getCs());
		frame.vueCentral(vue);
		this.parent.dispose();
	}
}
