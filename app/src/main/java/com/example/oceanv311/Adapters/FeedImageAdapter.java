package com.example.oceanv311.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.oceanv311.R;
import com.smarteist.autoimageslider.SliderViewAdapter;
import java.util.ArrayList;

public class FeedImageAdapter extends SliderViewAdapter<FeedImageAdapter.FeedImageAdapterVH> {
    private Context context;
    public ArrayList<Bitmap> items;
    @Override
    public FeedImageAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_image_slider, null);
        return new FeedImageAdapterVH(inflate);
    }


    public FeedImageAdapter(Context context, ArrayList<Bitmap> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public void onBindViewHolder(final FeedImageAdapterVH viewHolder, int position) {
        if(items.size() > 0){
            viewHolder.imageViewBackground.setImageBitmap(items.get(position));
        }
    }

    @Override
    public int getCount() {
        return items.size();
    }

    class FeedImageAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;

        public FeedImageAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.feedImageElement);
            this.itemView = itemView;
        }
    }
}
