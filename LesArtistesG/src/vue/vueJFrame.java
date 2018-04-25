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

	public void vueArtisteAlbum(Boolean vue) {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		vueCentral vc = new vueCentral();
		this.setTitle("LesArtistesG - Index " + ((vue)? "artiste" : "album"));
		vc.setNorthPanel();
		this.contenu.add(vc, BorderLayout.CENTER);
		start(750, 450, vue);
	}

	public void vueArtisteAlbum(int vue) {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		vueCentral vc = new vueCentral();
		vc.setNorthPanel(vue, this);
		this.contenu.add(vc, BorderLayout.CENTER);
		start(750, 450, vue % 2 == 0);
	}

	private void start(int width, int height, boolean vue) {
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.contenu.add(new vueIndex(this, vue), BorderLayout.WEST);
		this.setVisible(true);
	}
}
