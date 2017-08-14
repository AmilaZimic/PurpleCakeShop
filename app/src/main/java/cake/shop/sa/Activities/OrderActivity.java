package cake.shop.sa.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cake.shop.sa.Adapters.CustomerOrder;
import cake.shop.sa.R;

public class OrderActivity extends AppCompatActivity {

    private static final String TAG = OrderActivity.class.getSimpleName();
    private TextView txtDetails;
    private EditText inputName, inputLastname, inputEmail, inputAddress, inputCakeName, inputNote;
    private Button order_done;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        txtDetails = (TextView) findViewById(R.id.txt_order);
        inputName = (EditText) findViewById(R.id.order_etxt1);
        inputLastname = (EditText) findViewById(R.id.order_etxt2);
        inputEmail = (EditText) findViewById(R.id.order_etxt3);
        inputAddress = (EditText) findViewById(R.id.order_etxt4);
        inputCakeName = (EditText) findViewById(R.id.order_etxt6);
        inputNote = (EditText) findViewById(R.id.order_etxt7);

        order_done = (Button) findViewById(R.id.order_done);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        /* Get reference to 'CustomerOrders' node */
        mFirebaseDatabase = mFirebaseInstance.getReference("CustomerOrders");

        /* Save/update the order */
        order_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputName.getText().toString();
                String lastname = inputLastname.getText().toString();
                String email = inputEmail.getText().toString();
                String address = inputAddress.getText().toString();
                String cake_name = inputCakeName.getText().toString();
                String note = inputNote.getText().toString();

                /* Check for already existed orderId */
                if (TextUtils.isEmpty(orderId)) {
                    createOrder(name, lastname, email, address, cake_name, note);
                } else {
                    updateOrder(name, lastname, email, address, cake_name, note);
                }
            }
        });

        toggleButton();

    }

    /* Changing button text */
    private void toggleButton() {
        if (TextUtils.isEmpty(orderId)) {
            order_done.setText("Done");
        } else {
            order_done.setText("Update");
        }
    }

    /* Creating new order node under 'CustomerOrders' */
    private void createOrder(String name, String lastname, String email, String address, String cake_name, String note) {

        if (TextUtils.isEmpty(orderId)) {
            orderId = mFirebaseDatabase.push().getKey();
        }

        CustomerOrder customer = new CustomerOrder(name, lastname, email, address, cake_name, note);

        mFirebaseDatabase.child(orderId).setValue(customer);

        addOrderChangeListener();
    }

    /* Customer data change listener */
    private void addOrderChangeListener() {

        mFirebaseDatabase.child(orderId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CustomerOrder order = dataSnapshot.getValue(CustomerOrder.class);

                /* Check for null */
                if (order == null) {
                    Log.e(TAG, "Order data is null!");
                    return;
                }

                Log.e(TAG, "Order data is changed!" + order.name + ", " + order.lastname + ", " + order.email + ", "
                        + order.delivery_address + ", " + order.cake_name + ", " + order.note);

                /* Display newly updated order */
                txtDetails.setText(order.name + ", " + order.lastname + ", " + order.email + ", "
                        + order.delivery_address + ", " + order.cake_name + ", " + order.note);

                /* Clear edit text */
                inputName.setText("");
                inputLastname.setText("");
                inputEmail.setText("");
                inputAddress.setText("");
                inputCakeName.setText("");
                inputNote.setText("");

                toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                /* Failed to read value */
                Log.e(TAG, "Failed to read order.", error.toException());
            }
        });
    }

    /* Updating the order via child nodes */
    private void updateOrder(String name, String lastname, String email, String address, String cake_name, String note) {
        if (!TextUtils.isEmpty(name))
            mFirebaseDatabase.child(orderId).child("name").setValue(name);

        if (!TextUtils.isEmpty(lastname))
            mFirebaseDatabase.child(orderId).child("lastname").setValue(lastname);

        if (!TextUtils.isEmpty(email))
            mFirebaseDatabase.child(orderId).child("email").setValue(email);

        if (!TextUtils.isEmpty(address))
            mFirebaseDatabase.child(orderId).child("address").setValue(address);

        if (!TextUtils.isEmpty(cake_name))
            mFirebaseDatabase.child(orderId).child("cake_name").setValue(cake_name);

        if (!TextUtils.isEmpty(note))
            mFirebaseDatabase.child(orderId).child("note").setValue(note);
    }

}

