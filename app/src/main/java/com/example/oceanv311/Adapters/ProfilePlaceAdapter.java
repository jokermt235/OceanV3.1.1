package com.example.oceanv311.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oceanv311.R;

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
        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.profile_place_list_item, null);
            holder.placeList = convertView.findViewById(R.id.profilePlaceListItemTitle);
            holder.chevronIcon = convertView.findViewById(R.id.profilePlaceItemChevron);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        String title = items.get(position).get("market").toString() + "рк , "+
                items.get(position).get("row").toString() + "р / " +
                items.get(position).get("pavilion").toString() +  "п / " +
                items.get(position).get("floor").toString()  + "э";
        holder.placeList.setText(title);
        return convertView;
    }
    static class ViewHolder{
        TextView placeList;
        ImageView chevronIcon;
    }
}
