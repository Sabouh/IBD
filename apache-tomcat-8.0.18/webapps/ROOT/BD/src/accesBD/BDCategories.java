package accesBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import exceptions.CategorieException;
import exceptions.ExceptionConnexion;

import modele.Categorie;
import modele.Utilisateur;

public class BDCategories {

	public BDCategories () {
		
	}
	/**
	 * retourne la liste des catégories définies dans la bd
	 * @param Utilisateur
	 * @return Vector<Categorie>
	 * @throws CategorieException
	 * @throws ExceptionConnexion
	 */
	public static Vector<Categorie> getCategorie (Utilisateur user)
	throws CategorieException, ExceptionConnexion {
		Vector<Categorie> res = new Vector<Categorie>();
		String requete ;
		Statement stmt ;
		ResultSet rs ;
		Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());
		
		requete = "select nomc, prix from LesCategories order by nomc";
		//requete = "select * from LesCategories";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(requete);
			while (rs.next()) {
				res.addElement(new Categorie (rs.getString(1), rs.getFloat(2)));
			}
		} catch (SQLException e) {
			throw new CategorieException (" Problème dans l'interrogation des catégories.."
					+ "Code Oracle " + e.getErrorCode()
					+ "Message " + e.getMessage());
		}
		BDConnexion.FermerTout(conn, stmt, rs);
		return res;
	}
	
	
	
	public static void setCategorie (Utilisateur user, String n, int num)
	throws CategorieException, ExceptionConnexion {
		String requete ;
		Statement stmt ;
		ResultSet rs ;
		Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());
		
		requete = "insert into LesCategories values ('"+n+"', '"+num+"')";
		System.out.println(requete);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(requete);
			
		} catch (SQLException e) {
			throw new CategorieException (" Problème dans l'interrogation des catégories.."
					+ "Code Oracle " + e.getErrorCode()
					+ "Message " + e.getMessage());
		}
		BDConnexion.FermerTout(conn, stmt, rs);

	}
	
	public static Vector<Categorie> executerRequete(Utilisateur user, String requete)throws CategorieException, ExceptionConnexion{
		Vector<Categorie> res = new Vector<Categorie>();		
		Statement stmt ;
		ResultSet rs ;
		Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(requete);
			if (!requete.contains("select")) {
				BDConnexion.FermerTout(conn, stmt, rs);
				return null;
			}else{
				while (rs.next()) {
					res.addElement(new Categorie (rs.getString(1), rs.getFloat(2)));
				}
			}
			
		} catch (SQLException e) {
			throw new CategorieException (" Problème dans l'interrogation des catégories.."
					+ "Code Oracle " + e.getErrorCode()
					+ "Message " + e.getMessage());
		}
		BDConnexion.FermerTout(conn, stmt, rs);
		return res;
	}
}
