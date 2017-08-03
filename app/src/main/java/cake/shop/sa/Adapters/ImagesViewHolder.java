package cake.shop.sa.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Locale;

import cake.shop.sa.R;

/**
 * Created by Amila on 25/07/2017.
 */

public class ImagesViewHolder extends RecyclerView.ViewHolder {

    private ImageView mAdImage;
    private TextView mAdName;

    public ImagesViewHolder(View itemView) {
        super(itemView);
        mAdImage = (ImageView) itemView.findViewById(R.id.ad_image);
        mAdName = (TextView) itemView.findViewById(R.id.ad_name);
    }

    public void bindAd(Images ad) {
        if(Locale.getDefault().getLanguage().equals("hr") || Locale.getDefault().getLanguage().equals("bs"))
        {
            mAdName.setText(ad.name_bih);
        }
        else
        {
            mAdName.setText(ad.name_eng);
        }
        Glide.with(mAdImage.getContext()).load(ad.image1).into(mAdImage);
    }

}
