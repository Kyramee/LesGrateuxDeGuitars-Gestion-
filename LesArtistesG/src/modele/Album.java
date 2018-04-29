package modele;


public class Album {
	private String id;
	private String titre;
	private String prix;
	private String genre;
	private String anneeSortie;
	private String maisonDistribution;
	private String imageUrl;
	private String idArtiste;

	public Album(String id, String titre, String prix, String genre, String anneeSortie, String maisonDistribution,
			String imageUrl, String idArtiste) {
		this.id = id;
		this.titre = titre;
		this.prix = prix;
		this.genre = genre;
		this.anneeSortie = anneeSortie;
		this.maisonDistribution = maisonDistribution;
		this.imageUrl = imageUrl;
		this.idArtiste = idArtiste;
	}
	
	public String getId() {
		return this.id;
	}

	public String getTitre() {
		return this.titre;
	}

	public String getPrix() {
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
	
	public String getImageUrl() {
		return this.imageUrl;
	}
	
	public String getIdArtiste() {
		return this.idArtiste;
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setDate(String annerrSortie) {
		this.anneeSortie = annerrSortie;
	}

	public void setMaison(String maisonDistribution) {
		this.maisonDistribution = maisonDistribution;
	}
}
