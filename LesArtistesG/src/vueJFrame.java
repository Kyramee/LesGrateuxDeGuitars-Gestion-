import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class vueJFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public vueJFrame() {
		super("LesArtistesG - Connexion");
		this.setSize(750, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		//ImageIcon iconeFenetre = new ImageIcon(getClass().getResource("images/grateux.png"));
		//this.setIconImage(iconeFenetre.getImage());
		Container contenu = getContentPane();
		
		contenu.add(new vueGeneral(this), BorderLayout.WEST);
		contenu.add(new vueAjouterArtiste(), BorderLayout.CENTER);
	}
}
