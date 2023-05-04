package com.example.latihanlks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.latihanlks.R;

public class customAdapter extends BaseAdapter {

    Context context;
    String data[];
    LayoutInflater inflater;

    public customAdapter(Context ctx, String[] datalist) {
        this.context = ctx;
        this.data = datalist;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_card_list, null);
        TextView txtdataname = (TextView) view.findViewById(R.id.txtdataName);
        txtdataname.setText(data[i]);
        return view;
    }
}
