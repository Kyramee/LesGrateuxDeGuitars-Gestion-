import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;

public class jtablePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTable table;

	public jtablePanel() {
		initTable();
	}

	public void initTable() {
		String[] nomColonne = { "Id", "Nom", "Membre" };
		this.table = new JTable(donneesInit(), nomColonne);
	}

	public String[][] donneesInit() {
		gestionArtistes ga = new gestionArtistes();
		ArrayList<Artiste> result = new ArrayList<>();
		String[][] donnees = new String[result.size()][3];

		int index = 0;

		for (Artiste artiste : result) {
			donnees[index++] = artiste.getTableInfo();
		}

		return donnees;
	}
}