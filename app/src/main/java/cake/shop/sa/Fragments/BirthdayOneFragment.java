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

public class BirthdayOneFragment extends Fragment {

    final ArrayList<Images> ads = new ArrayList<Images>();
    LinearLayoutManager mLinearLayoutManager;
    RecyclerView mRecyclerView;
    ImagesAdapter mAdAdapter;

    public BirthdayOneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdAdapter = new ImagesAdapter(ads);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_birthday_one, container, false);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdAdapter);


        ArrayList<Images> places = new ArrayList<>();
        // Creating ads in database should be considered
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Birthday%2F1.jpg?alt=media&token=6b84f108-2445-4074-bd93-a7a0be10e825",
                "Čokoladno mliječna torta",
                "Chocolate Tres Leches",
                "Our traditional tres leche frosted with 72% Belgian chocolate whipped. Peanut Butter is folded into our Bavarian cream paired with honey roasted peanuts and a layer of dark Belgian chocolate ganache.",
                "Naša tradicionalna čokoladno mliječna torta s 72% belgijskom čokoladnom. Maslac od kikirikija s našom bavarskom kremom u kombinaciji s pečenim kikirikijem u medu i slojevima tamne belgijske čokoladec."));
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Birthday%2F2.jpg?alt=media&token=1bd0778a-e7ce-4b4d-9ca2-e71ed3274ae3",
                "Mišelina Poslastica od Čokolade",
                "Michael’s Fudge Cake",
                "A rich fudge frosting. Thank you Michael! Gluten free. Our version of an old-fashioned devil’s food chocolate cake frosted with chocolate buttercream. This is a real crowd-pleaser.",
                "Bogata glatka glazura. Hvala ti Michael! Bez glutena. Naša inačica staromodane đavolske hrane hrane s čokoladom. Ovo je pravi miljenik."));
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Birthday%2F4.jpg?alt=media&token=7a11ce7d-c6c3-4b73-88a8-58c708dbd804",
                "Tri sloja",
                "Three layers",
                "Three layers of dark chocolate cake finished with rich creamy fudge. Quite possibly the best chocolate cake you’ve ever tasted.",
                "Tri sloja tamne čokoladne kolače završile su bogatim kremastim okusom. Vrlo vjerojatno najbolji čokoladni kolač koji ste ikad kušali."));
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Birthday%2F3.jpg?alt=media&token=2cc2977d-32bf-42ba-8c12-68fc0027a822",
                "Tri Mlijeka",
                "Three Milks",
                "Sweetened condensed, cream and whole milk poured over rich sponge cake and finished with whipped cream and rainbow fondan – incredible!",
                "Zaslađeno kondenzirano vrhnje i mlijeko preliveno preko bogate kore i završeno s šlagom i duginim fondanom - nevjerojatno!"));
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Birthday%2F5.jpg?alt=media&token=ee3aeae6-a959-4c93-b3ef-3babe63e0f21",
                "Njemački čokoladni kolač",
                "German Chocolate Cake",
                "Three layers of rich chocolate cake frosted with our housemade German Chocolate frosting with coconut and pecans.",
                "Tri sloja bogatog čokoladnog kolača sladoled s našim domaćim njemačkim čokoladnim glazurama s kokosom i orasima."));
        places.add(new Images(
                "https://firebasestorage.googleapis.com/v0/b/cakeshop-d7135.appspot.com/o/Birthday%2F6.jpg?alt=media&token=77ab6593-7b25-4aa6-88bd-7e35b3810533",
                "Zlatni Kralj",
                "Gold King",
                "We’re famous for this moist, rich three-layer wonder. The cake layers are dark chocolate frosted with rich fudge and caramel and generous amounts of giant pecans.",
                "Poznati smo zbog ovog vlažnog, bogatog troslojnog čuda. Slojevi kolača su tamna čokolada, zamrljana bogatim ljepljenjem i karamelom i velikodušnim količinama divovskih oraha."));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("ImagesBirthday").addValueEventListener(new ValueEventListener() {

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
            databaseReference.child("ImagesBirthday").push().setValue(places.get(i));
        }
        */

        return rootView;
        }
    }
