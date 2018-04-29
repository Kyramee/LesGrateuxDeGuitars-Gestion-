package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vue.vueJFrame;

public class controlerOption implements ActionListener {

	private vueJFrame parent;

	public controlerOption( vueJFrame parent ) {
		this.parent = parent;
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		String option = ( (JButton) e.getSource() ).getText();

		switch ( option ) {
		case "Artiste":
			afficher( true );
			break;
		case "Album":
			afficher( false );
			break;
		case "Quitter":
			close();
		}
	}

	public void close() {
		if ( JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog( this.parent, "Voulez-vous vraiment quitt\u00E9?",
				"Confirmation d\u00E9connexion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE ) ) {
			try {
				this.parent.getCs().getConnBD().closeConnexion();
			} catch ( ClassNotFoundException e1 ) {
				e1.printStackTrace();
			} catch ( SQLException e1 ) {
				e1.printStackTrace();
			}
			this.parent.dispose();
			System.exit( 0 );
		}
	}

	public void afficher( Boolean vue ) {
		vueJFrame frame = new vueJFrame( this.parent.getCs() );
		frame.vueCentral( vue );
		this.parent.dispose();
	}
}
