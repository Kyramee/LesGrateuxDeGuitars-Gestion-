package controler;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modele.Album;
import modele.Artiste;

public class controlerArtisteAlbum {

	private ArrayList<Artiste> tabArtiste;
	private ArrayList<Album> tabAlbum;
	private controlerConnexion cc;
	private int lastInt = 0;

	public controlerArtisteAlbum() {
		cc = new controlerConnexion();
		accessTabArtiste("", "", false);
		accessTabAlbum();
	}

	public ArrayList<Artiste> getTabArtiste() {
		return this.tabArtiste;
	}

	public ArrayList<Album> getTabAlbum() {
		return this.tabAlbum;
	}

	public void accessTabArtiste(String id, String nom, Boolean membre) {
		this.tabArtiste = new ArrayList<>();
		String requete = "SELECT * FROM Artiste WHERE nom LIKE '%" + nom + "%'";

		if (!id.isEmpty()) {
			requete += " id = " + id;
		}

		if (membre) {
			requete += " membre = " + membre;
		}

		requete += " ORDER BY nom";

		try {
			ResultSet result = cc.executerRequete(requete);

			while (result.next()) {
				this.tabArtiste.add(new Artiste(result.getInt("id"), result.getString("nom"),
						result.getBoolean("membre"), result.getString("photo_url")));
				obtenirLastInt(result.getInt("id"));
			}

			cc.closeConnexion();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void accessTabAlbum() {
		this.tabAlbum = new ArrayList<>();
		String requete = "SELECT * FROM Album ORDER BY titre";

		try {
			ResultSet result = cc.executerRequete(requete);

			while (result.next()) {
				this.tabAlbum.add(new Album(result.getInt("id"), result.getString("titre"), result.getDouble("prix"),
						result.getInt("genre_id"), result.getString("annee_sortie"),
						result.getString("maison_distribution"), result.getString("image_url"),
						result.getInt("artiste_id")));
			}
			cc.closeConnexion();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void ajouterArtiste(String nom, String membre, String photoUrl) {
		String requete = "INSERT INTO `Artiste`(`id`, `nom`, `membre`, `photo_url`) VALUES (null, '" + nom + "', "
				+ membre + ", '" + photoUrl + "')";

		try {
			cc.update(requete);
			cc.closeConnexion();
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		accessTabArtiste("", "", false);
	}

	public void ajouterAlbum(String titre, double prix, int genre, String anneeSortie, String maisonDistribution,
			String imageUrl, int idArtiste) {
		String requete = "INSERT INTO `Album`(`id`, `titre`, `prix`, `genre_id`, `annee_sortie`, `maison_distribution`, `image_url`, `artiste_id`) VALUES (null, '"
				+ titre + "', " + prix + ", " + genre + ", '" + anneeSortie + "', '" + maisonDistribution + "', '"
				+ imageUrl + "', " + idArtiste + ")";

		try {
			cc.update(requete);
			cc.closeConnexion();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		accessTabAlbum();
	}

	public void modifierAlbum(int id, String titre, double prix, int genre, String anneeSortie,
			String maisonDistribution, String imageUrl, int idArtiste) {
		String requete = "UPDATE Album SET titre = '" + titre + "', prix = " + prix + ", genre_id = " + genre
				+ ", annee_sortie = '" + anneeSortie + "', " + "maison_distribution = '" + maisonDistribution
				+ "', image_url = '" + imageUrl + "', artiste_id = " + idArtiste + " WHERE id = " + id;

		try {
			cc.update(requete);
			cc.closeConnexion();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		accessTabAlbum();
	}

	public void modifierArtiste(int id, String nom, String membre, String photoUrl) {
		String requete = "UPDATE Artiste SET nom = '" + nom + "', membre = " + membre + ", photo_url = '" + photoUrl
				+ "' WHERE id = " + id;

		try {

			cc.update(requete);
			cc.closeConnexion();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		accessTabArtiste("", "", false);
	}

	public void supprimer(String table, int id) {
		String requete = "DELETE FROM " + table + " WHERE id = " + id;

		try {
			cc.update(requete);
			cc.closeConnexion();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		accessTabArtiste("", "", false);
		accessTabAlbum();
	}

	private void obtenirLastInt(int nombre) {
		lastInt = (nombre > lastInt) ? nombre : lastInt;
	}

	public boolean checkUser(String nom, String password) {
		String requete = "SELECT * FROM Utilisateur WHERE nom = '" + nom + "'";
		boolean ok = false;

		try {
			ResultSet result = cc.executerRequete(requete);

			if (result.next()) {
				if (result.getString("password").equals(password)) {
					ok = true;
				}
			}
			cc.closeConnexion();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		return ok;
	}
}
