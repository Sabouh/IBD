package accesBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import exceptions.SpectacleException;
import exceptions.ExceptionConnexion;

import modele.Spectacle;
import modele.Utilisateur;

public class BDRepresentations {

	public BDRepresentations () {
		
	}
	/**
	 * retourne la liste des catégories définies dans la bd
	 * @param Utilisateur
	 * @return Vector<Spectacle>
	 * @throws SpectacleException
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
		//requete = "select * from LesSpectacles";
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
		
		requete = "insert into LesRepresentation values ('"+num+"', '"+n+"')";
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
	
	
	public static Vector<Spectacle> executerRequete(Utilisateur user, String requete)throws SpectacleException, ExceptionConnexion{
		Vector<Spectacle> res = new Vector<Spectacle>();		
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
					res.addElement(new Spectacle (rs.getInt(1), rs.getString(2)));
				}
			}
			
		} catch (SQLException e) {
			throw new SpectacleException (" Problème dans l'interrogation des catégories.."
					+ "Code Oracle " + e.getErrorCode()
					+ "Message " + e.getMessage());
		}
		BDConnexion.FermerTout(conn, stmt, rs);
		return res;
	}
}
