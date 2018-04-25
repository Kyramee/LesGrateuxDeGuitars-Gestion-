package modele;


public class Artiste {
	private int id;
	private String nom;
	private boolean membre;
	private String photoUrl;

	public Artiste(int id, String nom, boolean membre, String photoUrl) {
		this.id = id;
		this.nom = nom;
		this.membre = membre;
		this.photoUrl = photoUrl;
	}

	// TEMP VRM TEMP LIKE SUPER TEMP
	public void afficher() {
		System.out.println(this.id + " | " + this.nom + " | " + this.membre + " | " + this.photoUrl);
	}

	public int getId() {
		return this.id;
	}

	public String getNom() {
		return this.nom;
	}
	
	public Boolean getMembre() {
		return this.membre;
	}
}
