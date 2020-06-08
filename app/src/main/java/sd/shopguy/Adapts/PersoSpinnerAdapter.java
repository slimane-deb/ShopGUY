package sd.shopguy.Adapts;

/**
 * Created by Slimane on 27/03/2016.
 */

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import sd.shopguy.Main.R;

public class PersoSpinnerAdapter extends ArrayAdapter<String> {

    private Context context1;
    private ArrayList<String> data;
    public Resources res;
    LayoutInflater inflater;

    public PersoSpinnerAdapter(Context context, ArrayList<String> objects) {
        super(context, R.layout.spinner_row, objects);
        context1 = context;
        data = objects;
        inflater = (LayoutInflater) context1
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    // appele pour chaque ligne( Appelée data.size() fois )
    public View getCustomView(int position, View convertView, ViewGroup parent) {

        View row = inflater.inflate(R.layout.spinner_row, parent, false);

        TextView tvCategory = (TextView) row.findViewById(R.id.tvCategory);

        tvCategory.setText(data.get(position).toString());
        ImageView icon=(ImageView)row.findViewById(R.id.image);
        switch (position) {
            case 0:
                icon.setImageResource(R.drawable.ic_action_black_white_android_t_shirt);
                break;

            case 1:
                icon.setImageResource(R.drawable.ic_pants);
                break;
            case 2:
                icon.setImageResource(R.drawable.ic_action_clothing_shoe_man_icon);
                break;
            case 3:
                icon.setImageResource(R.drawable.ic_action_clothing_coat_icon);
                break;
            case 4:
                icon.setImageResource(R.drawable.ic_action_mobile_headset_icon);
                break;

        }
        return row;
    }
}