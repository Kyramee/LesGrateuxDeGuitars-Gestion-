package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.vueAjouterArtiste;
import vue.vueJFrame;

public class controlerAjouterArtiste implements ActionListener {

	private vueAjouterArtiste va;

	public controlerAjouterArtiste(vueAjouterArtiste va) {
		this.va = va;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (check()) {
			controlerArtisteAlbum caa = new controlerArtisteAlbum();
			caa.ajouterArtiste(va.getJText(0).getText(), (va.getRadio(0).isSelected()) ? "true" : "false",
					va.getJText(1).getText());
			vueJFrame vj = new vueJFrame();
			vj.vueArtisteAlbum(0);
			va.close();
		}

	}

	private Boolean check() {
		Boolean ok = true;

		if (va.getJText(0).getText().isEmpty()) {
			va.setErreur(0, "Remplir le champ");
			ok = false;
		} else if (va.getJText(0).getText().length() > 30) {
			va.setErreur(0, "Le nom doit avoir moins de 30 caract\u00E8re");
			ok = false;
		} else {
			va.setErreur(0, "");
		}

		if (!(va.getRadio(0).isSelected() || va.getRadio(1).isSelected())) {
			va.setErreur(1, "Remplir le champ");
			ok = false;
		} else {
			va.setErreur(1, "");
		}

		if (va.getJText(1).getText().isEmpty()) {
			va.setErreur(2, "Remplir le champ");
			ok = false;
		} else if (va.getJText(1).getText().length() > 255) {
			va.setErreur(2, "Le nom doit avoir moins de 255 caract\u00E8");
			ok = false;
		} else {
			va.setErreur(2, "");
		}

		return ok;
	}
}
