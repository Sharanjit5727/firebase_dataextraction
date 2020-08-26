package com.example.read_write_firebase;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class ItemsList extends ArrayAdapter<Item> {
    private Activity context;
    private List<Item> itemList;
    public ItemsList(Activity context,List<Item> itemList)
    {
        super(context,R.layout.list_item,itemList);
        this.context=context;
        this.itemList=itemList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listviewitem= inflater.inflate(R.layout.list_item,null,true);
        TextView textviewitem=(TextView)listviewitem.findViewById(R.id.textview);
        Item item=itemList.get(position);
        textviewitem.setText(item.getItem());
        return listviewitem;
    }
}
