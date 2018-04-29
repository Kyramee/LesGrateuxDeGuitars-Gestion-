package controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class controlerConnexionBD {
	private final String url = "jdbc:mysql://localhost/LesArtistesG";
	private Connection connexion;

	public controlerConnexionBD() throws SQLException, ClassNotFoundException {
		Class.forName("org.gjt.mm.mysql.Driver");
		this.connexion = DriverManager.getConnection(url, "root", "mysql");
	}

	public void closeConnexion() throws SQLException, ClassNotFoundException {
		Class.forName("org.gjt.mm.mysql.Driver");
		this.connexion.close();
	}

	public ResultSet executerRequete(String requete) throws ClassNotFoundException, SQLException {
		try {
			Statement statement = connexion.createStatement();
			return statement.executeQuery(requete);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "La requete est invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public void update(String requete) throws SQLException, ClassNotFoundException {
		try {
			Statement statement = connexion.createStatement();
			statement.executeUpdate(requete);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "La requete est invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
}
