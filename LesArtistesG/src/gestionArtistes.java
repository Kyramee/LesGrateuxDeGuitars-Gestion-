import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class gestionArtistes {

	private ArrayList<Artiste> tabArtiste;
	private ArrayList<Album> tabAlbum;

	public gestionArtistes() {
		this.tabArtiste = getTabArtiste();
	}

	public ArrayList<Artiste> getTabArtiste() {
		return getTabArtiste("", "", false);
	}

	public ArrayList<Artiste> getTabArtiste(String id, String nom, Boolean membre) {
		this.tabArtiste = new ArrayList<>();
		String requete = "SELECT * FROM Artiste WHERE membre = " + membre;

		if (!id.isEmpty()) {
			requete += " id = " + id;
		}

		if (!nom.isEmpty()) {
			requete += " nom LIKE '%" + nom + "%'";
		}

		try {
			ResultSet result = connexionControler.executerRequete(requete);

			while (result.next()) {
				this.tabArtiste.add(new Artiste(result.getInt("id"), result.getString("nom"),
						result.getBoolean("membre"), result.getString("photo_url")));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		return this.tabArtiste;
	}

	public ArrayList<Album> getTabAlbum(int idArtist) {
		this.tabAlbum = new ArrayList<>();
		String requete = "SELECT * FROM Album ORDER BY nom WHERE artiste_id = " + idArtist;

		try {
			ResultSet result = connexionControler.executerRequete(requete);

			while (result.next()) {
				this.tabAlbum.add(new Album(result.getInt("id"), result.getString("titre"), result.getDouble("prix"),
						result.getInt("genre_id"), result.getString("annee_sortie"),
						result.getString("maison_distribution"), result.getString("image_url"),
						result.getInt("artist_id")));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		return this.tabAlbum;
	}

	public void ajouterArtiste(String nom, boolean membre, String photoUrl) {
		String requete = "INSERT INTO Artiste('id', 'nom', 'membre', 'photo_url') VALUES (null, '" + nom + "', '"
				+ membre + "', '" + photoUrl + "')";

		try {
			connexionControler.executerRequete(requete);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void ajouterAlbum(String titre, double prix, int genre, String anneeSortie, String maisonDistribution,
			String imageUrl, int idArtiste) {
		String requete = "INSERT INTO Album(id, titre, prix, genre_id, annee_sortie, maison_distribution, image_url, artiste_id) VALUES (null, '"
				+ titre + "', '" + prix + "', '" + genre + "', '" + anneeSortie + "', '" + maisonDistribution + "', '"
				+ imageUrl + "', '" + idArtiste + "', )";
		
		try {
			connexionControler.executerRequete(requete);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void modifierArtiste(int id, String nom, boolean membre, String photoUrl) {
		String requete = "UPDATE Artiste SET nom = '" + nom + ", membre = '" + membre + "', photo_url = '" + photoUrl + "' WHERE id = " + id;
		
		try {
			connexionControler.executerRequete(requete);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public void supprimer(String table, int id) {
		String requete = "DELETE FROM " + table + " WHERE id = " + id;
		
		try {
			connexionControler.executerRequete(requete);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
}
