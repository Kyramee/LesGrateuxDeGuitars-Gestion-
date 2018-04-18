import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connexionControler {
	private final static String url = "jdbc:mysql://localhost/LesArtistesG";
	
	private static Connection getConnexion() throws SQLException, ClassNotFoundException {
		Class.forName("org.gjt.mm.mysql.Driver"); 
		return DriverManager.getConnection(url,"root","mysql");
	}
	
	private static void closeConnexion(Connection connexion) throws SQLException, ClassNotFoundException {
		Class.forName("org.gjt.mm.mysql.Driver"); 
		connexion.close();
	}
	
	public static ResultSet executerRequete(String requete) throws ClassNotFoundException, SQLException {
		Connection connexion = getConnexion();

		Statement statement = connexion.createStatement();
		
		ResultSet rs = statement.executeQuery(requete);
		
		closeConnexion(connexion);
		
		return rs;
	}
}
