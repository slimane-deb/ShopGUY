package sd.shopguy.Task;

import java.util.ArrayList;

import sd.shopguy.Metier.Produit;

/**
 * Created by ProUser on 24/06/2016.
 */
public interface AsyncResponse {

        void processFinish(ArrayList<Produit> produits);

}
