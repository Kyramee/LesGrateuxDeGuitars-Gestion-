package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import modele.modeleJTableAlbum;
import vue.vueGestionAlbum;
import vue.vueJFrame;

public class controlerIndexAlbum implements ActionListener {

	private vueJFrame parent;
	private vueGestionAlbum vue;
	private modeleJTableAlbum modele;

	public controlerIndexAlbum(vueJFrame parent, vueGestionAlbum vue, modeleJTableAlbum modele) {
		this.parent = parent;
		this.vue = vue;
		this.modele = modele;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String titre = this.vue.getJText(1).getText();
		double prix = (this.vue.getJText(2).getText().isEmpty()) ? 0
				: Double.parseDouble(this.vue.getJText(2).getText());
		String genre = this.vue.getJText(3).getText();
		String anneeSortie = this.vue.getJText(4).getText();
		String maisonDistribution = this.vue.getJText(5).getText();
		String imageUrl = this.vue.getJText(6).getText();

		switch (((JButton) e.getSource()).getText()) {
		case "Ajouter":
			if (checkInfo()) {
				controlerSysteme cs = new controlerSysteme();
				cs.ajouterAlbum(titre, prix, genre, anneeSortie, maisonDistribution, imageUrl,
						Integer.parseInt(this.vue.getJText(0).getText()));
				this.modele.setDonnees(cs.getTabAlbum());
				this.vue.effacerChamp();
			}
			break;
		case "Modifier":
			if (checkId() && checkInfo()) {
				controlerSysteme cs = new controlerSysteme();
				cs.modifierAlbum(Integer.parseInt(this.vue.getJText(0).getText()), titre, prix, genre, anneeSortie,
						maisonDistribution, imageUrl, Integer.parseInt(this.vue.getJText(0).getText()));
				this.modele.setDonnees(cs.getTabAlbum());
				this.vue.effacerChamp();
			}
			break;
		case "Supprimer":
			if (checkId()) {
				controlerSysteme cs = new controlerSysteme();
				cs.supprimer("Album", Integer.parseInt(this.vue.getJText(0).getText()));
				this.modele.setDonnees(cs.getTabAlbum());
				this.vue.effacerChamp();
			}
			break;
		case "Rechercher":
			if (checkNumber()) {
				controlerSysteme cs = new controlerSysteme();
				cs.accessTabAlbum(this.vue.getJText(0).getText(), titre, this.vue.getJText(2).getText(), genre,
						anneeSortie, maisonDistribution, imageUrl, this.vue.getJText(0).getText());
				this.modele.setDonnees(cs.getTabAlbum());
			}
			break;
		case "Quitter":
			vueJFrame vf = new vueJFrame();
			vf.init();
			this.parent.dispose();
		}
	}

	public boolean checkNumber() {
		Boolean ok = true;

		if (this.vue.getJText(2).getText().matches("^[0-9]*$")) {
			this.vue.setErreur(0, "");
		} else {
			this.vue.setErreur(0, "Le id doit \u00EAtre un nombre");
			ok = false;
		}

		return ok;
	}

	public boolean checkId() {
		Boolean ok = true;

		if (this.vue.getJText(0).getText().isEmpty()) {
			this.vue.setErreur(0, "Remplir le champ");
			ok = false;
		} else if (this.vue.getJText(0).getText().matches("^[0-9]+$")) {
			if (this.modele.containt(Integer.parseInt(this.vue.getJText(0).getText())) != null) {
				this.vue.setErreur(0, "");
			} else {
				this.vue.setErreur(0, "Le id doit n'est pas valide");
				ok = false;
			}
		} else {
			this.vue.setErreur(0, "Le id doit \u00EAtre un nombre");
			ok = false;
		}

		return ok;
	}

	private Boolean checkInfo() {
		Boolean ok = true;
		int index = 1;

		if (this.vue.getJText(index).getText().isEmpty()) {
			this.vue.setErreur(index, "Remplir le champ");
			ok = false;
		} else if (this.vue.getJText(index).getText().length() > 30) {
			this.vue.setErreur(index, "Le titre doit avoir moins de 30 caract\u00E8re");
			ok = false;
		} else {
			this.vue.setErreur(index, "");
		}

		index++;

		if (this.vue.getJText(index).getText().isEmpty()) {
			this.vue.setErreur(index, "Remplir le champ");
			ok = false;
		} else if (this.vue.getJText(index).getText().matches("^\\d+(\\.\\d{1,2})$")) {
			this.vue.setErreur(index, "Le champ doit \u00EAtre du format 0.00");
			ok = false;
		} else {
			this.vue.setErreur(index, "");
		}

		index++;

		if (this.vue.getJText(index).getText().isEmpty()) {
			this.vue.setErreur(index, "Remplir le champ");
			ok = false;
		} else if (this.vue.getJText(index).getText().length() > 30) {
			this.vue.setErreur(index, "Le genre doit avoir moins de 30 caract\u00E8re");
			ok = false;
		} else {
			this.vue.setErreur(index, "");
		}

		index++;

		if (this.vue.getJText(index).getText().isEmpty()) {
			this.vue.setErreur(index, "Remplir le champ");
			ok = false;
		} else if (this.vue.getJText(index).getText()
				.matches("^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$")) {
			this.vue.setErreur(index, "La date doit \u00EAtre valide du format 9999-99-99");
			ok = false;
		} else {
			this.vue.setErreur(index, "");
		}

		index++;

		if (this.vue.getJText(index).getText().isEmpty()) {
			this.vue.setErreur(index, "Remplir le champ");
			ok = false;
		} else if (this.vue.getJText(index).getText().length() > 30) {
			this.vue.setErreur(index, "La maison de distribution doit avoir moins de 30 caract\u00E8re");
			ok = false;
		} else {
			this.vue.setErreur(index, "");
		}

		index++;

		if (this.vue.getJText(index).getText().isEmpty()) {
			this.vue.setErreur(index, "Remplir le champ");
			ok = false;
		} else if (this.vue.getJText(index).getText().length() > 30) {
			this.vue.setErreur(index, "L'url doit avoir moins de 255 caract\\u00E8");
			ok = false;
		} else {
			this.vue.setErreur(index, "");
		}

		index++;

		if (this.vue.getJText(index).getText().isEmpty()) {
			this.vue.setErreur(index, "Remplir le champ");
			ok = false;
		} else if (this.vue.getJText(index).getText().matches("^[0-9]+$")) {
			controlerSysteme cs = new controlerSysteme();
			cs.accessTabArtiste(this.vue.getJText(index).getText(), "", 2);
			if (cs.getTabArtiste().isEmpty()) {
				this.vue.setErreur(index, "Le id n'est pas valide");
				ok = false;
			} else {
				this.vue.setErreur(index, "");
			}
		} else {
			this.vue.setErreur(index, "Le id doit \u00EAtre un nombre");
			ok = false;
		}

		return ok;
	}
}
