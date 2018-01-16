package modele;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Formatter;

import controleur.Client;
import controleur.Technicien;

public class modele {
	private static String encryptPassword(String password)
	{
	    String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(password.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e)
	    {
	        e.printStackTrace();
	    }
	    return sha1;
	}

	private static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}
	
	public static String verifConnection (String login, String mdp) {
		mdp = encryptPassword(mdp);
		String requete =  "Select count(*) as nb, droits from personne where email = '"+login+"'and password = '"+mdp+"';";
		String droits = "";
		System.out.println(requete);
		Bdd uneBdd = new Bdd ("localhost","ppe","root","");
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnection().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			if(unRes.next()) {
				int nb = unRes.getInt("nb");
				if(nb==0) droits ="";
				else droits = unRes.getString("droits");
			}
			unRes.close();
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur : "+requete); //affiche la requete de l'erreur
			exp.printStackTrace(); // affiche l'erreur
		}
		System.out.println(droits);
		return droits;
	}
	public static ArrayList<Client> selectAllClients(){
		ArrayList<Client> lesclients = new ArrayList<Client>();
		String requete = "Select * from personne;";
		Bdd uneBdd = new Bdd("localhost","ppe","root","");
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnection().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			while(unRes.next()) {
				lesclients.add(new Client(unRes.getInt("id_personne"),unRes.getString("nom"),unRes.getString("prenom"),unRes.getString("email")));
			}
			unStat.close();
			unRes.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'insertion dans la base de données : "+requete);
		}
		return lesclients;
	}
	
	public static void insertClient(Client unclient) {
		String requete = "insert into personne values (null,'"+unclient.getNom()+"','"+unclient.getPrenom()+"','"+unclient.getEmail()+"');";
		Bdd uneBdd = new Bdd ("localhost","ppe","root","");
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnection().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur dans l'éxecution de la requete : "+ requete);
		}
	}
	public static void deleteclient(Client unclient) {
		String requete = "delete from personne where id_personne = "+unclient.getIdclient()+";";
		Bdd uneBdd = new Bdd ("localhost","intervention","root","");
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnection().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur dans l'éxecution de la requete : "+ requete);
		}
	}
	public static void updateclient(Client unclient) {
		String requete = "update personne set nom ='"+unclient.getNom()+"',prenom ='"+unclient.getNom()+"',email = '"+unclient.getEmail()+"' where id_personne = "+unclient.getIdclient()+";";
		Bdd uneBdd = new Bdd ("localhost","ppe","root","");
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnection().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur dans l'éxecution de la requete : "+ requete);
		}
	}
	
	/* ------------------------------------------------ Modèle des techniciens ------------------------------------------------ */
	
	public static ArrayList<Technicien> SelectAllTech(){
		ArrayList <Technicien> lestechniciens = new ArrayList<Technicien>();
		String requete ="Select * from technicien ;";
		Bdd uneBdd = new Bdd ("localhost","intervention","root","");
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnection().createStatement();;
			ResultSet unRes = unStat.executeQuery(requete);
			while(unRes.next()) {
				int idtech = unRes.getInt("idtech");
				String nom = unRes.getString("nom");
				String prenom = unRes.getString("prenom");
				String competence = unRes.getString("competence");
				Technicien untechnicien = new Technicien(idtech,nom,prenom,competence);
				lestechniciens.add(untechnicien);

			}
			unStat.close();
			unRes.close();
			uneBdd.seDeconnecter();
			
		}catch(SQLException exp) {
			System.out.println("Erreur de requete : "+requete);
		}
		return lestechniciens;
	}
	public static Technicien SelectWhereTech(Technicien untechnicien) {
		String requete = "Select * from technicien where idtech = "+ untechnicien.getIdtech();
		Technicien untechresultat = null;
		Bdd uneBdd = new Bdd ("localhost","intervention","root","");
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnection().createStatement();;
			ResultSet unRes = unStat.executeQuery(requete);
			if(unRes.next()) {
				int idtech = unRes.getInt("idtech");
				String nom = unRes.getString("nom");
				String prenom = unRes.getString("prenom");
				String competence = unRes.getString("competence");
				untechresultat = new Technicien(idtech,nom,prenom,competence);

			}
			unStat.close();
			unRes.close();
			uneBdd.seDeconnecter();
			
		}catch(SQLException exp) {
			System.out.println("Erreur de requete : "+requete);
		}
		return untechresultat;
	}
}
