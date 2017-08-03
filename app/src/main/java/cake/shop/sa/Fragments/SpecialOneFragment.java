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

public class SpecialOneFragment extends Fragment {

    final ArrayList<Images> ads = new ArrayList<Images>();
    LinearLayoutManager mLinearLayoutManager;
    RecyclerView mRecyclerView;
    ImagesAdapter mAdAdapter;

    public SpecialOneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdAdapter = new ImagesAdapter(ads);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_special_one, container, false);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdAdapter);


        ArrayList<Images> places = new ArrayList<>();
        // Creating ads in database should be considered
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Special%2F1.jpg?alt=media&token=1d417383-0b8a-4ef2-a036-2b8228bb27c3",
                "Mickey Mouse",
                "Mickey Mouse",
                "Three layers of Chocolate chiffon cake with two fillings of custard then topped with a ganache frosting.",
                "Tri sloja čokolade s dva punjenja, a na vrhu s ganaž glazurom."));
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Special%2F2.jpg?alt=media&token=1f9cf2ea-9ec2-434c-8201-8aaa7e19199f",
                "Crni kolač",
                "Black Forest Cake",
                "Chocolate creme cake with cherries, chocolate mousse and triple sec, whip cream frosting and chocolate crumbles on the sides.",
                "Kolač od čokolade s trešnjama, glazura od žitne kreme i čokoladne mrvice sa strane."));
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Special%2F3.jpg?alt=media&token=55948207-6ffb-4a4d-ace7-e1030103fc62",
                "Minions Kolačici",
                "Minions Cakes",
                "Chocolate chiffon cake with mocha (coffee flavored) Mousse and mocha buttercream frosting accented on sugary floral glaze",
                "Čokoladni šifon kolača s aromatiziranom kafom i moka buttercream glazura s naglaskom na šećernu cvijetnu glazuru."));
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Special%2F4.jpg?alt=media&token=0590ea98-7f28-4c01-a62b-99ab37be798b",
                "Mac Kozmetika Torta",
                "Mac Cosmetics Cake",
                " A rich, dense, moist chocolate cake.",
                "Bogata, gusta vlažna čokoladna torta."));
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Special%2F5.jpg?alt=media&token=912feb9c-b668-4560-b966-607b6e6b91a7",
                "Fotoapart Torta",
                "Photo Camera Cake",
                "Vanilla and strawberry flavors combine to give you smooth, mellow flavors reminiscent of your favorite bakery cakes.",
                "Vanilija i jagode daju glatki, nježni okus koji podsjećaja na vaše omiljene pekarske kolače."));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("ImagesSpecial").addValueEventListener(new ValueEventListener() {

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
            databaseReference.child("ImagesSpecial").push().setValue(places.get(i));
        }
        */

        return rootView;
    }
}
