package vue;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class vueCentral extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public vueCentral() {
		super(new BorderLayout());
		add(new vueJTable(), BorderLayout.CENTER);
	}
	
	public void setNorthPanel() {
		JPanel jp = new JPanel();
		Font font = new Font("SansSerif", Font.BOLD, 20);
		JLabel label = new JLabel("Choissez l'une des options dans l'index de gauche");
		label.setFont(font);
		label.setHorizontalAlignment(JLabel.CENTER);
		jp.add(label);
		this.add(jp, BorderLayout.NORTH);
	}
	
	public void setNorthPanel(int vue) {
		switch (vue) {
		case 0:
			this.add(new vueAjouterArtiste(), BorderLayout.NORTH);
			break;
		case 1:

			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;
		case 5:

			break;
		case 6:

			break;
		case 7:
			
		}
	}
	
	public JPanel centerPanel() {
		JPanel jp = new JPanel();
		JTable table = new JTable();	
		JScrollPane scroListe = new JScrollPane(table);
		
		jp.add(scroListe);
		return new JPanel();
	}
}
