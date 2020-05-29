package com.example.oceanv311.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.oceanv311.R;

import java.util.ArrayList;
import java.util.Map;

public class CategoryChooseAdapter extends BaseAdapter {
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
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_choose_list_item, null);
        return view;
    }
}
