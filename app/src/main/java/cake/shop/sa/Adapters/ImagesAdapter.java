package cake.shop.sa.Adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cake.shop.sa.R;

/**
 * Created by Amila on 25/07/2017.
 */

public  class ImagesAdapter extends RecyclerView.Adapter<ImagesViewHolder> {
    ArrayList<Images> mImages;

    public ImagesAdapter(ArrayList<Images> mAds)
    {
        this.mImages = mAds;
    }

    @Override
    public ImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ImagesViewHolder(inflatedView);
    }

    public void onBindViewHolder(ImagesViewHolder holder, int position) {
        final Images image = mImages.get(position);
        holder.bindAd(image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), ImagesDetails.class);
                intent.putExtra("ad",image);

                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }
}
