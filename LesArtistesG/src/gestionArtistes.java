import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class gestionArtistes {

	private ArrayList<Artiste> tabArtiste;
	private ArrayList<Album> tabAlbum;

	public gestionArtistes() {
		this.tabArtiste = getTabArtiste();
	}

	public ArrayList<Artiste> getTabArtiste() {
		this.tabArtiste = new ArrayList<>();
		String requete = "SELECT * FROM Artiste ORDER BY nom";

		try {
			Connection connexion = connexionControler.getConnexion();

			Statement statement = connexion.createStatement();

			ResultSet result = statement.executeQuery(requete);

			while (result.next()) {
				this.tabArtiste.add(new Artiste(result.getInt("id"), result.getString("nom"),
						result.getBoolean("membre"), result.getString("photo_url")));
			}

			connexionControler.closeConnexion(connexion);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		return this.tabArtiste;
	}

	public ArrayList<Album> getTabAlbum(int idArtist) {
		this.tabAlbum = new ArrayList<>();
		String requete = "SELECT * FROM Album ORDER BY nom WHERE artiste_id = " + idArtist;
		
		try {
			Connection connexion = connexionControler.getConnexion();

			Statement statement = connexion.createStatement();

			ResultSet result = statement.executeQuery(requete);

			while (result.next()) {
				this.tabAlbum.add(new Album(result.getInt("id"), result.getString("titre"), result.getDouble("prix"), result.getInt("genre_id"), result.getString("annee_sortie"), result.getString("maison_distribution"), result.getString("image_url"), result.getInt("artist_id")));
			}

			connexionControler.closeConnexion(connexion);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur: " + e, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
}
