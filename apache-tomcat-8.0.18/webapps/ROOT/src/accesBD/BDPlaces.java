package accesBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import exceptions.PlaceException;
import exceptions.ExceptionConnexion;

import modele.Place;
import modele.Utilisateur;

public class BDPlaces {

	public BDPlaces () {
		
	}
	/**
	 * retourne la liste des catégories définies dans la bd
	 * @param Utilisateur
	 * @return Vector<Place>
	 * @throws PlaceException
	 * @throws ExceptionConnexion
	 */
	public static Vector<Place> getPlace (Utilisateur user)
	throws PlaceException, ExceptionConnexion {
		Vector<Place> res = new Vector<Place>();
		String requete ;
		Statement stmt ;
		ResultSet rs ;
		Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());
		
		requete = "select noPlace, noRang, numZ from LesPlaces order by noPlace";
		//requete = "select * from LesPlaces";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(requete);
			while (rs.next()) {
				res.addElement(new Place(rs.getInt(1),rs.getInt(2), rs.getInt(3)));
			}
		} catch (SQLException e) {
			throw new PlaceException (" Problème dans l'interrogation des catégories.."
					+ "Code Oracle " + e.getErrorCode()
					+ "Message " + e.getMessage());
		}
		BDConnexion.FermerTout(conn, stmt, rs);
		return res;
	}
	
	
	
	public static void setPlace (Utilisateur user, int num, String n)
	throws PlaceException, ExceptionConnexion {
		String requete ;
		Statement stmt ;
		ResultSet rs ;
		Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());
		
		requete = "insert into LesPlaces values ('"+num+"', '"+n+"')";
		System.out.println(requete);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(requete);
			
		} catch (SQLException e) {
			throw new PlaceException (" Problème dans l'interrogation des catégories.."
					+ "Code Oracle " + e.getErrorCode()
					+ "Message " + e.getMessage());
		}
		BDConnexion.FermerTout(conn, stmt, rs);

	}
	
	
	public static Vector<Place> executerRequete(Utilisateur user, String requete)throws PlaceException, ExceptionConnexion{
		Vector<Place> res = new Vector<Place>();		
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
					res.addElement(new Place (rs.getInt(1),rs.getInt(2), rs.getInt(3)));
				}
			}
			
		} catch (SQLException e) {
			throw new PlaceException (" Problème dans l'interrogation des catégories.."
					+ "Code Oracle " + e.getErrorCode()
					+ "Message " + e.getMessage());
		}
		BDConnexion.FermerTout(conn, stmt, rs);
		return res;
	}
}
