/*
 * @(#)ProgrammeServlet.java	1.0 2007/10/31
 * 
 * Copyright (c) 2007 Sara Bouchenak.
 */
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Vector;

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

public class ReservationServlet extends HttpServlet {

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
				  Vector<Categorie> resultat= BDCategories.getCategorie(user);
				  if (resultat.isEmpty()) {
						out.println(" Liste vide ");
					} else {
						out.print("<p><i><font color=\"#FFFFFF\">");
						for (int i = 0; i < resultat.size(); i++) {
							out.println(resultat.elementAt(i).getCategorie() + " (prix : "+ resultat.elementAt(i).getPrix()+")"+ "</i></p><br>");
							out.println("<p><i><font color=\"#FFFFFF\">");
						}
						out.println("</i></p>");
					}
			  } catch (CategorieException e) {
					out.println("<p><i><font color=\"#FFFFFF\"> Erreur dans l'affichage des categories : "
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
