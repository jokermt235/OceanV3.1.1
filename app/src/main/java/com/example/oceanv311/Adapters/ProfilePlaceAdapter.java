package com.example.oceanv311.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class ProfilePlaceAdapter extends BaseAdapter {
    private ArrayList<Map<String, Object>> items = new ArrayList<>();
    private Context context;
    public ProfilePlaceAdapter(Context context, ArrayList<Map<String,Object>> items){
        this.context = context;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
    static class ViewHolder{
        TextView placeList;
    }
}
