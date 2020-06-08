package sd.shopguy.Adapts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import sd.shopguy.Main.PanierActivity;
import sd.shopguy.Main.R;
import sd.shopguy.Metier.ProduitPanier;
import sd.shopguy.Util.UtilService;

/**
 * Created by Slimane on 17/04/2016.
 */
public class PanierListAdapter extends BaseAdapter {

    private ArrayList<ProduitPanier> panierArrayList;
    private Context context;


    public PanierListAdapter(Context context, ArrayList<ProduitPanier> panierArrayList) {

        this.context = context;
        this.panierArrayList = panierArrayList;

    }

    public void removePanierArrayList() {
        this.panierArrayList.clear();
    }

    @Override
    public int getCount() {
        return panierArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return panierArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

                View v = inflateViewPanier(convertView);
                ImageView coverIcon = (ImageView) v.findViewById(R.id.coverIconPanier);
                final ProduitPanier pp = panierArrayList.get(position);

                TextView textMarque = (TextView) v.findViewById(R.id.marque);
                TextView textModele = (TextView) v.findViewById(R.id.modele);
                TextView textPrix = (TextView) v.findViewById(R.id.prix);
                TextView textQuant = (TextView) v.findViewById(R.id.quantity);

                coverIcon.setImageBitmap(new UtilService().getImageByte(pp.getImage()));

                textMarque.setText("Marque : " + pp.getMarque());
                textModele.setText("Modele : " + pp.getNom());
                textPrix.setText("Prix       : " + pp.getPrix() + " DA");
                textQuant.setText("Quantité : " + pp.getQuantityPanier());
                setButtonsActions(v,position,pp,textQuant);


            return v;
        }
    private View inflateViewPanier(View convertView) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.element_panier, null);
        }
        return view;
    }

    public void setButtonsActions(View view, final int position, final ProduitPanier pp, final TextView tvQuantity) {
        Button plusBtn ,minusBtn;

        //Incement quantity
        plusBtn= (Button)view.findViewById(R.id.plusQuant);
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pp.getQuantityPanier()<pp.getQuantite()) {
                    pp.setQuantityPanier(pp.getQuantityPanier() + 1);
                    tvQuantity.setText("Quantité : " + pp.getQuantityPanier() + "");
                    TextView to = (TextView) ((PanierActivity) context).findViewById(R.id.textViewTotal);
                    Double prix = Double.parseDouble(to.getText().toString()) + pp.getPrix();
                    to.setText(prix.toString());
                }else Toast.makeText(context, "Limite de Stock", Toast.LENGTH_SHORT).show();
            }
        });
        //Decrement quantity
        minusBtn = (Button)view.findViewById(R.id.minusQuant);
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pp.decQuantity();
                TextView to = (TextView )((PanierActivity )context).findViewById(R.id.textViewTotal);
                Double prix = Double.parseDouble(to.getText().toString())-pp.getPrix();
                to.setText(prix.toString());
                if(pp.getQuantityPanier()==0){
                    panierArrayList.remove(position);
                    notifyDataSetChanged();
                    return;

                }
                tvQuantity.setText("Quantité : " +pp.getQuantityPanier() + "");

            }
        });
    }

}
