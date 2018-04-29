package controler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modele.Album;
import modele.Artiste;

public class controlerSysteme {

	private ArrayList<Artiste> tabArtiste;
	private ArrayList<Album> tabAlbum;
	private controlerConnexionBD cc;
	private int lastInt = 0;
	private boolean database;

	public controlerSysteme() {
		this.database = true;

		try {
			cc = new controlerConnexionBD();
		} catch (ClassNotFoundException | SQLException e) {
			this.database = false;
		}

		if (this.database) {
			accessTabArtiste();
			accessTabAlbum();
		}
	}
	
	public controlerConnexionBD getConnBD() {
		return this.cc;
	}
	public boolean hasDataBase() {
		return this.database;
	}

	public ArrayList<Artiste> getTabArtiste() {
		return this.tabArtiste;
	}

	public ArrayList<Album> getTabAlbum() {
		return this.tabAlbum;
	}

	private void accessTabArtiste() {
		accessTabArtiste("SELECT * FROM Artiste ORDER BY nom");
	}

	public void accessTabArtiste(String id, String nom, int membre) {
		String requete = "SELECT * FROM Artiste WHERE nom LIKE '%" + nom + "%'";

		if (!id.isEmpty()) {
			requete += " AND id = " + id;
		}

		switch (membre) {
		case 0:
			requete += " AND membre = true";
			break;
		case 1:
			requete += " AND membre = false";
			break;
		default:
			// NOTHING IS HAPPENING HERE
		}

		accessTabArtiste(requete + " ORDER BY nom");
	}

	private void accessTabArtiste(String requete) {
		this.tabArtiste = new ArrayList<>();
		try {
			ResultSet result = cc.executerRequete(requete);

			while (result.next()) {
				this.tabArtiste.add(new Artiste(result.getString("id"), result.getString("nom"),
						result.getBoolean("membre"), result.getString("photo_url")));
				obtenirLastInt(result.getInt("id"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void accessTabAlbum() {
		accessTabAlbum("SELECT * FROM Album ORDER BY titre");
	}

	public void accessTabAlbum(String id, String titre, String prix, String genre, String anneeSortie,
			String maisonDistribution, String imageUrl, String idArtiste) {
		String requete = "SELECT * FROM Album Where titre LIKE '%" + titre + "%' AND genre LIKE '%" + genre
				+ "%' AND maison_distribution LIKE '%" + maisonDistribution + "%' AND image_url LIKE '%" + imageUrl
				+ "%'";

		if (!id.isEmpty()) {
			requete += " AND id = " + id;
		}

		if (!idArtiste.isEmpty()) {
			requete += " AND artiste_id = " + idArtiste;
		}

		if (!prix.isEmpty()) {
			requete += " AND prix = " + prix;
		}
		accessTabAlbum(requete + " ORDER BY titre");
	}

	private void accessTabAlbum(String requete) {
		this.tabAlbum = new ArrayList<>();

		try {
			ResultSet result = cc.executerRequete(requete);

			while (result.next()) {
				this.tabAlbum.add(new Album(result.getString("id"), result.getString("titre"), result.getString("prix"),
						result.getString("genre"), result.getString("annee_sortie"),
						result.getString("maison_distribution"), result.getString("image_url"),
						result.getString("artiste_id")));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void ajouterArtiste(String nom, String membre, String photoUrl) {
		String requete = "INSERT INTO `Artiste`(`id`, `nom`, `membre`, `photo_url`) VALUES (null, '" + nom + "', "
				+ membre + ", '" + photoUrl + "')";

		updateElement(requete);
	}

	public void ajouterAlbum(String titre, String prix, String genre, String anneeSortie, String maisonDistribution,
			String imageUrl, String idArtiste) {
		String requete = "INSERT INTO `Album`(`id`, `titre`, `prix`, `genre`, `annee_sortie`, `maison_distribution`, `image_url`, `artiste_id`) VALUES (null, '"
				+ titre + "', " + prix + ", '" + genre + "', '" + anneeSortie + "', '" + maisonDistribution + "', '"
				+ imageUrl + "', " + idArtiste + ")";

		updateElement(requete);
	}

	public void modifierAlbum(String id, String titre, String prix, String genre, String anneeSortie,
			String maisonDistribution, String imageUrl, String idArtiste) {

		String requete = "UPDATE Album SET titre = '" + titre + "', prix = " + prix + ", genre = '" + genre
				+ "', annee_sortie = '" + anneeSortie + "', " + "maison_distribution = '" + maisonDistribution
				+ "', image_url = '" + imageUrl + "', artiste_id = " + idArtiste + " WHERE id = " + id;

		updateElement(requete);
	}

	public void modifierAlbumTitre(String id, String titre) {
		String requete = "UPDATE Album SET titre = '" + titre + "' WHERE id = " + id;

		updateElement(requete);
	}

	public void modifierAlbumPrix(String id, String prix) {
		String requete = "UPDATE Album SET prix = " + prix + " WHERE id = " + id;

		updateElement(requete);
	}

	public void modifierAlbumGenre(String id, String genre) {
		String requete = "UPDATE Album SET genre = '" + genre + "' WHERE id = " + id;

		updateElement(requete);
	}

	public void modifierAlbumAnnee(String id, String annee) {
		String requete = "UPDATE Album SET annee_sortie = '" + annee + "' WHERE id = " + id;

		updateElement(requete);
	}

	public void modifierAlbumMaison(String id, String maison) {
		String requete = "UPDATE Album SET maison_distribution = '" + maison + "' WHERE id = " + id;

		updateElement(requete);
	}

	public void modifierArtiste(String id, String nom, String membre, String photoUrl) {
		String requete = "UPDATE Artiste SET nom = '" + nom + "', membre = " + membre + ", photo_url = '" + photoUrl
				+ "' WHERE id = " + id;

		updateElement(requete);
	}

	public void modifierArtisteNom(String id, String nom) {
		String requete = "UPDATE Artiste SET nom = '" + nom + "' WHERE id = " + id;

		updateElement(requete);
	}

	public void modifierArtisteMembre(String id, Boolean membre) {
		String requete = "UPDATE Artiste SET membre = " + membre + " WHERE id = " + id;

		updateElement(requete);
	}

	public void supprimer(String table, int id) {
		String requete = "DELETE FROM " + table + " WHERE id = " + id;

		updateElement(requete);
	}

	private void obtenirLastInt(int nombre) {
		lastInt = (nombre > lastInt) ? nombre : lastInt;
	}
	
	private void updateElement(String requete) {
		try {
			cc.update(requete);
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}

		accessTabArtiste();
		accessTabAlbum();
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
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		return ok;
	}

	public boolean containtArtiste(String nom) {
		boolean ok = false;

		try {
			ResultSet result = cc.executerRequete("SELECT * FROM Artiste WHERE nom LIKE '" + nom + "'");

			if (result.next()) {
				ok = true;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}

		return ok;
	}

	public boolean containtAlbum(String titre) {
		boolean ok = false;

		try {
			ResultSet result = cc.executerRequete("SELECT * FROM Album WHERE titre LIKE '" + titre + "'");

			if (result.next()) {
				ok = true;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}

		return ok;
	}

	public ArrayList<Album> hasAlbum(String id) {
		this.tabAlbum.clear();

		try {
			ResultSet result = cc.executerRequete("SELECT Album.* FROM Artiste, Album WHERE Artiste.id = " + id
					+ " AND Album.artiste_id = Artiste.id");

			while (result.next()) {
				this.tabAlbum.add(new Album(result.getString("id"), result.getString("titre"), result.getString("prix"),
						result.getString("genre"), result.getString("annee_sortie"),
						result.getString("maison_distribution"), result.getString("image_url"),
						result.getString("artiste_id")));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}

		return this.tabAlbum;
	}
}
