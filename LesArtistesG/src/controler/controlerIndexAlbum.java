package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

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
		String id = this.vue.getJText(0).getText();
		String titre = this.vue.getJText(1).getText();
		String prix = this.vue.getJText(2).getText();
		String genre = this.vue.getJText(3).getText();
		String anneeSortie = this.vue.getJText(4).getText();
		String maisonDistribution = this.vue.getJText(5).getText();
		String imageUrl = this.vue.getJText(6).getText();
		String artisteId = this.vue.getJText(7).getText();

		switch (((JButton) e.getSource()).getText()) {
		case "Ajouter":
			ajouter(titre, prix, genre, anneeSortie, maisonDistribution, imageUrl, artisteId);
			break;
		case "Modifier":
			modifier(id, titre, prix, genre, anneeSortie, maisonDistribution, imageUrl, artisteId);
			break;
		case "Supprimer":
			supprimer(id);
			break;
		case "Rechercher":
			rechercher(id, titre, genre, anneeSortie, maisonDistribution, imageUrl, artisteId);
			break;
		case "Quitter":
			vueJFrame vf = new vueJFrame();
			vf.init();
			this.parent.dispose();
		}
	}

	private void rechercher(String id, String titre, String genre, String anneeSortie, String maisonDistribution,
			String imageUrl, String artisteId) {
		if (checkNumber()) {
			controlerSysteme cs = new controlerSysteme();
			cs.accessTabAlbum(id, titre, this.vue.getJText(2).getText(), genre, anneeSortie, maisonDistribution,
					imageUrl, artisteId);
			this.modele.setDonnees(cs.getTabAlbum());
		}
	}

	private void supprimer(String id) {
		if (checkId()) {
			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(parent,
					"Voulez-vous supprimer l'album avec l'id " + id + "?", "Confirmation supprimer",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE)) {

				controlerSysteme cs = new controlerSysteme();
				cs.supprimer("Album", Integer.parseInt(this.vue.getJText(0).getText()));
				this.modele.setDonnees(cs.getTabAlbum());
				this.vue.effacerChamp();
			}
		}
	}

	private void modifier(String id, String titre, String prix, String genre, String anneeSortie,
			String maisonDistribution, String imageUrl, String artisteId) {
		if (checkId() && checkInfo()) {
			controlerSysteme cs = new controlerSysteme();
			cs.modifierAlbum(id, titre, prix, genre, anneeSortie, maisonDistribution, imageUrl, artisteId);
			this.vue.effacerChamp();
		}
	}

	private void ajouter(String titre, String prix, String genre, String anneeSortie, String maisonDistribution,
			String imageUrl, String artisteId) {
		if (checkInfo()) {
			controlerSysteme cs = new controlerSysteme();
			if (cs.containtAlbum(titre)) {
				JOptionPane.showMessageDialog(parent, "L'album " + titre + " est d\u00E9j\u00E0 pr\u00E9sent", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			} else {
				cs.ajouterAlbum(titre, prix, genre, anneeSortie, maisonDistribution, imageUrl, artisteId);
				this.modele.setDonnees(cs.getTabAlbum());
				this.vue.effacerChamp();
			}

		}
	}

	private boolean checkNumber() {
		Boolean ok = true;

		if (this.vue.getJText(2).getText().matches("^[0-9]*$")) {
			this.vue.setErreur(0, "");
		} else {
			this.vue.setErreur(0, "Le id doit \u00EAtre un nombre");
			ok = false;
		}

		return ok;
	}

	private boolean checkId() {
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

		ok = checkTitre(ok, index++);

		ok = checkPrix(ok, index++);

		ok = checkGenre(ok, index++);

		ok = checkDate(ok, index++);

		ok = checkMaison(ok, index++);

		ok = checkUrl(ok, index++);

		ok = checkArtisteId(ok, index);

		return ok;
	}

	private Boolean checkArtisteId(Boolean ok, int index) {
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

	private Boolean checkUrl(Boolean ok, int index) {
		if (this.vue.getJText(index).getText().isEmpty()) {
			this.vue.setErreur(index, "Remplir le champ");
			ok = false;
		} else if (this.vue.getJText(index).getText().length() > 30) {
			this.vue.setErreur(index, "L'url doit avoir moins de 255 caract\\u00E8");
			ok = false;
		} else {
			this.vue.setErreur(index, "");
		}
		return ok;
	}

	private Boolean checkMaison(Boolean ok, int index) {
		if (this.vue.getJText(index).getText().isEmpty()) {
			this.vue.setErreur(index, "Remplir le champ");
			ok = false;
		} else if (this.vue.getJText(index).getText().length() > 30) {
			this.vue.setErreur(index, "La maison de distribution doit avoir moins de 30 caract\u00E8re");
			ok = false;
		} else {
			this.vue.setErreur(index, "");
		}
		return ok;
	}

	private Boolean checkDate(Boolean ok, int index) {
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
		return ok;
	}

	private Boolean checkGenre(Boolean ok, int index) {
		if (this.vue.getJText(index).getText().isEmpty()) {
			this.vue.setErreur(index, "Remplir le champ");
			ok = false;
		} else if (this.vue.getJText(index).getText().length() > 30) {
			this.vue.setErreur(index, "Le genre doit avoir moins de 30 caract\u00E8re");
			ok = false;
		} else {
			this.vue.setErreur(index, "");
		}
		return ok;
	}

	private Boolean checkPrix(Boolean ok, int index) {
		if (this.vue.getJText(index).getText().isEmpty()) {
			this.vue.setErreur(index, "Remplir le champ");
			ok = false;
		} else if (this.vue.getJText(index).getText().matches("^\\d+(\\.\\d{1,2})$")) {
			this.vue.setErreur(index, "Le champ doit \u00EAtre du format 0.00");
			ok = false;
		} else {
			this.vue.setErreur(index, "");
		}
		return ok;
	}

	private Boolean checkTitre(Boolean ok, int index) {
		if (this.vue.getJText(index).getText().isEmpty()) {
			this.vue.setErreur(index, "Remplir le champ");
			ok = false;
		} else if (this.vue.getJText(index).getText().length() > 30) {
			this.vue.setErreur(index, "Le titre doit avoir moins de 30 caract\u00E8re");
			ok = false;
		} else {
			this.vue.setErreur(index, "");
		}
		return ok;
	}
}