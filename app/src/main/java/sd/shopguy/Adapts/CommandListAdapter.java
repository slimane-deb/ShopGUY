package sd.shopguy.Adapts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import sd.shopguy.Main.CommandActivity;
import sd.shopguy.Main.LoginActivity;
import sd.shopguy.Main.MainActivity;
import sd.shopguy.Main.PanierActivity;
import sd.shopguy.Main.R;
import sd.shopguy.Metier.Command;
import sd.shopguy.Service.AlarmService;
import sd.shopguy.Service.ICommandStorage;
import sd.shopguy.Task.AddCommandTask;

/**
 * Created by Slimane on 15/04/2016.
 */
public class CommandListAdapter extends BaseAdapter  implements ICommandStorage{

    private ArrayList<Command> commandsList;
    private Context context;
    TextView textEtat ;


    public CommandListAdapter(Context context, ArrayList<Command> commandsList) {

        this.context = context;
        this.commandsList = commandsList;
    }

    @Override
    public int getCount() {
        return commandsList.size();
    }

    @Override
    public Object getItem(int position) {
        return commandsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        final Command cmd = commandsList.get(position);
        View view = inflateViewCMD(convertView ,cmd);
        affichCMDitems(view, cmd);
        setButtonsActions(position, view);



        return view;
    }

    private View inflateViewCMD(View convertView,Command cmd) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.element_cmd, null);


        }
        return view;
    }

    private void affichCMDitems(View view, Command cmd) {
        TextView textNum = (TextView) view.findViewById(R.id.textNumCMD);
        TextView textDate = (TextView) view.findViewById(R.id.textDateCMD);
        TextView textMontant = (TextView) view.findViewById(R.id.textMontant);
        textEtat = (TextView) view.findViewById(R.id.textEtatCMD);
        textNum.setText("Commande N°: " + cmd.getNumero());
        textDate.setText("Date     : " + cmd.getDate());
        textMontant.setText("Montant       : " + cmd.getMontant() + " DA");
        boolean etat = cmd.getEtat();
        if (etat) {
            textEtat.setText("Etat     :Validée");
            textEtat.setBackgroundColor(Color.GREEN);
        } else {
            textEtat.setText("Etat     :En cours");
            textEtat.setBackgroundColor(Color.RED);
        }


    }

    public void setButtonsActions(final int position, final View view) {
        final Button paiesBtn, suppBtn;

        paiesBtn = (Button) view.findViewById(R.id.paieCMD);
        ViewGroup layout = (ViewGroup) paiesBtn.getParent();
        if (null != layout && commandsList.get(position).getEtat())
            paiesBtn.setVisibility(View.INVISIBLE);

        else {
            paiesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //do something

                    if (MainActivity.login) {
                        suppCommand(commandsList.get(position));
                        commandsList.get(position).setEtat(true);
                        commandsList.get(position).setIdClient(LoginActivity.client.getEmail());
                        saveCommand(commandsList.get(position));
                        //removeCommand();
                        //CommandActivity.nbCmd ++ ;
                        new AlarmService().cancelAlarm(context);

                        MainActivity.produitPaniers.clear();
                        Toast.makeText(v.getContext(), "Commande Validée",
                                Toast.LENGTH_LONG).show();
                        TextView textEtat = (TextView) view.findViewById(R.id.textEtatCMD);
                        textEtat.setText("Etat     :Validée");
                        textEtat.setBackgroundColor(Color.GREEN);
                        v.setVisibility(View.INVISIBLE);
                        //new AddCommandTask().execute()

                        new AlarmService().synchroCommand(context);
                    } else {
                        Intent intent = new Intent(v.getContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });
        }

        suppBtn = (Button) view.findViewById(R.id.suppCMD);
        suppBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(v.getRootView().getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Confirmer Commande")
                        .setMessage("Etes vous sure de vouloir supprimer la commande")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new AlarmService().cancelAlarm(context);
                                removeCommand();
                                suppCommand(commandsList.get(position));
                                commandsList.remove(position);

                                notifyDataSetChanged();


                            }

                        })
                        .setNegativeButton("Non", null)
                        .show();

            }
        });


    }

    @Override
    public void saveCommand(Command  cmd) {
        SharedPreferences sp = context.getSharedPreferences("my_prefs_cmd", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cmd);
        //editor.putString("username",userName);
        CommandActivity.nbCmd ++ ;
        editor.putInt("nbCommand",CommandActivity.nbCmd);
        editor.putString("MyCommand"+CommandActivity.nbCmd,json);

        editor.commit();
    }

    @Override
    public ArrayList<Command> restoreCommands(Context context) {
        return null;
    }

    @Override
    public void removeCommand() {

            SharedPreferences sharedPreferences  = context.getSharedPreferences("my_prefs", Activity.MODE_PRIVATE);
            int size  = sharedPreferences.getInt("nbPanier",-1);
            //SharedPreferences.Editor editor = sharedPreferences.edit();
            for (int i =1 ; i<=size;i++){
                //ArrayList<ProduitPanier> pp  = gson.fromJson(json, new TypeToken<List<ProduitPanier>>(){}.getType());
                sharedPreferences.edit().remove("MyPanier"+i).commit();
            }

            MainActivity.produitPaniers.clear();
            if(PanierActivity.nbPanier>0){
                PanierActivity.nbPanier -- ;
                sharedPreferences.edit().putInt("nbPanier",0).apply();
            }

    }

    @Override
    public void commitCommmand() {

    }

    @Override
    public void rollbackCommand() {

    }

    public void suppCommand(Command cmd){
        //ArrayList<Command> Coms = new ArrayList<>();
        SharedPreferences sharedPreferences  = (context).getSharedPreferences("my_prefs_cmd", Activity.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPreferences.edit();

            sharedPreferences.edit().remove("MyCommand"+ cmd.getNumero()).commit();
        if(CommandActivity.nbCmd>0){
            CommandActivity.nbCmd -- ;
            sharedPreferences.edit().putInt("nbCommand",CommandActivity.nbCmd).apply();
        }

    }
}
