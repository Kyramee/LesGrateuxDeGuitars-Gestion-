import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class gestionArtistes {

	private ArrayList<Artiste> tabArtiste;
	private ArrayList<Album> tabAlbum;
	private connexionControler cc;

	public gestionArtistes() {
		cc = new connexionControler();
		this.tabArtiste = accessTabArtiste();
		this.tabAlbum = accessTabAlbum();
	}

	public ArrayList<Artiste> getTabArtiste() {
		return this.tabArtiste;
	}

	public ArrayList<Album> getTabAlbum() {
		return this.tabAlbum;
	}

	public ArrayList<Artiste> accessTabArtiste() {
		return accessTabArtiste("", "", false);
	}

	public ArrayList<Artiste> accessTabArtiste(String id, String nom, Boolean membre) {
		this.tabArtiste = new ArrayList<>();
		String requete = "SELECT * FROM Artiste WHERE membre = " + membre;

		if (!id.isEmpty()) {
			requete += " id = " + id;
		}

		if (!nom.isEmpty()) {
			requete += " nom LIKE '%" + nom + "%'";
		}

		try {
			ResultSet result = cc.executerRequete(requete);

			while (result.next()) {
				this.tabArtiste.add(new Artiste(result.getInt("id"), result.getString("nom"),
						result.getBoolean("membre"), result.getString("photo_url")));
			}

			cc.closeConnexion();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		return this.tabArtiste;
	}

	public ArrayList<Album> accessTabAlbum() {
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
		return this.tabAlbum;
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
	}

	public void modifierArtiste(int id, String nom, String membre, String photoUrl) {
		String requete = "UPDATE Artiste SET nom = '" + nom + ", membre = " + membre + ", photo_url = '" + photoUrl
				+ "' WHERE id = " + id;

		try {
			cc.update(requete);
			cc.closeConnexion();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void supprimer(String table, int id) {
		String requete = "DELETE FROM " + table + " WHERE id = " + id;

		try {
			cc.update(requete);
			cc.closeConnexion();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
}
