package vue;


import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controler.controlerArtisteAlbum;
import modele.modeleJTable;

public class vueJTable extends JPanel {
	private static final long serialVersionUID = 1L;

	private JScrollPane scroListe;

	public vueJTable() {
		initTable();
		add(this.scroListe);
	}

	public void initTable() {
		controlerArtisteAlbum ga = new controlerArtisteAlbum();
		JTable table = new JTable(new modeleJTable(ga.getTabArtiste()));
		this.scroListe = new JScrollPane(table);
		this.scroListe.setPreferredSize(new Dimension(500, 250));
	}
}