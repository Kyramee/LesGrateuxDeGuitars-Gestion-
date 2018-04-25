package modele;


public class Album {
	private int id;
	private String titre;
	private double prix;
	private int genre;
	private String anneeSortie;
	private String maisonDistribution;
	private String imageUrl;
	private int idArtiste;

	public Album(int id, String titre, double prix, int genre, String anneeSortie, String maisonDistribution,
			String imageUrl, int idArtiste) {
		this.id = id;
		this.titre = titre;
		this.prix = prix;
		this.genre = genre;
		this.anneeSortie = anneeSortie;
		this.maisonDistribution = maisonDistribution;
		this.imageUrl = imageUrl;
		this.idArtiste = idArtiste;
	}

	public void afficher() {
		System.out.println(this.id + " | " + this.titre + " | " + this.prix + " | " + this.genre + " | "
				+ this.anneeSortie + " | " + this.maisonDistribution + " | " + this.imageUrl + " | " + this.idArtiste);
	}
	
	public int getId() {
		return this.id;
	}
}
