package cake.shop.sa.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cake.shop.sa.R;

import static android.widget.Toast.LENGTH_LONG;

public class RateFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private Button rate;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public RateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView =inflater.inflate(R.layout.fragment_rate, container, false);
        final RatingBar mBar = (RatingBar) rootView.findViewById(R.id.ratingBar);
        rate = (Button) rootView.findViewById(R.id.rate);

        rate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                float rate=mBar.getRating();
                Toast toast = Toast.makeText(getActivity(), "Your rate is "+rate,LENGTH_LONG);
                toast.show();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference();
                databaseReference.child("AppRating").push().setValue(rate);
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
