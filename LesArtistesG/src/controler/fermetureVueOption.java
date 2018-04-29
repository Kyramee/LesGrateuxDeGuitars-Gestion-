package controler;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import vue.vueJFrame;

public class fermetureVueOption extends WindowAdapter {
	private vueJFrame vue;

	public fermetureVueOption( vueJFrame vue ) {
		this.vue = vue;
	}

	@Override
	public void windowClosing( WindowEvent e ) {
		if ( JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog( this.vue, "Voulez-vous vraiment quitt\u00E9?",
				"Confirmation d\u00E9connexion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE ) ) {
			try {
				this.vue.getCs().getConnBD().closeConnexion();
			} catch ( ClassNotFoundException e1 ) {
				e1.printStackTrace();
			} catch ( SQLException e1 ) {
				e1.printStackTrace();
			}
			this.vue.dispose();
		}
	}
}
