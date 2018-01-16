package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bdd {
	private String serveur, bdd, user, mdp;
	private Connection maConnection;
	
	public Bdd (String serveur, String bdd, String user, String mdp) {
		this.serveur = serveur;
		this.bdd = bdd;
		this.user = user;
		this.mdp = mdp;
		this.maConnection = null;
	}
	public void chargerPilote() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e){
			System.out.println("Erreur du chargement du pilote jdbc !");
		}
	}
	public void seConnecter() {
		String url = "jdbc:mysql://"+this.serveur+"/"+this.bdd;
		this.chargerPilote();
		try {
			this.maConnection = DriverManager.getConnection(url,this.user,this.mdp);
		} catch(SQLException e) {
			System.out.println("Erreur de connexion : "+url);
		}
	}
	public void seDeconnecter() {
		try {
			if (this.maConnection != null) {
				this.maConnection.close();
			}
		} catch(SQLException e) {
			System.out.println("Erreur de fermeture de ma connexion");
		}
	}
	public Connection getMaConnection() {
		return this.maConnection;
	}
}
