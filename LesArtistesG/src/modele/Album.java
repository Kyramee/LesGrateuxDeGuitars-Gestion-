package modele;


public class Album {
	private int id;
	private String titre;
	private double prix;
	private String genre;
	private String anneeSortie;
	private String maisonDistribution;
	private String imageUrl;
	private int idArtiste;

	public Album(int id, String titre, double prix, String genre, String anneeSortie, String maisonDistribution,
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
	
	public int getId() {
		return this.id;
	}

	public String getTitre() {
		return this.titre;
	}

	public double getPrix() {
		return this.prix;
	}

	public String getGenre() {
		return this.genre;
	}

	public String getDate() {
		return this.anneeSortie;
	}

	public String getMaison() {
		return this.maisonDistribution;
	}
}
