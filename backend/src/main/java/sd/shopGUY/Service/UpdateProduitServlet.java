package sd.shopGUY.Service;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sd.shopGUY.BDD.ServiceDB;
import sd.shopGUY.Metier.Promotion;

/**
 * Created by ProUser on 26/06/2016.
 */
public class UpdateProduitServlet extends HttpServlet {

    public UpdateProduitServlet() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String quantite = req.getParameter("quantite");
        String filename=req.getParameter("filename");

        new ServiceDB().updateProdQuant(quantite,filename);
        //resp.getWriter().write(new Gson().toJson());
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
