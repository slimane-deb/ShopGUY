package sd.shopguy.Task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import sd.shopguy.Adapts.PagerAdapter;
import sd.shopguy.Main.R;
import sd.shopguy.Metier.ClassProduit;
import sd.shopguy.Metier.Produit;

public class GetProduitTask extends AsyncTask<String,Void,String> implements AsyncResponse {



    //----
    private Context context;
    ArrayList<Produit> listProduit ;
    ProgressDialog pd;

    public AsyncResponse delegate = null;

    public GetProduitTask(Context context) {
        this.context = context;
    }

    public GetProduitTask (Context context , AsyncResponse delegate) {

        this.context = context;
        this.delegate = delegate;
    }

    public  ArrayList<Produit> getListProduit() {
        return listProduit;
    }

    public  void setListProduit(ArrayList<Produit> listProduit) {
        this.listProduit = listProduit;
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
        pd.setTitle("Please Wait..");
        pd.setMessage("Loading...");
        pd.show();
    }

    @Override
    protected String doInBackground(String... params) {
        StringBuilder result = new StringBuilder();
        String data;
        try {
            URL url = new URL("http://192.168.173.1:8080/getproduits?density="+params[0]);
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
            listProduit = new ArrayList<>();


            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Produit produit = new Produit();
                    produit.setNom(jsonObject.get("nom").toString());
                    produit.setMarque(jsonObject.get("marque").toString());
                    produit.setAnneeFab(jsonObject.getInt("anneeFab"));
                    produit.setPrix(jsonObject.getInt("prix"));
                    produit.setClassProduit(ClassProduit.valueOf(jsonObject.getString("classProduit")));
                    produit.setTypeClient(jsonObject.getString("typeClient"));
                    produit.setDescription(jsonObject.get("description").toString());
                    produit.setImage(jsonObject.getString("image"));

                    listProduit.add(produit);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


           /* ListView listView = (ListView) ((AppCompatActivity)context).findViewById(R.id.listView);
            ProdListAdapter cutomAdapter = new ProdListAdapter(context,listProduit);
            if (listView !=null )listView.setAdapter(cutomAdapter);
*/
/*            ViewPager viewPager = (ViewPager) ((AppCompatActivity)context).findViewById(R.id.viewpager);
            PagerAdapter pagerAdapter = new PagerAdapter(
                    ((AppCompatActivity)context).getSupportFragmentManager(),listProduit ) ;
            viewPager.setAdapter(pagerAdapter);
            TabLayout tabLayout = (TabLayout) ((AppCompatActivity)context).findViewById(R.id.tabs);
            //tabLayout.setVisibility(View.VISIBLE);
            tabLayout.setupWithViewPager(viewPager);*/

            //delegate.processFinish(listProduit);



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

