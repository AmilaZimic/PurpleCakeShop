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

public class AnniversaryOneFragment extends Fragment {

    final ArrayList<Images> ads = new ArrayList<Images>();
    LinearLayoutManager mLinearLayoutManager;
    RecyclerView mRecyclerView;
    ImagesAdapter mAdAdapter;

    public AnniversaryOneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdAdapter = new ImagesAdapter(ads);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_anniversary_one, container, false);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdAdapter);


        ArrayList<Images> places = new ArrayList<>();
        // Creating ads in database should be considered
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Anniversary%2F1.jpg?alt=media&token=f33b04b2-5f91-44e8-b1d6-9e10ee48dcb9",
                "Volim te",
                "Love You",
                "A light German Chocolate chiffon cake with red coloring and our cream cheese filling and frosting. Our version of an old-fashioned devil’s food chocolate cake frosted with chocolate buttercream. This is a real crowd-pleaser. It is popular white chocolate cake layered with lemon curd, whipped cream, fresh raspberries and raspberry preserves. Finished with white chocolate cream cheese frosting.",
                "Lagani njemački kolač od čokolade s crvenom bojom i punjenjem od krem sira, prekriven glazurom. Naša inačica staromodane đavolske hrane hrane s čokoladom. Ovo je pravi miljenik. Popularna torta od bijele čokolade sa slojevima limuna, šlaga, svježih malina i konzerviranih malina. Završena s bijelom čokoladnom kremastom sirnom glazurom."));
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Anniversary%2F3.jpg?alt=media&token=dbd9e837-bd53-4611-9105-e837d2bb629f",
                "Mocha Mousse",
                "Mocha Mousse",
                "Chocolate chiffon cake with mocha (coffee flavored) Mousse and mocha buttercream frosting accented on sugary floral glaze. Desert for the coffee lovers and chocolate lovers at the same time. Seven layers of chocolate bliss composed of moist chocolate cake, espresso whipped cream, chocolate ganache, and rich chocolate buttercream. This one is slightly more sweet than Chocolate Dementia.",
                "Čokoladni šifon kolača s aromatiziranom kafom i moka buttercream glazura s naglaskom na šećernu cvijetnu glazuru. Dezert za ljubitelje kafe i ljubitelje čokolade u isto vrijeme. Sedam slojeva čokoladnog blaženstva, sastavljene od vlažne čokoladne kore, s kremom od espressa, čokoladnog ganaža i bogate čokolade. Ovo je nešto sladje od čokoladne demencije."));
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Anniversary%2F4.jpg?alt=media&token=88f3cca0-84a3-4123-b7c7-ddcc8e001d3c",
                "Haupia",
                "Haupia",
                "White chiffon cake with Haupia (coconut pudding) filling and whipped cream frosting garnished with shredded coconut. Creamy white chocolate Bavarian cream layered with fresh coconut compote.",
                "Bijeli šifon kolača s punjenjem od kokosovog pudinga i ukrašen šlag glazurom s isjeckanim kokosom. Kremasta bijela bavarska čokolada s vrhnjem i slojevima s svježim kompotom od kokosa."));
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Anniversary%2F5.jpg?alt=media&token=88e025a5-05ee-4cbd-84a8-50492777de45",
                "King Arthur Torta",
                "King Arthur Cake",
                "Vanilla and almond flavors combine to give you smooth, mellow flavors reminiscent of your favorite bakery cakes. Spice cake paired with caramel cream cheese mousse and fresh vanilla compote made from heirloom variety gravenstein apples from Gizdich Ranch in Watsonville. This recipe uses King Arthur.",
                "Vanilija i bademi daju glatki, nježni okus koji podsjećaja na vaše omiljene pekarske kolače. Kolač uparen s kremastim sirnim mousseom i svježim mlijekom od vanilije iz Gizdich Ranch u Watsonvilleu. Ovaj recept je koristio King Arthur."));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("ImagesAnniversary").addValueEventListener(new ValueEventListener() {

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
            databaseReference.child("ImagesAnniversary").push().setValue(places.get(i));
        }
        */

        return rootView;
    }
}
