package cake.shop.sa.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import cake.shop.sa.Adapters.Images;
import cake.shop.sa.Adapters.ImagesAdapter;
import cake.shop.sa.R;

/**
 * Created by Amila on 03/07/2017.
 */

public class CorporateOneFragment extends Fragment {

    final ArrayList<Images> ads = new ArrayList<Images>();
    LinearLayoutManager mLinearLayoutManager;
    RecyclerView mRecyclerView;
    ImagesAdapter mAdAdapter;

    public CorporateOneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdAdapter = new ImagesAdapter(ads);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_corporate_one, container, false);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdAdapter);


        ArrayList<Images> places = new ArrayList<>();
        // Creating ads in database should be considered
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Corporate%2F1.jpg?alt=media&token=40d74b11-6ff6-4b36-b93d-2382abceaaa6",
                "Srebreni Mafin",
                "Silver Muffin",
                "A light muffin cake with silver coloring and our cream vanilla filling and frosting.",
                "Lagani mafin od čokolade s srebrenom bojom i punjenjem od krem vanilije, prekriven glazurom."));
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Corporate%2F2.jpg?alt=media&token=cbdb7d28-f946-4585-a617-ee2745a73002",
                "Slatki Štapici",
                "Sweet Sticks",
                "A rich, dense, moist coconuts cake on stick.",
                "Bogati, gusti, svjezi kolač od kokosa na štapiću."));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("ImagesCorporate").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    Images r = child.getValue(Images.class);
                    ads.add(r);

                    mAdAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*
        for (int i = 0; i < places.size(); i++) {
            databaseReference.child("ImagesCorporate").push().setValue(places.get(i));
        }
        */

        return rootView;
    }
}
