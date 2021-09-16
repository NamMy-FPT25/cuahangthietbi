package com.example.onlineapplicationstore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlineapplicationstore.R;
import com.example.onlineapplicationstore.model.Loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaispAdapter extends BaseAdapter {
    ArrayList<Loaisp> arrayListloaisp;
    Context context;

    public LoaispAdapter(ArrayList<Loaisp> arrayListloaisp, Context context) {
        this.arrayListloaisp = arrayListloaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListloaisp.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListloaisp.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class Viewholder{
        TextView txttenloaisp;
        ImageView imgloaisp;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder viewholder = null;
        if (view == null){
            viewholder = new Viewholder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_loaisp, null);
            viewholder.txttenloaisp = (TextView) view.findViewById(R.id.textviewloaisp);
            viewholder.imgloaisp = (ImageView) view.findViewById(R.id.imageviewloaisp);
            view.setTag(viewholder);
        }else {
            viewholder = (Viewholder) view.getTag();
        }
        Loaisp loaisp = (Loaisp) getItem(i);
        viewholder.txttenloaisp.setText(loaisp.getTenloaisp());
        Picasso.get().load(loaisp.getHinhanhloaisp())
                .placeholder(R.drawable.ic_no_img_black_24dp)
                .error(R.drawable.cancel)
                .into(viewholder.imgloaisp);
        return view;
    }
}
