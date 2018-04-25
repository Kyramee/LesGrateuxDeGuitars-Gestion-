package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.vueJFrame;

public class controlerIndex implements ActionListener {

	private vueJFrame parent;
	private Boolean vue;

	public controlerIndex(vueJFrame parent, Boolean vue) {
		this.parent = parent;
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (((JButton) e.getSource()).getText()) {
		case "Ajouter":
			vueJFrame vf1 = new vueJFrame();
			vf1.vueArtisteAlbum((this.vue)? 0 : 1);
			break;
		case "Modifier":
			vueJFrame vf2 = new vueJFrame();
			vf2.vueArtisteAlbum((this.vue)? 2 : 3);
			break;
		case "Supprimer":
			vueJFrame vf3 = new vueJFrame();
			vf3.vueArtisteAlbum((this.vue)? 4 : 5);
			break;
		case "Rechercher":
			vueJFrame vf4 = new vueJFrame();
			vf4.vueArtisteAlbum((this.vue)? 6 : 7);
			break;
		case "Quitter":
			vueJFrame vf5 = new vueJFrame();
			vf5.init();
		}
		this.parent.dispose();
	}

}
