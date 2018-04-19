import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connexionControler {
	private final String url = "jdbc:mysql://localhost/LesArtistesG";
	private Connection connexion;
	
	private Connection getConnexion() throws SQLException, ClassNotFoundException {
		Class.forName("org.gjt.mm.mysql.Driver"); 
		return DriverManager.getConnection(url,"root","mysql");
	}
	
	public void closeConnexion() throws SQLException, ClassNotFoundException {
		Class.forName("org.gjt.mm.mysql.Driver"); 
		this.connexion.close();
	}
	
	public ResultSet executerRequete(String requete) throws ClassNotFoundException, SQLException {
		this.connexion = getConnexion();

		Statement statement = connexion.createStatement();
		
		return statement.executeQuery(requete);

	}
	
	public void update(String requete) throws SQLException, ClassNotFoundException {
		this.connexion = getConnexion();

		Statement statement = connexion.createStatement();
		
		statement.executeUpdate(requete);
	}
}
