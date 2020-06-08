package sd.shopguy.Service;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sd.shopguy.Main.LoginActivity;
import sd.shopguy.Metier.Command;
import sd.shopguy.Task.AddCommandTask;
import sd.shopguy.Task.UpdateCommandTask;
import sd.shopguy.Task.UpdateProduitTask;

/**
 * Created by ProUser on 27/06/2016.
 */
public class SyncService  extends BroadcastReceiver implements ICommandStorage{
    ArrayList<Command> commands = new ArrayList<>();

    @Override
    public void onReceive(Context context, Intent intent) {
        commands = restoreCommands(context);
        commitCommmand();

    }

    @Override
    public void saveCommand(Command cmd) {

    }

    @Override
    public ArrayList<Command> restoreCommands(Context context) {
        ArrayList<Command> Coms = new ArrayList<>();
        SharedPreferences sharedPreferences  = (context).getSharedPreferences("my_prefs_cmd", Activity.MODE_PRIVATE);
        int size  = sharedPreferences.getInt("nbCommand",-1);
        //SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i =1 ; i<=size;i++){
            Gson gson = new Gson();
            String json = sharedPreferences.getString("MyCommand"+i,"" );
            Command c  = gson.fromJson(json,Command.class);
            Coms.add(c);
            //sharedPreferences.edit().remove("MyCommand"+i).commit();
        }



        return  Coms ;
    }

    @Override
    public void removeCommand() {

    }

    @Override
    public void commitCommmand() {
        for (int i= 0;i<commands.size();i++){
            Command q = commands.get(i) ;
            new UpdateCommandTask().execute(q.getEatText(), LoginActivity.client.getEmail());

        }
    }

    @Override
    public void rollbackCommand() {

    }
}
