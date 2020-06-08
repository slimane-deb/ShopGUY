package sd.shopguy.Service;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import sd.shopguy.Main.MainActivity;
import sd.shopguy.Main.PanierActivity;
import sd.shopguy.Main.R;
import sd.shopguy.Metier.ProduitPanier;
import sd.shopguy.Task.UpdateProduitTask;

/**
 * Created by amine on 23/05/2016.
 */
public class BroadcastService extends BroadcastReceiver implements IPanierStorage {
    ArrayList<ProduitPanier> produitPaniers = new ArrayList<>();
    @Override
    public void onReceive(Context context, Intent intent) {
        produitPaniers = restorePanier(context);
        rollbackPanier();
        sendNotification("Votre commande dans ShopGUY a expiré",context);
    }

    private void sendNotification(String message,Context ctx) {

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(ctx)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notification")
                .setContentText(message)
                .setAutoCancel(false)
                .setSound(defaultSoundUri);

        NotificationManager notificationManager =
                (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 , notificationBuilder.build());
    }

    @Override
    public void savePanier(ArrayList<ProduitPanier> pp) {

    }

    @Override
    public ArrayList<ProduitPanier> restorePanier(Context context) {
        ArrayList<ProduitPanier> pps = new ArrayList<>();
        SharedPreferences sharedPreferences  = (context).getSharedPreferences("my_prefs", Activity.MODE_PRIVATE);
        int size  = sharedPreferences.getInt("nbPanier",-1);
        //SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i =1 ; i<=size;i++){
            Gson gson = new Gson();
            String json = sharedPreferences.getString("MyPanier"+i,"" );
            ArrayList<ProduitPanier> pp  = gson.fromJson(json, new TypeToken<List<ProduitPanier>>(){}.getType());
            pps.addAll(pp) ;
            sharedPreferences.edit().remove("MyPanier"+i).commit();
        }

      /*  ArrayList<ProduitPanier> pps = new ArrayList<>((MainActivity.produitPaniers));
        pps.addAll(produitPaniers);*/
        MainActivity.produitPaniers.clear();
        //sharedPreferences.edit().remove("nbPanier").commit();
        if(PanierActivity.nbPanier>0){
            PanierActivity.nbPanier -- ;
            sharedPreferences.edit().putInt("nbPanier",0).apply();
        }

        return  pps ;
    }

    @Override
    public void commitPanier() {

    }

    @Override
    public void rollbackPanier() {
        for (int i= 0;i<produitPaniers.size();i++){
            Integer q = produitPaniers.get(i).getQuantite() ;
            new UpdateProduitTask().execute(q.toString(),produitPaniers.get(i).getFilename());
        }

    }

}
