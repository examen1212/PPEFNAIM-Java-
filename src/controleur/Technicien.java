package controleur;

public class Technicien {
	private int idtech;
	private String nom,prenom,competence;
	
	public Technicien() {
		this.idtech = 0;
		this.nom = "";
		this.prenom = "";
		this.competence = "";
	}
	public Technicien (int idtech, String nom, String prenom, String competence) {
		this.idtech = idtech;
		this.nom = nom;
		this.prenom = prenom;
		this.competence = competence;
	}
	public Technicien (String nom, String prenom, String competence) {
		this.idtech = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.competence = competence;
	}
	public int getIdtech() {
		return idtech;
	}
	public void setIdtech(int idtech) {
		this.idtech = idtech;
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
	public String getCompetence() {
		return competence;
	}
	public void setCompetence(String competence) {
		this.competence = competence;
	}
	
}
