package com.example.oceanv311.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oceanv311.R;

import java.util.ArrayList;
import java.util.Map;

public class SizeChooseAdapter extends RecyclerView.Adapter<SizeChooseAdapter.VH>{
    private ArrayList<Map<String,Object>> items = new ArrayList();
    private Context context;
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.size_choose_list_item,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, final int position) {
        holder.checkBox.setText((String)items.get(position).get("name"));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                items.get(position).put("checked",true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public ArrayList<Map<String, Object>> getItems(){
        return items;
    }

    public SizeChooseAdapter(ArrayList<Map<String,Object>> items, Context context){
        this.items = items;
        this.context = context;
    }

    public static class VH extends   RecyclerView.ViewHolder{
        private CheckBox checkBox;
        private View view;

        public VH(View view) {
            super(view);
            this.view = view;
            this.checkBox = view.findViewById(R.id.sizeChooseListItemCheckbox);
        }
    }
}
