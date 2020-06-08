package sd.shopguy.Service;

import android.content.Context;

import java.util.ArrayList;

import sd.shopguy.Metier.Command;


/**
 * Created by ProUser on 27/06/2016.
 */
public interface ICommandStorage {
    void saveCommand(Command cmd) ;
    ArrayList<Command> restoreCommands(Context context) ;
    void removeCommand(); // cancel command ==> vider le panier stocké en local
    void commitCommmand();
    void rollbackCommand();
}
