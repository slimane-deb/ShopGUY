package sd.shopGUY.Service;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sd.shopGUY.BDD.ServiceDB;
import sd.shopGUY.Metier.Produit;


/**
 * Created by Slimane on 02/05/2016.
 */
public class GetProduitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String density=req.getParameter("density");
        String  classe = req.getParameter("classproduit");
        String client = req.getParameter("typeclient");
        List<Produit> listProduits = new ServiceDB().getProduits(density,classe,client);
        resp.getWriter().write(new Gson().toJson(listProduits));

    }
}
