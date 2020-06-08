package sd.shopguy.Adapts;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sd.shopguy.Main.MainActivity;
import sd.shopguy.Main.R;
import sd.shopguy.Metier.Produit;
import sd.shopguy.Metier.ProduitPanier;
import sd.shopguy.Util.UtilService;

/**
 * Created by Slimane on 31/03/2016.
 */
public class ProdListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Produit> produitList;
    //private List<ProduitPanier> produitPaniers =new ArrayList<ProduitPanier>()  ;

/*    // Pour le fitre
    private ValueFilter valueFilter;
    private List<Produit> mFilterList;*/

    public ProdListAdapter(Context context, List<Produit> produitList) {
        this.context = context;
        this.produitList = new ArrayList<>(produitList);
        int k = 2 ;
        //this.mFilterList = produitList;
        //valueFilter = new ValueFilter();


    }

    @Override
    public int getCount() {
        return produitList.size();
    }

    @Override
    public Object getItem(int position) {
        return produitList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        if (convertView == null) {

            final Produit p = produitList.get(position);
            if (p.getQuantite()>0){
                LayoutInflater inflater;
                inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.element_list, null);

                TextView textMarque = (TextView) convertView.findViewById(R.id.marque);
                TextView textModele = (TextView) convertView.findViewById(R.id.nom);
                TextView textPrix = (TextView) convertView.findViewById(R.id.prix);
                Button addBtn;
                ImageView coverIcon = (ImageView) convertView.findViewById(R.id.coverIcon);
                // convertir en bitmap
                Bitmap bm = new UtilService().getImageByte(produitList.get(position).getImage());
                coverIcon.setImageBitmap(bm);
          /*  if (bm != null && !bm.isRecycled()) {
                bm.recycle();
                bm = null;
            }*/


                textModele.setText("Nom    : " + p.getNom());
                textMarque.setText("Marque : " + p.getMarque());
                textPrix.setText("Prix       : " + p.getPrix() + " DA");

                addBtn = (Button) convertView.findViewById(R.id.addpanier);
                Configuration config = context.getResources().getConfiguration();
                if ((config.orientation ==
                        Configuration.ORIENTATION_LANDSCAPE)
                        && (config.smallestScreenWidthDp > 600)) {
                    ViewGroup layout = (ViewGroup) addBtn.getParent();
                    if (null != layout) //en cas de onClick
                        layout.removeView(addBtn);
                } else {
                    addBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //do something

                            if(!containsProduit(p.getFilename())) {
                            MainActivity.addProdPanier(context, p);
                            }else {
                                Toast.makeText(context, "Produit déja ajouté !!",
                                        Toast.LENGTH_LONG).show();

                            }

                        }
                    });
                }

        }
}

        return convertView;
    }

    public List<Produit> getProduitList() {
        return produitList;
    }

    public void setProduitList(List<Produit> produitList) {
        this.produitList = new ArrayList<>(produitList);
    }

    public boolean containsProduit(String filename) {
        for(int i= 0 ; i<MainActivity.produitPaniers.size();i++) {
            ProduitPanier  o = MainActivity.produitPaniers.get(i);
            System.out.println(o.getFilename());
            if(o != null && o.getFilename().equals(filename)) {
                return true;
            }
        }
        return false;
    }
}



