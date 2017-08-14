package cake.shop.sa.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cake.shop.sa.Activities.OrderActivity;
import cake.shop.sa.R;

/**
 * Created by Amila on 03/07/2017.
 */

public class CorporateTwoFragment extends Fragment {

    Context context;

    public CorporateTwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_corporate_two, container, false);
        context = rootView.getContext(); // Assign your rootView to context

        Button btn1 = (Button) rootView.findViewById(R.id.c_order1);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderActivity.class);
                startActivity(intent);
            }

        });

        Button btn2 = (Button) rootView.findViewById(R.id.c_order2);
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
