package controler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import vue.vueGestionAlbum;

public class controlerJTableAlbum extends MouseAdapter {

	private vueGestionAlbum vue;
	private JTable table;

	public controlerJTableAlbum(vueGestionAlbum vue, JTable table) {
		this.vue = vue;
		this.table = table;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.vue.effacerErreur();
		int numLigne = table.getSelectedRow();

		controlerSysteme cs = new controlerSysteme();
		cs.accessTabAlbum(String.valueOf(this.table.getValueAt(numLigne, 0)), "", "", "", "", "", "", "");

		this.vue.getJText(0).setText(cs.getTabAlbum().get(0).getId());
		this.vue.getJText(1).setText(cs.getTabAlbum().get(0).getTitre());
		this.vue.getJText(2).setText(cs.getTabAlbum().get(0).getPrix());
		this.vue.getJText(3).setText(cs.getTabAlbum().get(0).getGenre());
		this.vue.getJText(4).setText(cs.getTabAlbum().get(0).getDate());
		this.vue.getJText(5).setText(cs.getTabAlbum().get(0).getMaison());
		this.vue.getJText(6).setText(cs.getTabAlbum().get(0).getImageUrl());

		cs.accessTabArtiste(cs.getTabAlbum().get(0).getIdArtiste(), "", 2);

		for (int i = 0; i < this.vue.getArtiste().getItemCount(); i++) {
			if (this.vue.getArtiste().getItemAt(i).equals(cs.getTabArtiste().get(0).getNom())) {
				this.vue.getArtiste().setSelectedIndex(i);
				break;
			}
		}

		this.vue.setImage(cs.getTabAlbum().get(0).getImageUrl());
	}
}
