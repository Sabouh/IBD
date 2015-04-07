package accesBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import exceptions.TicketException;
import exceptions.ExceptionConnexion;

import modele.Tickets;
import modele.Utilisateur;

public class BDTickets {

	public BDTickets () {
		
	}
	/**
	 * retourne la liste des catégories définies dans la bd
	 * @param Utilisateur
	 * @return Vector<Ticket>
	 * @throws TicketException
	 * @throws ExceptionConnexion
	 */
	public static Vector<Tickets> getTicket (Utilisateur user)
	throws TicketException, ExceptionConnexion {
		Vector<Tickets> res = new Vector<Tickets>();
		String requete ;
		Statement stmt ;
		ResultSet rs ;
		Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());
		
		requete = "select * from LesTickets order by noSerie";
		//requete = "select * from LesTickets";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(requete);
			while (rs.next()) {
				res.addElement(new Tickets(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6)));
			}
		} catch (SQLException e) {
			throw new TicketException (" Problème dans l'interrogation des catégories.."
					+ "Code Oracle " + e.getErrorCode()
					+ "Message " + e.getMessage());
		}
		BDConnexion.FermerTout(conn, stmt, rs);
		return res;
	}
	
	
	
	public static void setTicket (Utilisateur user, int num, String n)
	throws TicketException, ExceptionConnexion {
		String requete ;
		Statement stmt ;
		ResultSet rs ;
		Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());
		
		requete = "insert into LesTickets values ('"+num+"', '"+n+"')";
		System.out.println(requete);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(requete);
			
		} catch (SQLException e) {
			throw new TicketException (" Problème dans l'interrogation des catégories.."
					+ "Code Oracle " + e.getErrorCode()
					+ "Message " + e.getMessage());
		}
		BDConnexion.FermerTout(conn, stmt, rs);

	}
	
	
	public static Vector<Tickets> executerRequete(Utilisateur user, String requete)throws TicketException, ExceptionConnexion{
		Vector<Tickets> res = new Vector<Tickets>();		
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
					res.addElement(new Tickets (rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6)));
				}
			}
			
		} catch (SQLException e) {
			throw new TicketException (" Problème dans l'interrogation des catégories.."
					+ "Code Oracle " + e.getErrorCode()
					+ "Message " + e.getMessage());
		}
		BDConnexion.FermerTout(conn, stmt, rs);
		return res;
	}
}
