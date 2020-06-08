package sd.shopGUY.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sd.shopGUY.BDD.ServiceDB;


/**
 * Created by Slimane on 09/05/2016.
 */
public class AddCommandServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num = req.getParameter("id");
        String date=req.getParameter("date");
        String etat=req.getParameter("etat");
        String montant=req.getParameter("montant");
        String idUser=req.getParameter("idUser");

        new ServiceDB().addCommand(num,date,etat,montant,idUser);


    }


}
