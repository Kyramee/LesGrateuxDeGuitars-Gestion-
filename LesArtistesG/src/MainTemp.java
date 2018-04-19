
public class MainTemp {

	public static void main(String[] args) {
		gestionArtistes gA = new gestionArtistes();
		
		System.out.println(gA.getTabArtiste().size());
		
		//LES BOOLEAN DOIVENT ETRE TRANSMIT EN STRING.... why tho?
		gA.ajouterArtiste("TestAjout", "false", "Test5aa");
		
		for(int i = 0; i < gA.getTabArtiste().size(); i++) {
			gA.getTabArtiste().get(i).afficher();
		}
		
		gA.supprimer("Artiste", gA.getTabArtiste().get(gA.getTabArtiste().size() - 1).getId());
		
		System.out.println(gA.getTabAlbum().size());
		
		gA.ajouterAlbum("Test ajout", 30.22, 2, "1999-02-02", "test", "test", 1);
		
		for(int i = 0; i < gA.getTabAlbum().size(); i++) {
			gA.getTabAlbum().get(i).afficher();
		}
		
		gA.supprimer("Album", gA.getTabAlbum().get(gA.getTabAlbum().size() - 1).getId());
		
		//REST A TESTER LES METHODE QUI UPDATE ET A AJOUTER D'AUTRE METHODE QUE TU PENSES UTILES
	}
}
