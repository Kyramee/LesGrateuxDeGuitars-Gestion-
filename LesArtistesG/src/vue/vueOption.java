package vue;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controler.controlerOption;

public class vueOption extends JPanel {
	private static final long serialVersionUID = 1L;

	private JButton[] tabBouton = new JButton[3];
	private final String[] tabNom = { "Artiste", "Album", "Quitter" };

	public vueOption( vueJFrame parent ) {
		super( new BorderLayout() );
		parent.setSize( 400, 220 );
		controlerOption co = new controlerOption( parent );

		this.add( centerPanel( co ), BorderLayout.CENTER );
		this.add( northPanel(), BorderLayout.NORTH );
	}

	private JPanel northPanel() {
		JPanel northPanel = new JPanel( new GridLayout( 2, 1 ) );
		Font font = new Font( "SansSerif", Font.BOLD, 20 );
		String[] text = { "Vous pouvez choisir se que vous", "voulez g\u00E9rer ou quitter" };

		for ( int i = 0; i < 2; i++ ) {
			JLabel label = new JLabel( text[i] );
			label.setFont( font );
			label.setHorizontalAlignment( JLabel.CENTER );
			northPanel.add( label, BorderLayout.NORTH );
		}
		return northPanel;
	}

	private JPanel centerPanel( controlerOption co ) {
		JPanel centerPanel = new JPanel();
		Dimension dBouton = new Dimension( 150, 50 );

		for ( int i = 0; i < this.tabBouton.length; i++ ) {
			this.tabBouton[i] = new JButton( this.tabNom[i] );
			this.tabBouton[i].setPreferredSize( dBouton );
			this.tabBouton[i].addActionListener( co );
			centerPanel.add( this.tabBouton[i], BorderLayout.CENTER );
			this.tabBouton[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			this.tabBouton[i].setToolTipText("<html><img src=\"" + getClass().getResource("../images/" + this.tabNom[i] + ".jpeg")
			        + "\">");
		}
		return centerPanel;
	}
}
