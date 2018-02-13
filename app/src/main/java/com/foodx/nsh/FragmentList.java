package com.foodx.nsh;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ThisIsNSH on 2/13/2018.
 */

public class FragmentList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] name;
    private final String[] address;
    private final String[] mobile;
    private final Integer[] total;

    public FragmentList(Activity context,
                        String[] name, Integer[] total, String[]  address, String[]  mobile) {
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

        order123.setText(name[position]);
        total23.setText(total[position]);
        address123.setText(address[position]);
        mobile123.setText(mobile[position]);
        return rowView;
    }
}