package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controler.controlerJTableArtiste;
import controler.controlerSysteme;
import modele.modeleColonneId;
import modele.modeleColonneMembre;
import modele.modeleJTableAlbum;
import modele.modeleJTableArtiste;

public class vueCentral extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private modeleJTableArtiste modeleArtiste;
	private modeleJTableAlbum modeleAlbum;
	private vueGestionArtiste vueArtiste;
	private vueGestionAlbum vueAlbum;
	private boolean vue;
	
	public vueCentral(Boolean vue) {
		super(new BorderLayout());
		this.vue = vue;
		setNorthPanel();
		setCenterPanel();
	}
	
	public void setCenterPanel() {
		JTable table = new JTable();
		JScrollPane scroListe = new JScrollPane(table);
		JPanel jp = new JPanel();
		controlerSysteme ga = new controlerSysteme();
		
		if(vue) {
			modeleArtiste = new modeleJTableArtiste(ga.getTabArtiste(), this.vueArtiste);
			table.setModel(modeleArtiste);
			table.addMouseListener(new controlerJTableArtiste(this.vueArtiste, table));
			table.getColumnModel().getColumn(0).setCellRenderer(new modeleColonneId());
			table.getColumnModel().getColumn(2).setCellRenderer(new modeleColonneMembre());
			scroListe.setPreferredSize(new Dimension(500, 180));
		} else {
			modeleAlbum = new modeleJTableAlbum(ga.getTabAlbum());
			table.setModel(modeleAlbum);
			table.getColumnModel().getColumn(0).setCellRenderer(new modeleColonneId());
			scroListe.setPreferredSize(new Dimension(600, 200));
		}
		
		
		table.getTableHeader().setReorderingAllowed(false);
		
		jp.add(scroListe);
		add(jp, BorderLayout.CENTER);
	}
	
	public void setNorthPanel() {
		if (vue) {
			this.vueArtiste = new vueGestionArtiste();
			add(this.vueArtiste, BorderLayout.NORTH);
		} else {
			this.vueAlbum = new vueGestionAlbum();
			add(this.vueAlbum, BorderLayout.NORTH);
		}
	}
	
	public modeleJTableArtiste getModeleArtiste() {
		return this.modeleArtiste;
	}
	
	public modeleJTableAlbum getModeleAlbum() {
		return this.modeleAlbum;
	}
	
	public vueGestionArtiste getVueArtiste() {
		return this.vueArtiste;
	}
	
	public vueGestionAlbum getVueAlbum() {
		return this.vueAlbum;
	}
}
