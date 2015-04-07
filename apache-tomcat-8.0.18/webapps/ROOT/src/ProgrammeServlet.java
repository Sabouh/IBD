/*
 * @(#)ProgrammeServlet.java	1.0 2007/10/31
 * 
 * Copyright (c) 2007 Sara Bouchenak.
 */
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.Vector;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import modele.*;
import accesBD.*;
import application.*;
import exceptions.*;
import utils.*;
/**
 * Proramme Servlet.
 *
 * This servlet dynamically returns the theater program.
 *
 * @author <a href="mailto:Sara.Bouchenak@imag.fr">Sara Bouchenak</a>
 * @version 1.0, 31/10/2007
 */

public class ProgrammeServlet extends HttpServlet {

   /**
    * HTTP GET request entry point.
    *
    * @param req	an HttpServletRequest object that contains the request 
    *			the client has made of the servlet
    * @param res	an HttpServletResponse object that contains the response 
    *			the servlet sends to the client
    *
    * @throws ServletException   if the request for the GET could not be handled
    * @throws IOException	   if an input or output error is detected 
    *					   when the servlet handles the GET request
    */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
    {
        ServletOutputStream out = res.getOutputStream();   

	  res.setContentType("text/html");

	  out.println("<HEAD><TITLE> Programme de la saison </TITLE></HEAD>");
	  out.println("<BODY bgproperties=\"fixed\" background=\"/images/rideau.JPG\">");
	  out.println("<font color=\"#FFFFFF\"><h1> Programme de la saison </h1>");

	  // TO DO
	  // R�cup�ration de la liste de tous les spectacles de la saison.
	  // Puis construction dynamique d'une page web d�crivant ces spectacles.
	  //recuperation : 
	  try{
		  Utilisateur user = Utilitaires.Identification();
		  if(user!=null){
			  
			  try{

				  Vector<Spectacle> resultat=  BDSpectacles.executerRequete(user,"select * from LesSpectacles");
				//  Vector<Categorie> resultat= BDCategories.getCategorie(user);
				  if (resultat.isEmpty()) {
						out.println(" Liste vide ");
					} else {
						out.print("<p><i><font color=\"#FFFFFF\">");
						for (int i = 0; i < resultat.size(); i++) {
							out.println(resultat.elementAt(i).getNomS() + " (numS: "+ resultat.elementAt(i).getNumS()+")"+ "</i></p><br>");
							out.println("<p><i><font color=\"#FFFFFF\">");
							try{
								Vector<Representation> rep = BDRepresentations.executerRequete(user,"select * from LesRepresentations where numS="+resultat.elementAt(i).getNumS());
									for(int j=0;j<rep.size();j++){
										out.println(rep.elementAt(j).getDate() + " (numS: "+ rep.elementAt(j).getNumS()+")"+ "</i></p><br>");
										try{
											Vector<Place> place = BDPlaces.executerRequete(user,"select noPlace, noRang, numZ from LesPlaces order by noPlace");
											//MaDate date = new MaDate(rep.elementAt(j).getDate());

							                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
							               
							                java.util.Date date = sdf.parse(rep.elementAt(j).getDate());
							                
							               
							                java.sql.Date sqlDate = new Date(date.getTime());
											Vector<Tickets> ti = BDTickets.executerRequete(user,"select * from LesTickets where dateRep =to_date('"+sqlDate+"')");
											//Vector<Tickets> ti = BDTickets.executerRequete(user,"select * from LesTickets");
											/*for(int k=0;k<rep.size();k++){
												if(ti.elementAt(k).getDateRep())
												out.println(ti.elementAt(k).getDateRep());
												out.println("<p><i><font color=\"#FFFFFF\">");
												
											}*/
											//out.println(ti.elementAt(0).getDateRep());
											out.println("<p><i><font color=\"#FFFFFF\">");
											out.println("nb places "+place.size());
											out.println(" nb tickets "+ti.size());
											//out.println("Nb Place = "+(place.size()-ti.size()));
										}catch(PlaceException e){
											out.println("<p><i><font color=\"#FFFFFF\"> Erreur dans table des places : "
													+ e.getMessage()+"</i></p>");
											
										}catch(TicketException e){
											out.println("<p><i><font color=\"#FFFFFF\"> Erreur dans table des tickets : "
													+ e.getMessage()+"</i></p>");											
										}catch(ParseException e ){
						                	out.println("PROBLEME PARSE");
						                }
										out.println("<p><i><font color=\"#FFFFFF\">");
										
									}
							}catch(RepresentationException e){
								out.println("<p><i><font color=\"#FFFFFF\"> Erreur dans l'affichage des representations : "
										+ e.getMessage()+"</i></p>");
								
							}
						}
						out.println("</i></p>");
					}
			  } catch (SpectacleException e) {
					out.println("<p><i><font color=\"#FFFFFF\"> Erreur dans l'affichage des spectacles : "
							+ e.getMessage()+"</i></p>");
			  }
			  
		  }
	  }catch ( ExceptionConnexion e){
			out.println("<p><i><font color=\"#FFFFFF\"> Erreur dans la connexion : "
					+ e.getMessage()+"</i></p>");
	  }catch (ExceptionUtilisateur e){
			out.println("<p><i><font color=\"#FFFFFF\"> Erreur avec l'utilisateur : "
					+ e.getMessage()+"</i></p>");
	  }catch(IOException e){
			out.println("<p><i><font color=\"#FFFFFF\"> Erreur dans IO exception : "
					+ e.getMessage()+"</i></p>");
	  }

      out.println("<a href=\"/servlet/NouvelleRepresentationServlet\">Ajouter une representation</a>"); 
      
	  out.println("<p><i><font color=\"#FFFFFF\">...</i></p>");

	  out.println("<hr><p><font color=\"#FFFFFF\"><a href=\"/index.html\">Accueil</a></p>");
	  out.println("</BODY>");
	  out.close();

    }

   /**
    * HTTP POST request entry point.
    *
    * @param req	an HttpServletRequest object that contains the request 
    *			the client has made of the servlet
    * @param res	an HttpServletResponse object that contains the response 
    *			the servlet sends to the client
    *
    * @throws ServletException   if the request for the POST could not be handled
    * @throws IOException	   if an input or output error is detected 
    *					   when the servlet handles the POST request
    */
    public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
    {
	  doGet(req, res);
    }


   /**
    * Returns information about this servlet.
    *
    * @return String information about this servlet
    */

    public String getServletInfo() {
        return "Retourne le programme du th�atre";
    }

}
