package sd.shopguy.Frags;


import android.support.v4.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import sd.shopguy.Main.MainActivity;
import sd.shopguy.Main.R;
import sd.shopguy.Metier.Produit;
import sd.shopguy.Metier.ProduitPanier;
import sd.shopguy.Util.UtilService;

/**
 * Created by pc on 04/03/2016.
 */
public class DetailFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.detail_fragment,null);
        Bundle bundle = getArguments();

        if (bundle != null) {

            final Produit produit = (Produit) bundle.getSerializable("produit");
            ImageView coverImage = (ImageView) v.findViewById(R.id.coverImage);
            TextView textSummary = (TextView) v.findViewById(R.id.description);
            TextView textYear = (TextView) v.findViewById(R.id.yearText);
            TextView textMarque = (TextView) v.findViewById(R.id.textMarque);
            TextView textModele = (TextView) v.findViewById(R.id.textNom);
            ((TextView) v.findViewById(R.id.textView)).setVisibility(v.VISIBLE);

            coverImage.setImageBitmap(new UtilService().getImageByte(produit.getImage()));
            textSummary.setText(produit.getDescription());
            textYear.setText(  "Année   : " + produit.getAnneeFab());
            textMarque.setText("Marque : "+produit.getMarque());
            textModele.setText("Nom    : "+produit.getNom());

            Button addBtn = (Button) v.findViewById(R.id.addpanier);
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!containsProduit(produit.getFilename())) {
                        MainActivity.addProdPanier(getActivity(), produit);
                    }else {
                        Toast.makeText(getActivity(), "Produit déja ajouté !!",
                                Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
            Button retBtn = (Button) v.findViewById(R.id.addpanierRet);

        Configuration config = getResources().getConfiguration();
        if ((config.orientation ==
                Configuration.ORIENTATION_LANDSCAPE)
                &&(config.smallestScreenWidthDp>600)) {
            ViewGroup layout = (ViewGroup) retBtn.getParent();
            if (null != layout) //en cas de onClick
                layout.removeView(retBtn);
        }else {
            retBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //do something
                    getActivity().finish();
                }
            });
        }
        return v;
    }
    public boolean containsProduit(String filename) {
        for(int i= 0 ; i<MainActivity.produitPaniers.size();i++) {
            ProduitPanier o = MainActivity.produitPaniers.get(i);
            System.out.println(o.getFilename());
            if(o != null && o.getFilename().equals(filename)) {
                return true;
            }
        }
        return false;
    }
}
