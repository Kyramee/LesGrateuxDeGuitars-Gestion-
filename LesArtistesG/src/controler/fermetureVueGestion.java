package controler;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import vue.vueJFrame;

public class fermetureVueGestion extends WindowAdapter {
	private vueJFrame vue;

	public fermetureVueGestion(vueJFrame vue) {
		this.vue = vue;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		vueJFrame vf = new vueJFrame(this.vue.getCs());
		vf.vueOption();
		this.vue.dispose();
	}
}
