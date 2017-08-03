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

public class WeddingOneFragment extends Fragment {

    final ArrayList<Images> ads = new ArrayList<Images>();
    LinearLayoutManager mLinearLayoutManager;
    RecyclerView mRecyclerView;
    ImagesAdapter mAdAdapter;

    public WeddingOneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdAdapter = new ImagesAdapter(ads);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_wedding_one, container, false);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdAdapter);


        ArrayList<Images> places = new ArrayList<>();
        // Creating ads in database should be considered
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Wedding%2F1.jpg?alt=media&token=3f1d85c3-6fc5-451f-9d08-61bdbcb6b3fb",
                "Volim te",
                "Love You",
                "Short paste crust with fresh and canned fruit slices arranged on custard made with almond paste.",
                "Kratka kora tijesta sa svježim i konzerviranim voćnim krišama postavljenim na kremu napravljenu od bademovog tijesta."));
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Wedding%2F2.jpeg?alt=media&token=c9d3d056-56a7-4941-9773-6920fc3369c3",
                "Čokoladni Rum Kolač",
                "Chocolate Rum Cake",
                "Chocolate creme cake soaked with rum flavored syrup and chocolate buttercream frosting.",
                "Čokoladni kolač natopljenim rum sirupom i čokoladom od voćnog krastavaca."));
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Wedding%2F3.jpg?alt=media&token=31b5c78d-d430-454a-8dbf-76cf46dd965c",
                "Četiri Godisnja Doba",
                "Four Seasons",
                "Chocolate chiffon cake with mocha (coffee flavored) Mousse and mocha buttercream frosting accented on sugary floral glaze",
                "Čokoladni šifon kolača s aromatiziranom kafom i moka buttercream glazura s naglaskom na šećernu cvijetnu glazuru."));
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Wedding%2F4.jpg?alt=media&token=53570cd3-a786-401c-9577-b3530977ef51",
                "Bijeli Andjeo",
                "White Angel",
                "White chiffon cake with Haupia (coconut pudding) filling and whipped cream frosting garnished with shredded coconut.",
                "Bijeli šifon kolača s punjenjem od kokosovog pudinga i ukrašen šlag glazurom s isjeckanim kokosom."));
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Wedding%2F5.jpg?alt=media&token=942a38b7-0063-43f6-8b5e-b7ff24ba3fed",
                "Crvena Kraljevska Torta",
                "Red King Cake",
                "Vanilla and almond flavors combine to give you smooth, mellow flavors reminiscent of your favorite bakery cakes. This recipe uses King Arthur.",
                "Vanilija i bademi daju glatki, nježni okus koji podsjećaja na vaše omiljene pekarske kolače. Ovaj recept je koristio King Arthur."));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("ImagesWedding").addValueEventListener(new ValueEventListener() {

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
            databaseReference.child("ImagesWedding").push().setValue(places.get(i));
        }
        */

        return rootView;
    }
}

