package accesBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import exceptions.RepresentationException;
import exceptions.ExceptionConnexion;

import modele.Representation;
import modele.Utilisateur;

public class BDRepresentations {

	public BDRepresentations () {
		
	}
	/**
	 * retourne la liste des catégories définies dans la bd
	 * @param Utilisateur
	 * @return Vector<Representation>
	 * @throws RepresentationException
	 * @throws ExceptionConnexion
	 */
	public static Vector<Representation> getRepresentation (Utilisateur user)
	throws RepresentationException, ExceptionConnexion {
		Vector<Representation> res = new Vector<Representation>();
		String requete ;
		Statement stmt ;
		ResultSet rs ;
		Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());
		
		requete = "select numS, dateRep from LesRepresentations order by dateRep";
		//requete = "select * from LesRepresentations";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(requete);
			while (rs.next()) {
				res.addElement(new Representation (rs.getInt(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			throw new RepresentationException (" Problème dans l'interrogation des catégories.."
					+ "Code Oracle " + e.getErrorCode()
					+ "Message " + e.getMessage());
		}
		BDConnexion.FermerTout(conn, stmt, rs);
		return res;
	}
	
	
	
	public static void setRepresentation (Utilisateur user, int num, String date)
	throws RepresentationException, ExceptionConnexion {
		String requete ;
		Statement stmt ;
		ResultSet rs ;
		Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());
		
		requete = "insert into LesRepresentation values ('"+num+"', '"+date+"')";
		System.out.println(requete);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(requete);
			
		} catch (SQLException e) {
			throw new RepresentationException (" Problème dans l'interrogation des catégories.."
					+ "Code Oracle " + e.getErrorCode()
					+ "Message " + e.getMessage());
		}
		BDConnexion.FermerTout(conn, stmt, rs);

	}
	
	
	public static Vector<Representation> executerRequete(Utilisateur user, String requete)throws RepresentationException, ExceptionConnexion{
		Vector<Representation> res = new Vector<Representation>();		
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
					res.addElement(new Representation (rs.getInt(1), rs.getString(2)));
				}
			}
			
		} catch (SQLException e) {
			throw new RepresentationException (" Problème dans l'interrogation des catégories.."
					+ "Code Oracle " + e.getErrorCode()
					+ "Message " + e.getMessage());
		}
		BDConnexion.FermerTout(conn, stmt, rs);
		return res;
	}
}
