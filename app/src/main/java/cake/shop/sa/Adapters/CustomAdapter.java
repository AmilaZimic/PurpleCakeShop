package cake.shop.sa.Adapters;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cake.shop.sa.R;

/**
 * Created by Amila on 27/06/2017.
 */

public class CustomAdapter extends ArrayAdapter<RowItemAdapter> {

    LayoutInflater flater;

    public CustomAdapter(Activity context, int resouceId, int textviewId, List<RowItemAdapter> list){

        super(context,resouceId,textviewId, list);
        flater = context.getLayoutInflater();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RowItemAdapter rowItem = getItem(position);

        View v = flater.inflate(R.layout.listitems_layout,null,true);

        TextView txtTitle = (TextView) v.findViewById(R.id.title);
        txtTitle.setText(rowItem.getTitle());

        ImageView imageView = (ImageView) v.findViewById(R.id.icon);
        imageView.setImageResource(rowItem.getImageId());

        return v;
    }

    /* To show images in Spinner DropDownList */

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = flater.inflate(R.layout.listitems_layout, parent, false);
        }
        RowItemAdapter rowItem = getItem(position);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        txtTitle.setText(rowItem.getTitle());
        ImageView imageView = (ImageView) convertView.findViewById(R.id.icon);
        imageView.setImageResource(rowItem.getImageId());
        return convertView;
    }
}


