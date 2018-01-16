package controleur;

public class Client {
	private static int nbchampsclients = 4;
	private int idclient;
	private String nom,prenom,email;
	
	public Client() {
		this.idclient = 0;
		this.nom = this.prenom = this.email = "";
	}
	public Client(int idclient, String nom, String prenom, String email) {
		this.idclient = idclient;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}
	public Client(String nom, String prenom, String email) {
		this.idclient = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}
	public int getIdclient() {
		return idclient;
	}
	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public static int getNbchampsclients() {
		return nbchampsclients;
	}
	public static void setNbchampsclients(int nbchampsclients) {
		Client.nbchampsclients = nbchampsclients;
	}
	
}
