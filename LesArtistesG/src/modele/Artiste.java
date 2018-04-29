package modele;


public class Artiste {
	private String id;
	private String nom;
	private boolean membre;
	private String photoUrl;

	public Artiste(String id, String nom, boolean membre, String photoUrl) {
		this.id = id;
		this.nom = nom;
		this.membre = membre;
		this.photoUrl = photoUrl;
	}

	public String getId() {
		return this.id;
	}

	public String getNom() {
		return this.nom;
	}
	
	public Boolean getMembre() {
		return this.membre;
	}
	
	public String getUrl() {
		return this.photoUrl;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setMembre(Boolean membre) {
		this.membre = membre;
	}
}
