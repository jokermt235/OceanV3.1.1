package com.example.oceanv311.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.oceanv311.R;

import java.util.ArrayList;
import java.util.Map;

public class CategoryChooseAdapter extends BaseAdapter {
    private static String TAG = "CategoryChooseAdapter";
    private ArrayList<Map<String , Object>> items = new ArrayList<>();
    private Context context;
    public  CategoryChooseAdapter(Context context, ArrayList<Map<String , Object>> items){
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.category_choose_list_item, null);
            holder = new ViewHolder();
            holder.checkedCategory  = convertView.findViewById(R.id.categoryChooseListItemCheckbox);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.checkedCategory.setText((String) items.get(position).get("name"));
        holder.checkedCategory.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                items.get(position).put("checked", false);
                if(isChecked)
                items.get(position).put("checked", true);
            }
        });
        return convertView;
    }

    static class ViewHolder{
        CheckBox checkedCategory;
    }

}


