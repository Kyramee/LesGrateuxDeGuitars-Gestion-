package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controler.controlerSysteme;
import controler.fermetureVueGestion;
import controler.fermetureVueOption;

public class vueJFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private Container contenu;
	private controlerSysteme cs;

	public vueJFrame(controlerSysteme cs) {
		this.cs = cs;
		this.setResizable(false);
		this.contenu = getContentPane();
		ImageIcon iconeFenetre = new ImageIcon(getClass().getResource("../images/membre.png"));
		this.setIconImage(iconeFenetre.getImage());
	}
	
	public void vueConnexion() {
		setTitle("LesArtistesG - Connexion");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground( Color.ORANGE );
		setBounds( 100, 100, 630, 350 );
		this.setLocationRelativeTo(null);
		this.contenu.add(new vueConnexion(this));
		this.setVisible(true);
	}

	public void vueOption() {
		this.contenu.add(new vueOption(this), BorderLayout.CENTER);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new fermetureVueOption(this));
		this.setTitle("LesArtistesG - Options");
		this.setSize(400, 220);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void vueCentral(Boolean vue) {
		setTitle("LesArtistesG - Gestion des " + ((vue) ? "artistes" : "albums"));
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new fermetureVueGestion(this));
		vueCentral jp = new vueCentral(vue);
		vueIndex vi = new vueIndex();
		
		this.contenu.add(jp, BorderLayout.CENTER);
		
		if (vue) {
			this.setSize(750, 450);
			vi.setListenerArtiste(this, jp.getVueArtiste(), jp.getModeleArtiste());
			this.contenu.add(vi, BorderLayout.WEST);
		} else {
			this.setSize(970, 450);
			vi.setListenerAlbum(this, jp.getVueAlbum(), jp.getModeleAlbum());
			this.contenu.add(vi, BorderLayout.WEST);
		}
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public controlerSysteme getCs() {
		return this.cs;
	}
}
