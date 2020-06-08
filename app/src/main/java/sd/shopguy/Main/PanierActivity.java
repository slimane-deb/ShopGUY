package sd.shopguy.Main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sd.shopguy.Adapts.PanierListAdapter;
import sd.shopguy.Metier.Command;
import sd.shopguy.Metier.ProduitPanier;
import sd.shopguy.Service.AlarmService;
import sd.shopguy.Service.IPanierStorage;
import sd.shopguy.Task.AddCommandTask;
import sd.shopguy.Task.UpdateProduitTask;

public class PanierActivity extends AppCompatActivity implements IPanierStorage{

    private ArrayList<ProduitPanier> panier ;
    private PanierListAdapter panierListAdapter ;
    public static  int nbPanier = 0 ; // nombre de panier sauvgardé

    TextView textTotal ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPanier);
        toolbar.setTitle("Mon Panier");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initPanier();

        ListView listView = (ListView) findViewById(R.id.listViewPanier);
        panier =MainActivity.produitPaniers ;
        panierListAdapter = new PanierListAdapter(this,panier);
        listView.setAdapter(panierListAdapter);

        textTotal = (TextView) findViewById(R.id.textViewTotal);
        textTotal.setText(getMontant().toString());
        setButtonsActions();



    }

    public void setButtonsActions() {
        Button passBtn ,clearBtn;

        passBtn= (Button) findViewById(R.id.addCMD);
        clearBtn = (Button) findViewById(R.id.cancelCMD);

        passBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(panier.size()>0) {

                    Command cmd = new Command(CommandActivity.nbCmd ,new Date(),false,getMontant());
                    new AddCommandTask().execute(String.valueOf(cmd.getNumero()),cmd.getDate().toString(),cmd.getEatText(),
                            cmd.getMontant().toString().toString(),"");

                    MainActivity.commands.add(cmd);
                    /*Intent intent = new Intent(getBaseContext(), CommandActivity.class);
                    intent.putExtra("Commands", MainActivity.commands);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);*/
                    savePanier(MainActivity.produitPaniers); // sauv panier en local
                    commitPanier(); // maj la bdd
                    saveCommand(cmd); // sauv cmd en local
                    MainActivity.produitPaniers.clear();
                    new AlarmService().launchAlarmOnce(getApplicationContext());
                    Toast.makeText(getApplicationContext(), "Commande ajoutée passez au CheckOut",
                            Toast.LENGTH_LONG).show();
                    finish();


                }else
                    Toast.makeText(getApplicationContext(), "Panier vide pas de commande !!",
                            Toast.LENGTH_LONG).show();
            }
        });



            clearBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(panier.size()>0) {
                    new AlertDialog.Builder(v.getContext())
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Confirmer SVP")
                            .setMessage("Etes vous sure de vouloir vider le panier")
                            .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(), "Panier vidé",
                                            Toast.LENGTH_LONG).show();
                                    panier.clear();
                                    MainActivity.produitPaniers.clear();
                                    textTotal.setText("");
                                    panierListAdapter.removePanierArrayList();
                                    panierListAdapter.notifyDataSetChanged();
                                }

                            })
                            .setNegativeButton("Non", null)
                            .show();
                    }
                    else Toast.makeText(getApplicationContext(), "Panier vide rien a faire !!",
                            Toast.LENGTH_LONG).show();
                }
            });


    }

    public void commitPanier() {
        for (int i= 0;i<panier.size();i++){
            Integer q = panier.get(i).getQuantite() -panier.get(i).getQuantityPanier();
            new UpdateProduitTask().execute(q.toString(),panier.get(i).getFilename());
        }
    }

    @Override
    public void rollbackPanier() {

    }

    private Double getMontant() {
        double sum = 0 ;
        for (int i=0 ; i<panier.size();i++)
        {
            sum += panier.get(i).getPrix() ;
        }
        return sum ;
    }
    private int getNbArticle(){
        int sum = 0 ;
        for (int i=0 ; i<panier.size();i++)
        {
            sum += panier.get(i).getQuantityPanier() ;
        }
        return sum ;
    }


    public void initPanier(){

        panier = MainActivity.produitPaniers ;


    }
    @Override
    public void savePanier(ArrayList<ProduitPanier> pp){
        SharedPreferences sp = getSharedPreferences("my_prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(pp);
        //editor.putString("username",userName);
        PanierActivity.nbPanier ++ ;
        editor.putInt("nbPanier",nbPanier);
        editor.putString("MyPanier"+nbPanier,json);

        editor.commit();
    }
    @Override
    public ArrayList<ProduitPanier> restorePanier(Context context){
        ArrayList<ProduitPanier> pps = new ArrayList<>();
        SharedPreferences sharedPreferences  = getSharedPreferences("my_prefs", Activity.MODE_PRIVATE);
        int size  = sharedPreferences.getInt("nbPanier",-1);
        //SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i =1 ; i<=size;i++){
            Gson gson = new Gson();
            String json = sharedPreferences.getString("MyPanier"+i,"" );
            ArrayList<ProduitPanier> pp  = gson.fromJson(json, new TypeToken<List<ProduitPanier>>(){}.getType());
            pps.addAll(pp);
        }
        pps.addAll((MainActivity.produitPaniers));
        if(PanierActivity.nbPanier>0){
            PanierActivity.nbPanier -- ;
            sharedPreferences.edit().putInt("nbPanier",PanierActivity.nbPanier).apply();
        }
        return  pps ;

    }


    public void saveCommand(Command  cmd) {
        SharedPreferences sp = getSharedPreferences("my_prefs_cmd", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cmd);
        //editor.putString("username",userName);
        CommandActivity.nbCmd ++ ;
        editor.putInt("nbCommand",CommandActivity.nbCmd);
        editor.putString("MyCommand"+CommandActivity.nbCmd,json);

        editor.commit();
    }


}

