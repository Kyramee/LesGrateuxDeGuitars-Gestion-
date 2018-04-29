package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import modele.modeleJTableArtiste;
import vue.vueGestionArtiste;
import vue.vueJFrame;

public class controlerIndexArtiste implements ActionListener {

	private vueJFrame parent;
	private vueGestionArtiste vue;
	private modeleJTableArtiste modele;
	private controlerSysteme cs;
	
	public controlerIndexArtiste(vueJFrame parent, vueGestionArtiste vueArtiste, modeleJTableArtiste modele) {
		this.parent = parent;
		this.vue = vueArtiste;
		this.modele = modele;
		this.cs = parent.getCs();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String id = this.vue.getJText(2).getText();
		String nom = this.vue.getJText(0).getText();
		String membre = this.vue.getRadio(0).isSelected() ? "true" : "false";
		String url = this.vue.getJText(1).getText();
		this.vue.effacerErreur();

		switch (((JButton) e.getSource()).getText()) {
		case "Ajouter":
			ajouter(nom, membre, url);
			break;
		case "Modifier":
			modifer(id, nom, membre, url);
			break;
		case "Supprimer":
			supprime(id, nom);
			break;
		case "Rechercher":
			recherhcer(id, nom);
			break;
		case "Quitter":
			vueJFrame vf = new vueJFrame(this.cs);
			vf.vueOption();
			this.parent.dispose();
		}
	}

	private void recherhcer(String id, String nom) {
		if (checkNumber(id)) {
			this.cs.accessTabArtiste(id, nom, checkMembre());
			this.modele.setDonnees(cs.getTabArtiste());

			if (this.cs.getTabArtiste().isEmpty()) {
				JOptionPane.showMessageDialog(parent, "Aucun ne artiste concorde avec vos param\u00EAtres de recherche",
						"Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void supprime(String id, String nom) {
		if (checkId(id)) {
			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(parent,
					"Voulez-vous supprimer l'artiste avec l'id " + id + "?", "Confirmation supprimer",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {
				if (this.cs.hasAlbum(id).isEmpty()) {
					JOptionPane.showMessageDialog(parent,
							"L'artiste " + nom + " est assossi\u00E9 \u00E0 un ou plusieurs albums", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				} else {
					this.cs.supprimer("Artiste", Integer.parseInt(id));
					this.modele.setDonnees(cs.getTabArtiste());
					this.vue.effacerChamp();
				}
			}
		}
	}

	private void modifer(String id, String nom, String membre, String url) {
		if (checkId(id) && checkInfo(nom, url)) {
			this.cs.modifierArtiste(id, nom, membre, url);
			this.modele.setDonnees(cs.getTabArtiste());
			this.vue.effacerChamp();
		}
	}

	private void ajouter(String nom, String membre, String url) {
		if (checkInfo(nom, url)) {
			if (this.cs.containtArtiste(nom)) {
				JOptionPane.showMessageDialog(parent, "L'artiste " + nom + " est d\u00E9j\u00E0 pr\u00E9sent", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			} else {
				if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(parent,
						"Voulez-vous ajouter l'artiste " + nom + "?", "Confirmation ajout",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
					this.cs.ajouterArtiste(nom, membre, url);
					this.modele.setDonnees(cs.getTabArtiste());
					this.vue.effacerChamp();
				}
			}
		}
	}

	private boolean checkNumber(String id) {
		Boolean ok = true;

		if (id.matches("^[0-9]*$")) {
			this.vue.setErreur(0, "");
		} else {
			this.vue.setErreur(0, "Le id doit \u00EAtre un nombre");
			ok = false;
		}

		return ok;
	}

	private boolean checkId(String id) {
		Boolean ok = true;

		if (id.isEmpty()) {
			this.vue.setErreur(0, "Remplir le champ");
			ok = false;
		} else if (id.matches("^[0-9]+$")) {
			if (this.modele.containt(id) != null) {
				this.vue.setErreur(0, "");
			} else {
				this.vue.setErreur(0, "Le id n'est pas valide");
				ok = false;
			}
		} else {
			this.vue.setErreur(0, "Le id doit \u00EAtre un nombre");
			ok = false;
		}

		return ok;
	}

	private Boolean checkInfo(String nom, String url) {
		Boolean ok = true;

		if (nom.isEmpty()) {
			this.vue.setErreur(1, "Remplir le champ");
			ok = false;
		} else if (nom.length() > 30) {
			this.vue.setErreur(1, "Le nom doit avoir moins de 30 caract\u00E8re");
			ok = false;
		} else {
			this.vue.setErreur(1, "");
		}

		if (checkMembre() == 2) {
			this.vue.setErreur(2, "Remplir le champ");
			ok = false;
		} else {
			this.vue.setErreur(2, "");
		}

		if (url.isEmpty()) {
			this.vue.setErreur(3, "Remplir le champ");
			ok = false;
		} else if (url.length() > 255) {
			this.vue.setErreur(3, "L'url doit avoir moins de 255 caract\u00E8");
			ok = false;
		} else {
			this.vue.setErreur(3, "");
		}

		return ok;
	}

	private int checkMembre() {
		if (this.vue.getRadio(0).isSelected()) {
			return 0;
		} else if (this.vue.getRadio(1).isSelected()) {
			return 1;
		} else {
			return 2;
		}
	}
}
