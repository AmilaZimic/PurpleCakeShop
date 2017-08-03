package cake.shop.sa.Adapters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Locale;

import cake.shop.sa.R;

public class ImagesDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_details);

        ImageView imageView = (ImageView) findViewById(R.id.imageAdDetails);
        TextView textView = (TextView) findViewById(R.id.textAdDetails);
        TextView textDesc = (TextView) findViewById(R.id.descriptionAdDetails);

        if (getIntent().hasExtra("ad")) {
            Images ad = (Images) getIntent().getSerializableExtra("ad");
            if(Locale.getDefault().getLanguage().equals("hr") || Locale.getDefault().getLanguage().equals("bs"))
            {
                textView.setText(ad.name_bih);
                textDesc.setText(ad.Description_bih);
            }
            else
            {
                textView.setText(ad.name_eng);
                textDesc.setText(ad.Description_eng);
            }

            Glide.with(imageView.getContext()).load(ad.image1).into(imageView);
        }
    }
}