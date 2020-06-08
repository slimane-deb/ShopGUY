package sd.shopguy.Service;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import sd.shopguy.Main.R;
import sd.shopguy.Metier.Promotion;
import sd.shopguy.Task.GetPromoTask;
import sd.shopguy.Util.UtilService;

/**
 * Created by ProUser on 27/06/2016.
 */
public class PushNotificationService extends BroadcastReceiver {
    List<Promotion> promos = new ArrayList<>();
    @Override
    public void onReceive(Context context, Intent intent) {

        Date today = new Date();
        //if (today.before())
        if(new UtilService().checkNetwork(context)) {
            GetPromoTask promoTask = new GetPromoTask() ;

            try {

                String data = promoTask.execute().get();
                Promotion[] promotions = new Gson().fromJson(data,Promotion[].class);
                promos = Arrays.asList(promotions);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
        else {
            Toast.makeText(context,"Aucune connexion", Toast.LENGTH_SHORT).show();
        }

        for(Promotion p :promos){
            if (p.getDate().after(today))
                sendNotification("ShopGUY promotion : "+p.getDescription(),context);
        }


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
}
