package vue;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class vueCentral extends JPanel{
	private static final long serialVersionUID = 1L;
	private final String name = "LesArtistesG - ";
	
	public vueCentral() {
		super(new BorderLayout());
	}
	
	public void setNorthPanel() {
		JPanel jp = new JPanel(new GridLayout(1,1));
		Font font = new Font("SansSerif", Font.BOLD, 20);
		JLabel label = new JLabel("Choissez l'une des options dans l'index de gauche");
		label.setFont(font);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		jp.add(label);
		this.add(jp, BorderLayout.CENTER);
	}
	
	public void setNorthPanel(int vue, vueJFrame vf) {
		add(new vueJTable(), BorderLayout.CENTER);
		
		switch (vue) {
		case 0:
			vf.setTitle(this.name + "Ajouter un artiste");
			this.add(new vueAjouterArtiste(vf), BorderLayout.NORTH);
			break;
		case 1:
			vf.setTitle(this.name + "Ajouter un album");
			break;
		case 2:
			vf.setTitle(this.name + "Modifier un artiste");
			break;
		case 3:
			vf.setTitle(this.name + "Modifier un album");
			break;
		case 4:
			vf.setTitle(this.name + "Supprimer un artiste");
			break;
		case 5:
			vf.setTitle(this.name + "Supprimer un album");
			break;
		case 6:
			vf.setTitle(this.name + "Rechercher un artiste");
			break;
		case 7:
			vf.setTitle(this.name + "Rechercher un album");
		}
	}
}
