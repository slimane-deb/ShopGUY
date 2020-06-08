package sd.shopGUY.Service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sd.shopGUY.BDD.ServiceDB;

/**
 * Created by ProUser on 28/06/2016.
 */
public class UpdateCommandServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String etat = req.getParameter("etat");
        String idUser = req.getParameter("idUser");

        new ServiceDB().updateCommand(etat, idUser);


    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* String filename=req.getParameter("filename");
        String quantite = req.getParameter("quantite");
        List<Promotion> listPromos = new ServiceDB().getPromos();
        resp.getWriter().write(new Gson().toJson(listPromos));*/
        super.doPost(req, resp);
    }
}
