package cake.shop.sa.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cake.shop.sa.Activities.AnniversaryActivity;
import cake.shop.sa.Activities.BirthdayActivity;
import cake.shop.sa.Activities.CorporateActivity;
import cake.shop.sa.Activities.CupcakesActivity;
import cake.shop.sa.Activities.SpecialActivity;
import cake.shop.sa.Activities.WeddingActivity;
import cake.shop.sa.R;

public class HomeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    Context context;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        context = rootView.getContext(); // Assign your rootView to context

        Button btn1 = (Button) rootView.findViewById(R.id.home_btn1);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BirthdayActivity.class);
                startActivity(intent);
            }
        });

        Button btn2 = (Button) rootView.findViewById(R.id.home_btn2);
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WeddingActivity.class);
                startActivity(intent);
            }
        });

        Button btn3 = (Button) rootView.findViewById(R.id.home_btn3);
        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AnniversaryActivity.class);
                startActivity(intent);
            }
        });

        Button btn4 = (Button) rootView.findViewById(R.id.home_btn4);
        btn4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CupcakesActivity.class);
                startActivity(intent);
            }
        });

        Button btn5 = (Button) rootView.findViewById(R.id.home_btn5);
        btn5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CorporateActivity.class);
                startActivity(intent);
            }
        });

        Button btn6 = (Button) rootView.findViewById(R.id.home_btn6);
        btn6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SpecialActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
