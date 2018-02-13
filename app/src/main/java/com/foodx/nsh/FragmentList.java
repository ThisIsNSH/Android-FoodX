package com.foodx.nsh;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ThisIsNSH on 2/13/2018.
 */

public class FragmentList extends ArrayAdapter<String> {

    private final Activity context;
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<Integer> total= new ArrayList<Integer>();
    ArrayList<String> address= new ArrayList<String>();
    ArrayList<String> mobile= new ArrayList<String>();

    public FragmentList(Activity context,
                        ArrayList<String> name, ArrayList<Integer> total, ArrayList<String> address, ArrayList<String> mobile) {
        super(context, R.layout.list_fragment, name );
        this.context = context;
        this.name = name;
        this.address= address;
        this.mobile= mobile;
        this.total= total;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView= inflater.inflate(R.layout.list_fragment, null, true);

        TextView order123 =rowView.findViewById(R.id.ordersql);
        TextView total23 = rowView.findViewById(R.id.totalsql);
        TextView address123 = rowView.findViewById(R.id.addsql);
        TextView mobile123 = rowView.findViewById(R.id.mobsql);

        order123.setText(name.get(position));
        total23.setText(Integer.toString(total.get(position)
        ));
        address123.setText(address.get(position));
        mobile123.setText(mobile.get(position));
        return rowView;
    }
}