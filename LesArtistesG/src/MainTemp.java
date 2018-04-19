
public class MainTemp {

	public static void main( String[] args ) {
		gestionArtistes gA = new gestionArtistes();

		System.out.println( "\nAffichage des test de d'update" );
		System.out.println( "La size des artiste = " + gA.getTabArtiste().size() + "\n" );

		for ( int i = 0; i < gA.getTabArtiste().size(); i++ ) {
			gA.getTabArtiste().get( i ).afficher();
		}

		System.out.println( "\nAffichage des test des albums" );
		System.out.println( "La size de l'album = " + gA.getTabAlbum().size() + "\n" );

		for ( int i = 0; i < gA.getTabAlbum().size(); i++ ) {
			gA.getTabAlbum().get( i ).afficher();
		}
	}
}
