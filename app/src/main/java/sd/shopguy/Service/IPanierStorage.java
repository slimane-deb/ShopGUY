package sd.shopguy.Service;

import android.content.Context;

import java.util.ArrayList;

import sd.shopguy.Metier.ProduitPanier;

/**
 * Created by ProUser on 27/06/2016.
 */
public interface IPanierStorage {
     void savePanier(ArrayList<ProduitPanier> pp) ;
     ArrayList<ProduitPanier> restorePanier(Context context) ;
    void commitPanier();
    void rollbackPanier();
}
