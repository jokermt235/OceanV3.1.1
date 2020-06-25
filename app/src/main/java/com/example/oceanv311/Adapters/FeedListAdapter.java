package com.example.oceanv311.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.oceanv311.Callbacks.Image.OnImageDownloadResult;
import com.example.oceanv311.Modules.SimpleImageLoader;
import com.example.oceanv311.R;
import com.smarteist.autoimageslider.SliderView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.VH> {
    private static  String TAG = "FeedListAdapter";
    private ArrayList<Map<String,Object>> items = new ArrayList();
    private Context context;
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.feed_list_item,parent,false);
            return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VH holder, final int position) {
        holder.name.setText((String)items.get(position).get("name"));
        holder.shop.setText((String)items.get(position).get("shop"));
        holder.place.setText((String)items.get(position).get("place"));
        holder.cloth.setText((String)items.get(position).get("cloth"));
        holder.size.setText((String)items.get(position).get("sizes"));
        holder.price.setText(Long.toString((long)items.get(position).get("price")));
        holder.priceSale.setText(Long.toString((long)items.get(position).get("priceSale")));
        SimpleImageLoader.loadImages("post", (String) items.get(position).get("uid"), new OnImageDownloadResult(){
            @Override
            public void onResult(ArrayList<Bitmap> items) {
                super.onResult(items);
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

    public FeedListAdapter(ArrayList<Map<String,Object>> items, Context context){
            this.items = items;
            this.context = context;
    }
    public static class VH extends   RecyclerView.ViewHolder{
        private TextView shop;
        private TextView place;
        private TextView name;
        private TextView size;
        private TextView cloth;
        private TextView price;
        private TextView priceSale;
        private SliderView images;

        public VH(View view) {
            super(view);
            shop      = view.findViewById(R.id.feedListItemMarket);
            place     = view.findViewById(R.id.feedListItemPlace);
            name      = view.findViewById(R.id.feedListItemInfoName);
            size      = view.findViewById(R.id.feedListItemInfoSize);
            cloth     = view.findViewById(R.id.feedListItemInfoCloth);
            price     = view.findViewById(R.id.feedListItemInfoPrice);
            priceSale = view.findViewById(R.id.feedListItemInfoPriceSale);
            images    = view.findViewById(R.id.feedListItemImageSlider);
        }
    }
}
