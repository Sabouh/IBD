package utils;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;
import java.awt.Frame;

import jus.util.IO;

import accesBD.BDCategories;
import accesBD.BDConnexion;

import modele.Utilisateur;
import modele.Categorie;
import exceptions.ExceptionUtilisateur;
import exceptions.ExceptionConnexion;
import exceptions.CategorieException;

/**
 * les operations de l'application
 * 
 * @author fauvet
 * 
 */
public class Utilitaires {

	public Utilitaires() {
	}

	/**
	 * Affiche les categories du theatre avec pour chacune son prix
	 * 
	 * @param user
	 *            l'utilisateur identifie
	 * @throws ExceptionConnexion
	 * @throws IOException
	 */
	public static void AfficherCategories(Utilisateur user) throws IOException {
		Vector<Categorie> res = new Vector<Categorie>();
		try {
			IO.afficherln("===================");
			IO.afficherln("Listes des categories tarifaires");
			res = BDCategories.getCategorie(user);
			if (res.isEmpty()) {
				IO.afficherln(" Liste vide ");
			} else {
				for (int i = 0; i < res.size(); i++) {
					IO.afficherln(res.elementAt(i).getCategorie() + " (prix : "
							+ res.elementAt(i).getPrix() + ")");
				}
			}
			IO.afficherln("===================");
		} catch (CategorieException e) {
			IO.afficherln(" Erreur dans l'affichage des categories : "
					+ e.getMessage());
		} catch (ExceptionConnexion e) {
			IO.afficherln(" Erreur dans l'affichage des categories : "
					+ e.getMessage());
		}

	}
	
	public static Vector<Categorie> AfficherCategories() throws IOException{
		Vector<Categorie> res = new Vector<Categorie>();
		Utilisateur user = new Utilisateur("sabine");
		try {
			IO.afficherln("===================");
			IO.afficherln("Listes des categories tarifaires");
			res = BDCategories.getCategorie(user);
			if (res.isEmpty()) {
				IO.afficherln(" Liste vide ");
			} else {
				for (int i = 0; i < res.size(); i++) {
					IO.afficherln(res.elementAt(i).getCategorie() + " (prix : "
							+ res.elementAt(i).getPrix() + ")");
				}
			}
			IO.afficherln("===================");
		} catch (CategorieException e) {
			IO.afficherln(" Erreur dans l'affichage des categories : "
					+ e.getMessage());
		} catch (ExceptionConnexion e) {
			IO.afficherln(" Erreur dans l'affichage des categories : "
					+ e.getMessage());
		}
		return res;
	}
	
	public static void AjouterCategorie(Utilisateur user) throws IOException{
		String pos;
		String prix;
		// lecture des parametres de connexion dans connection.conf
		Properties p = new Properties();
		InputStream is = null;
		is = new FileInputStream(utils.Constantes.Config);
		p.load(is);
		pos = p.getProperty("position");
		prix = p.getProperty("price");
		if (pos == null || prix == null) {
			CategorieAjoutDialog pos_dialog = new CategorieAjoutDialog(
					new Frame(""));
			pos_dialog.setVisible(true);
			pos = pos_dialog.getPos();
			prix = pos_dialog.getPrix();
		
			try {
					IO.afficherln("===================");
					IO.afficherln("Ajout de  categorie tarifaire");
					BDCategories.setCategorie (user,pos,Integer.parseInt(prix));
				
					IO.afficherln("===================");
				} catch (CategorieException e) {
					IO.afficherln(" Erreur dans l'ajout de la catégorie : "
							+ e.getMessage());
				} catch (ExceptionConnexion e) {
					IO.afficherln(" Erreur dans l'ajout de la catégorie : "
							+ e.getMessage());
				}
		}
	}
	public static void ModifierCategorie(Utilisateur user) throws IOException{
		String pos;
		String prix;
		// lecture des parametres de connexion dans connection.conf
		Properties p = new Properties();
		InputStream is = null;
		is = new FileInputStream(utils.Constantes.Config);
		p.load(is);
		pos = p.getProperty("position");
		prix = p.getProperty("price");
		if (pos == null || prix == null) {
			CategorieAjoutDialog pos_dialog = new CategorieAjoutDialog(
					new Frame(""));
			pos_dialog.setVisible(true);
			pos = pos_dialog.getPos();
			prix = pos_dialog.getPrix();
		
			try {
					IO.afficherln("===================");
					IO.afficherln("Ajout de  categorie tarifaire");
					BDCategories.setCategorie (user,pos,Integer.parseInt(prix));
				
					IO.afficherln("===================");
				} catch (CategorieException e) {
					IO.afficherln(" Erreur dans l'ajout de la catégorie : "
							+ e.getMessage());
				} catch (ExceptionConnexion e) {
					IO.afficherln(" Erreur dans l'ajout de la catégorie : "
							+ e.getMessage());
				}
		}
	}
	
	public static void ExecuterRequete(Utilisateur user) throws IOException{
		Vector<Categorie> res = new Vector<Categorie>();
		String requete;
		// lecture des parametres de connexion dans connection.conf
		Properties p = new Properties();
		InputStream is = null;
		is = new FileInputStream(utils.Constantes.Config);
		p.load(is);
		requete = p.getProperty("requete");
		if (requete == null ) {
			RequeteExecutionDialog req_dialog = new RequeteExecutionDialog(
					new Frame(""));
			req_dialog.setVisible(true);
			requete = req_dialog.getRequete();
		
			try {
				IO.afficherln("===================");
					IO.afficherln("===================");
					IO.afficherln("Execution de la requete");
					res = BDCategories.executerRequete(user,requete);
					if(res!=null){
						if (res.isEmpty()) {
							IO.afficherln(" Liste vide ");
						} else {
							for (int i = 0; i < res.size(); i++) {
								IO.afficherln(res.elementAt(i).getCategorie() + " (prix : "
										+ res.elementAt(i).getPrix() + ")");
							}
						}
					}
					IO.afficherln("===================");
				} catch (CategorieException e) {
					IO.afficherln(" Erreur dans l'ajout de la catégorie : "
							+ e.getMessage());
				} catch (ExceptionConnexion e) {
					IO.afficherln(" Erreur dans l'ajout de la catégorie : "
							+ e.getMessage());
				}
		}
	}

	/**
	 * effectue la connexion pour l'utilisateur
	 * 
	 * @return l'oid de l'objet utilisateur
	 * @throws ExceptionUtilisateur
	 */
	public static Utilisateur Identification() throws ExceptionConnexion,
			ExceptionUtilisateur, IOException {
		Utilisateur user = null;
		String login;
		String passwd;
		// lecture des parametres de connexion dans connection.conf
	/*	Properties p = new Properties();
		InputStream is = null;
		is = new FileInputStream(utils.Constantes.Config);
		p.load(is);
		login = p.getProperty("user");
		passwd = p.getProperty("mdp");*/
		login = "bouhdids";
		passwd = "bd2015";
		/*
		if (login == null || login.equals("MYUSERNAME")) {
			UserNamePasswordDialog login_dialog = new UserNamePasswordDialog(
					new Frame(""));
			login_dialog.setVisible(true);
			login = login_dialog.getUid();
			passwd = login_dialog.getPwd();
		}*/
		/* test de la connexion */
		Connection conn = BDConnexion.getConnexion(login, passwd);
		if (conn != null) {
			IO.afficherln("Connexion reussie...");
			BDConnexion.FermerTout(conn, null, null);
			user = new Utilisateur(login, passwd);
		} else {
			throw new ExceptionConnexion("Connexion impossible\n");
		}
		return user;
	}
}
