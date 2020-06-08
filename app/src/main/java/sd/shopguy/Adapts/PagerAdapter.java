package sd.shopguy.Adapts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import sd.shopguy.Frags.AffichFragment;
import sd.shopguy.Metier.Produit;

/**
 * Created by Slimane on 04/04/2016.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    private AffichFragment hFragment;
    private AffichFragment fFragment;
    private AffichFragment eFragment;
    String classe ;


    public PagerAdapter(FragmentManager fm) {
        super(fm);
        //this.fragmentManager=fragmentManager;
        //this.listner=listner;
        //separByClient(list);
    }


    //retourne fragment selon Position .
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:

                hFragment = createClientFragment("Homme");
                return hFragment;

            case 1:

                fFragment = createClientFragment("Femme");
                return fFragment;


            case 2:
                eFragment = createClientFragment("Enfant");
                return eFragment;
            default:
                return null;
        }
    }

    private AffichFragment createClientFragment(String client) {
        AffichFragment fragment = new AffichFragment();
        Bundle bundle = new Bundle();

        bundle.putString("client",client);
        bundle.putString("classe",classe);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Homme";
            case 1:
                return "Femme";
            case 2:
                return "Enfant";
            default:
                return null;
        }

    }


    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void separByCategory(int position) {
        switch (position) {
            case 0:
                classe = "Tricot";
                break;
            case 1:
                classe = "Pantalon";
                break;
            case 2:
                classe = "Chaussure";
                break;
            case 3:
                classe = "Veste";
                break;
            case 4:
                classe = "Divers";
                break;
        }
    }
}
