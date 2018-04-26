package vue;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class vueJFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private Container contenu;

	public vueJFrame() {
		this.setResizable(true);
		this.contenu = getContentPane();
		// ImageIcon iconeFenetre = new
		// ImageIcon(getClass().getResource("images/grateux.png"));
		// this.setIconImage(iconeFenetre.getImage());
	}

	public void init() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.contenu.add(new vueOption(this), BorderLayout.CENTER);
		this.setTitle("LesArtistesG - Options");
		this.setSize(400, 220);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void vueCentral(Boolean vue) {
		this.setSize(750, 450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("LesArtistesG - Gestion des" + ((vue) ? "artistes" : "albumms"));
		
		vueCentral jp = new vueCentral(vue);
		vueIndex vi = new vueIndex();
		
		this.contenu.add(jp, BorderLayout.CENTER);
		
		if (vue) {
			vi.setListenerArtiste(this, jp.getVueArtiste(), jp.getModeleArtiste());
			this.contenu.add(vi, BorderLayout.WEST);
		} else {
			
		}
		
		this.setVisible(true);
	}
}
