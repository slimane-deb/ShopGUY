package sd.shopguy.Frags;

/**
 * Created by Slimane on 25/03/2016.
 */


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import sd.shopguy.Adapts.PagerAdapter;
import sd.shopguy.Adapts.ProdListAdapter;
import sd.shopguy.Main.DetailActivity;
import sd.shopguy.Main.R;
import sd.shopguy.Metier.ClassProduit;
import sd.shopguy.Metier.Produit;
import sd.shopguy.Task.AsyncResponse;
import sd.shopguy.Task.GetProduitTask;
import sd.shopguy.Util.UtilService;


public class AffichFragment extends Fragment {
    private ProdListAdapter myAdapter ;
    private List<Produit> ProduitList = new ArrayList<Produit>() ;
    ListView listView ;
    //---------------------------------

    View view ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.affich_layout,null);

        if(new UtilService().checkNetwork(getContext())) {
            Bundle bundle = getArguments();
            if(bundle!=null) {
                String classe =  bundle.getString("classe");
                String client =  bundle.getString("client");

                new GetProduitTask(getContext()).execute(getScreenDensity(),classe,client);
            }

        } else {
            Toast.makeText(getContext(),"Aucune connexion", Toast.LENGTH_SHORT).show();
        }

        return view;
    }


/*
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





    }
*/

    public void showView (Produit p) {
        //isLandView
        View v  = getActivity().findViewById(R.id.frameLayout);
        if (v!=null) {
// si le layout d'affichage (ContainerView) de detail existe

            DetailFragment detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("produit", p);
            detailFragment.setArguments(bundle);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, detailFragment);

            ft.commit();

        } else {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("produit", p);
            startActivity(intent);
        }
    }
    public String getScreenDensity() {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        String density ="";
        switch(metrics.densityDpi){
            case DisplayMetrics.DENSITY_LOW:
                density="ldpi";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                density= "mdpi";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                density="hdpi";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                density= "xhdpi";
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                density= "xxhdpi";
            case DisplayMetrics.DENSITY_XXXHIGH:
                density= "xxxhdpi";
                break;
        }

        return density;

    }

    private void initListProds() {
        Bundle bundle = getArguments();
        if(bundle!=null){
            ProduitList=(ArrayList<Produit>)bundle.get("prods");
            myAdapter=new ProdListAdapter(getActivity(),ProduitList);

        }
    }





    public void setProduitList(ArrayList<Produit> produitList) {
        ProduitList = produitList;
    }

    public ProdListAdapter getMyAdapter() {
        return myAdapter;
    }

    public void setMyAdapter(ProdListAdapter myAdapter) {
        this.myAdapter = myAdapter;
    }


    //*********************************
    public void updateProductList(List<Produit> products){
        if(myAdapter!=null) {

            myAdapter.getProduitList().clear();
            this.ProduitList = products;
            myAdapter.setProduitList(products);
            listView.removeAllViewsInLayout();
            //listView.removeAllViews();
            //listView.removeViewAt(0);
            myAdapter.notifyDataSetChanged();
        }

    }
    /*@Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("d", ProduitList);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            ProduitList = (ArrayList<Produit>) savedInstanceState.getSerializable("d");
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View v = inflater.inflate(R.layout.affich_layout, null);
            ProdListAdapter adapter = new ProdListAdapter(getContext(), ProduitList);
            ListView listView = (ListView) v.findViewById(R.id.listView);
            listView.setAdapter(adapter);
            listView.onRestoreInstanceState(savedInstanceState);
        }
        //listView.onRestoreInstanceState(savedInstanceState);
    }*/


    public class GetProduitTask extends AsyncTask<String,Void,String> implements AsyncResponse {



        //----
        private Context context;
        ProgressDialog pd;

        public AsyncResponse delegate = null;

        public GetProduitTask(Context context) {
            this.context = context;
        }

        public GetProduitTask (Context context , AsyncResponse delegate) {

            this.context = context;
            this.delegate = delegate;
        }


        public ProgressDialog getPd() {
            return pd;
        }

        public void setPd(ProgressDialog pd) {
            this.pd = pd;
        }

        @Override
        public void onPreExecute() {
            // Création et affichage du ProgressDialog
            pd = new ProgressDialog(context);
            pd.setTitle("Patientez SVP..");
            pd.setMessage("Chargement ...");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            StringBuilder result = new StringBuilder();
            String data;
            try {
                URL url = new URL("http://192.168.173.1:8080/getproduits?density="+params[0]+"&classproduit="+params[1]+"&typeclient="+params[2]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                // Attendre 5 secondes max pour établir la connexion
                conn.setConnectTimeout(5000);
                // Attendre 1 minute max pour lire les données
                conn.setReadTimeout(60000);
                if (conn.getResponseCode()==200) {
               /* pd = new ProgressDialog(context);
                pd.setTitle("Please Wait..");
                pd.setMessage("Loading...");
                pd.show();*/
                    InputStream is = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    while ((data = reader.readLine()) != null) {
                        result.append(data);
                    }

                }
                else {
                    int code = conn.getResponseCode();
                    System.out.println(code);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
            if (!s.equals("")) {


                try {
                    JSONArray jsonArray = new JSONArray(s);
                    Produit[] produits = new Gson().fromJson(s, Produit[].class);
                    ProduitList = Arrays.asList(produits);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            listView = (ListView) view.findViewById(R.id.listView);
            myAdapter = new ProdListAdapter(context,ProduitList);
            if (listView !=null )listView.setAdapter(myAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        showView(ProduitList.get(position));
                    }
                });




            }
            else {
                Toast.makeText(context, "Une erreur s'est produite", Toast.LENGTH_SHORT).show();
            }
        }


        @Override
        public void processFinish(ArrayList<Produit> produits) {
            ViewPager viewPager = (ViewPager) ((AppCompatActivity)context).findViewById(R.id.viewpager);
            PagerAdapter pagerAdapter = new PagerAdapter(
                    ((AppCompatActivity)context).getSupportFragmentManager() ) ;


        }
    }


}


