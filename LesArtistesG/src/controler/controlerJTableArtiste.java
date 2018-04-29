package controler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import vue.vueGestionArtiste;

public class controlerJTableArtiste extends MouseAdapter {

	private vueGestionArtiste vue;
	private JTable table;

	public controlerJTableArtiste(vueGestionArtiste vue, JTable table) {
		this.vue = vue;
		this.table = table;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.vue.effacerErreur();
		int numLigne = table.getSelectedRow();
		
		controlerSysteme cs = new controlerSysteme();

		cs.accessTabArtiste(String.valueOf(this.table.getValueAt(numLigne, 0)), "", 2);

		this.vue.getJText(2).setText(cs.getTabArtiste().get(0).getId());
		this.vue.getJText(0).setText(cs.getTabArtiste().get(0).getNom());
		this.vue.getJText(1).setText(cs.getTabArtiste().get(0).getUrl());

		if (cs.getTabArtiste().get(0).getMembre()) {
			this.vue.getRadio(0).setSelected(true);
		} else {
			this.vue.getRadio(1).setSelected(true);
		}

		this.vue.setPhoto(cs.getTabArtiste().get(0).getUrl());

		this.vue.setDonnesAlbum(cs.hasAlbum(cs.getTabArtiste().get(0).getId()));
	}
}
