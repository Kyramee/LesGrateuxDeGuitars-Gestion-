package controler;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import modele.modeleJTableArtiste;
import vue.vueGestionArtiste;
import vue.vueJFrame;

public class controlerIndexArtiste implements ActionListener {

	private vueJFrame parent;
	private vueGestionArtiste vue;
	private modeleJTableArtiste modele;

	public controlerIndexArtiste(vueJFrame parent, vueGestionArtiste vueArtiste, modeleJTableArtiste modele) {
		this.parent = parent;
		this.vue = vueArtiste;
		this.modele = modele;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String nom = this.vue.getJText(0).getText();
		String membre = this.vue.getRadio(0).isSelected() ? "true" : "false";
		String url = this.vue.getJText(1).getText();
		this.vue.effacerErreur();

		switch (((JButton) e.getSource()).getText()) {
		case "Ajouter":
			if (checkInfo()) {
				controlerSysteme cs = new controlerSysteme();
				if (cs.containtArtiste(nom)) {
					JOptionPane.showMessageDialog(parent, "L'artiste " + nom + " est d\u00E9j\u00E0 pr\u00E9sent",
							"Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					if (JOptionPane.YES_OPTION != JOptionPane.showConfirmDialog(parent,
						"Voulez-vous ajouter l'artiste " + nom + "?", "Confirmation ajout",
						JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION)) {
						cs.ajouterArtiste(nom, membre, url);
						this.modele.setDonnees(cs.getTabArtiste());
						this.vue.effacerChamp();
					}
				}
			}
			break;
		case "Modifier":
			if (checkId() && checkInfo()) {
				controlerSysteme cs = new controlerSysteme();
				cs.modifierArtiste(Integer.parseInt(this.vue.getJText(2).getText()), nom, membre, url);
				this.modele.setDonnees(cs.getTabArtiste());
				this.vue.effacerChamp();
			}
			break;
		case "Supprimer":
			if (checkId()) {
				if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(parent,
						"Voulez-vous supprimer l'artiste?", "Confirmation ajout",
						JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION)) {

					controlerSysteme cs = new controlerSysteme();
					
					
					cs.supprimer("Artiste", Integer.parseInt(this.vue.getJText(2).getText()));
					this.modele.setDonnees(cs.getTabArtiste());
					this.vue.effacerChamp();
				}
			}
			break;
		case "Rechercher":
			if (checkNumber()) {
				controlerSysteme cs = new controlerSysteme();
				cs.accessTabArtiste(this.vue.getJText(2).getText(), nom, checkMembre());
				this.modele.setDonnees(cs.getTabArtiste());
				
				if (cs.getTabArtiste().isEmpty()) {
					JOptionPane.showMessageDialog(parent, "Aucun ne artiste concorde avec vos param\u00EAtres de recherche",
							"Erreur", JOptionPane.ERROR_MESSAGE);
				}
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

		if (this.vue.getJText(2).getText().isEmpty()) {
			this.vue.setErreur(0, "Remplir le champ");
			ok = false;
		} else if (this.vue.getJText(2).getText().matches("^[0-9]+$")) {
			if (this.modele.containt(Integer.parseInt(this.vue.getJText(2).getText())) != null) {
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

	private Boolean checkInfo() {
		Boolean ok = true;

		if (this.vue.getJText(0).getText().isEmpty()) {
			this.vue.setErreur(1, "Remplir le champ");
			ok = false;
		} else if (this.vue.getJText(0).getText().length() > 30) {
			this.vue.setErreur(1, "Le nom doit avoir moins de 30 caract\u00E8re");
			ok = false;
		} else {
			this.vue.setErreur(1, "");
		}

		if (!(this.vue.getRadio(0).isSelected() || this.vue.getRadio(1).isSelected())) {
			this.vue.setErreur(2, "Remplir le champ");
			ok = false;
		} else {
			this.vue.setErreur(2, "");
		}

		if (this.vue.getJText(1).getText().isEmpty()) {
			this.vue.setErreur(3, "Remplir le champ");
			ok = false;
		} else if (this.vue.getJText(1).getText().length() > 255) {
			this.vue.setErreur(3, "L'url doit avoir moins de 255 caract\u00E8");
			ok = false;
		} else {
			this.vue.setErreur(3, "");
		}

		return ok;
	}

	public int checkMembre() {
		if (this.vue.getRadio(0).isSelected()) {
			return 0;
		} else if (this.vue.getRadio(1).isSelected()) {
			return 1;
		} else {
			return 2;
		}
	}
}
