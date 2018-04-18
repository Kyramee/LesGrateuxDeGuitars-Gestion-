import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connexionControler {
	private final static String url = "jdbc:mysql://localhost/LesArtistesG";
	
	public static Connection getConnexion() throws SQLException, ClassNotFoundException {
		Class.forName("org.gjt.mm.mysql.Driver"); 
		return DriverManager.getConnection(url,"root","mysql");
	}
	
	public static void closeConnexion(Connection connexion) throws SQLException, ClassNotFoundException {
		Class.forName("org.gjt.mm.mysql.Driver"); 
		connexion.close();
	}
}
