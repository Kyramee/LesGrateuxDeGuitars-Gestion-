package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controler.controlerSysteme;
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
		setCenterPanel();
		setNorthPanel();
	}
	
	public void setCenterPanel() {
		JTable table;
		JPanel jp = new JPanel();
		controlerSysteme ga = new controlerSysteme();
		
		if(vue) {
			modeleArtiste = new modeleJTableArtiste(ga.getTabArtiste());
			table = new JTable(modeleArtiste);
		} else {
			modeleAlbum = new modeleJTableAlbum(ga.getTabAlbum());
			table = new JTable(modeleAlbum);
		}
		
		
		JScrollPane scroListe = new JScrollPane(table);
		scroListe.setPreferredSize(new Dimension(500, 250));
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
