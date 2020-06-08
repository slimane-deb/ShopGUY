package sd.shopguy.Main;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;

import sd.shopguy.Adapts.CommandListAdapter;
import sd.shopguy.Metier.Command;

public class CommandActivity extends AppCompatActivity {

    private ArrayList<Command> commands ;
    private CommandListAdapter commandAdapter ;
    private ListView listView ;
    public static int nbCmd = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCMD);
        toolbar.setTitle("Mes Commandes");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //initCommands();
        commands = getStoredCommands(this);
        listView = (ListView) findViewById(R.id.listViewCMD);

        commandAdapter = new CommandListAdapter(getApplicationContext(),commands );
        listView.setAdapter(commandAdapter);
    }

    public ArrayList<Command> getStoredCommands(Context context) {
        ArrayList<Command> Coms = new ArrayList<>();
        SharedPreferences sharedPreferences = (context).getSharedPreferences("my_prefs_cmd", Activity.MODE_PRIVATE);
        int size = sharedPreferences.getInt("nbCommand", -1);
        //SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i = 1; i <= size; i++) {
            Gson gson = new Gson();
            String json = sharedPreferences.getString("MyCommand" + i, "");
            Command c = gson.fromJson(json, Command.class);
            Coms.add(c);
            //sharedPreferences.edit().remove("MyCommand"+i).commit();
        }
        //Coms.addAll((MainActivity.commands));
        /*if(CommandActivity.nbCmd>0){
            CommandActivity.nbCmd -- ;
            sharedPreferences.edit().putInt("nbCommand",CommandActivity.nbCmd).apply();
        }*/

        return Coms;
    }
}
