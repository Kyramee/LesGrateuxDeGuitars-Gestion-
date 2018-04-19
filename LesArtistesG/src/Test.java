
public class Test {
	public void newtest() {
		gestionArtistes gA = new gestionArtistes();
		
		
		//LES BOOLEAN DOIVENT ETRE TRANSMIT EN STRING.... why tho?
		System.out.println( "\nAffichage des test de d'update" );
		System.out.println("\nLa size des artiste = "+ gA.getTabArtiste().size() + "\n");
		gA.ajouterArtiste("TestAjout", "false", "Test5aa");
		
		for(int i = 0; i < gA.getTabArtiste().size(); i++) {
			gA.getTabArtiste().get(i).afficher();
		}
		
		gA.supprimer("Artiste", gA.getTabArtiste().get(gA.getTabArtiste().size() - 1).getId());
			
		System.out.println( "\nAffichage des test des albums" );
		System.out.println("La size de l'album = " + gA.getTabAlbum().size() + "\n");
		
		gA.ajouterAlbum("Test ajout", 30.22, 2, "1999-02-02", "test", "test", 1);
		
		for(int i = 0; i < gA.getTabAlbum().size(); i++) {
			gA.getTabAlbum().get(i).afficher();
		}
		
		gA.supprimer("Album", gA.getTabAlbum().get(gA.getTabAlbum().size() - 1).getId());
		
		//REST A TESTER LES METHODE QUI UPDATE ET A AJOUTER D'AUTRE METHODE QUE TU PENSES UTILES
		
		//gA.ajouterArtiste("TestModifier", "false", "Modification");
		
		System.out.println( "\nAffichage des test de d'update" );
		System.out.println( "Artiste\n" );
		gA.modifierArtiste( 47, "a-TestModifier-Valide", "false", "Modifition-Valide" );
		
		for(int i = 0; i < gA.getTabArtiste().size(); i++) {
			gA.getTabArtiste().get(i).afficher();
		}
		
		gA.modifierArtiste( 47, "a-TestModifier-InValide", "false", "Modifition-InValide" );
		
		System.out.println( "\nAlbum\n" );
		
		gA.modifierAlbum( 55, "a-TestModiAlbum-Valide", 99.9, 1, "2000-01-01", "MaisonValide", "ImageValide", 1 );
		
		for(int i = 0; i < gA.getTabAlbum().size(); i++) {
			gA.getTabAlbum().get(i).afficher();
		}
		
		gA.modifierAlbum( 55, "a-TestModiAlbum-InValide", 99.9, 1, "2000-01-01", "MaisonInValide", "ImageInValide", 1 );
	}
}
