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
import sd.shopGUY.Metier.Promotion;

/**
 * Created by ProUser on 26/06/2016.
 */
public class GetPromoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String density=req.getParameter("density");
        List<Promotion> listPromos = new ServiceDB().getPromos();
        resp.getWriter().write(new Gson().toJson(listPromos));

    }
}
