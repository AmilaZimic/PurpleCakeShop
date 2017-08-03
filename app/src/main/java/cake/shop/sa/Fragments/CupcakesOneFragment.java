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

public class CupcakesOneFragment extends Fragment {

    final ArrayList<Images> ads = new ArrayList<Images>();
    LinearLayoutManager mLinearLayoutManager;
    RecyclerView mRecyclerView;
    ImagesAdapter mAdAdapter;

    public CupcakesOneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdAdapter = new ImagesAdapter(ads);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_cupcakes_one, container, false);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdAdapter);


        ArrayList<Images> places = new ArrayList<>();
        // Creating ads in database should be considered
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Cupcakes%2F1.jpg?alt=media&token=28a962b6-cd32-4bc4-8a90-5e1cc4528a0e",
                "Jagoda Mafin",
                "Strawberry Muffin",
                "Our moist white crème cake with fresh strawberry in whip cream and a whip cream frosting.",
                "Naš svjezi bijeli mafin s kremom od svježih jagoda u bijelom šlagu."));
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Cupcakes%2F4.jpeg?alt=media&token=183b674c-dc78-43ff-a274-17ee1b2c0535",
                "Rozi Mafin",
                "Rose Muffin",
                "A very light, fluffy cake.",
                "Vrlo lagan, pahuljasti kolač."));
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Cupcakes%2F5.jpeg?alt=media&token=ae88987e-0873-41f0-8f4c-b753ee7a45ea",
                "Tiramisu Mafin",
                "Tiramisu Muffin",
                "Chocolate chiffon cake pieces soaked in coffee syrup, creamy tiramisu filling and chocolate pieces accented with slivers of gourmet chocolate.",
                "Čokoladni šifon kolačići natopljeni u sirupu kafe, punjenje vrhnastog tiramisu i čokoladni komadići s naglaskom slojima gurmanske čokolade."));
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Cupcakes%2F3.jpg?alt=media&token=c190947e-0de3-415e-a65d-ff2399c84690",
                "Haupia",
                "Haupia",
                "Chocolate creme cake with cherries, chocolate mousse and triple sec, whip cream frosting and chocolate crumbles on the sides.",
                "Kolač od čokolade s trešnjama i čokoladnim mousse, glazurom od žitne kreme i čokoladne mrvice sa strane."));
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Cupcakes%2F2.jpg?alt=media&token=7084a924-c180-49c8-89bb-17443f95ebbf",
                "Chantilly Cake",
                "Chantilly Cake",
                "Chocolate chiffon with Chantilly, butter based filling and frosting, garnished with macadamia nuts.",
                "Čokoladni šifon s Chantilly, maslacem, punjenjenjem i glazurom od makadamija oraha."));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("ImagesCupcakes").addValueEventListener(new ValueEventListener() {

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
            databaseReference.child("ImagesCupcakes").push().setValue(places.get(i));
        }
        */

        return rootView;
    }
}
