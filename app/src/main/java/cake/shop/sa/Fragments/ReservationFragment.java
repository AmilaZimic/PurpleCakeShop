package cake.shop.sa.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import cake.shop.sa.Adapters.CustomAdapter;
import cake.shop.sa.Adapters.CustomerReservation;
import cake.shop.sa.Adapters.RowItemAdapter;
import cake.shop.sa.R;

import static android.widget.Toast.LENGTH_LONG;

public class ReservationFragment extends DialogFragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    public static final String[] seats = new String[] {
            "Two seats",
            "Three seats",
            "Four seats",
            "Five seats",
            "Six seats",
            "Seven seats",
            "Eight seats"
    };

    public static final Integer[] seats_images = {
            R.drawable.seats2,
            R.drawable.seats3,
            R.drawable.seats4,
            R.drawable.seats5,
            R.drawable.seats6,
            R.drawable.seats7,
            R.drawable.seats8
    };

    public static final String[] location = new String[] {
            "Marlton",
            "Morristown",
            "Hoboken",
            "Ridgewood",
            "Red Bank",
            "Westfield",
            "Wayne"
    };

    public static final Integer[] location_images = {
            R.drawable.im_shop1,
            R.drawable.im_shop2,
            R.drawable.im_shop3,
            R.drawable.im_shop4,
            R.drawable.im_shop5,
            R.drawable.im_shop6,
            R.drawable.im_shop7
    };

    private DatePickerDialog fromDatePickerDialog;
    private TimePickerDialog fromTimePickerDialog;

    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat timeFormatter;

    private EditText fromDateEtxt;
    private EditText fromTimeEtxt;

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;

    private Button reservation_done;

    private ReservationFragment.OnFragmentInteractionListener mListener;

    private static final String TAG = ReservationFragment.class.getSimpleName();
    private TextView txtDetails;
    private EditText inputName, inputLastname, inputEmail, inputPhone, inputDate, inputTime;
    private Spinner inputLocation, inputSeats;
    private Button btnSave;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private String customerId;

    public ReservationFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_reservation, container, false);

        /** Location Spinner **/

        List<RowItemAdapter> rowItems1;

        rowItems1 = new ArrayList<RowItemAdapter>();
        for (int i = 0; i < location.length; i++) {

            RowItemAdapter item = new RowItemAdapter(location[i],location_images[i]);
            rowItems1.add(item);
        }

        Spinner spinner1 = (Spinner) v.findViewById(R.id.spinner1);
        CustomAdapter adapter1 = new CustomAdapter(getActivity(), R.layout.listitems_layout, R.id.title, rowItems1);
        spinner1.setAdapter(adapter1);

        /** Seats Spinner **/

        List<RowItemAdapter> rowItems2;

        rowItems2 = new ArrayList<RowItemAdapter>();
        for (int i = 0; i < seats.length; i++) {

            RowItemAdapter item = new RowItemAdapter(seats[i],seats_images[i]);
            rowItems2.add(item);
        }

        Spinner spinner2 = (Spinner) v.findViewById(R.id.spinner2);
        CustomAdapter adapter2 = new CustomAdapter(getActivity(), R.layout.listitems_layout, R.id.title, rowItems2);
        spinner2.setAdapter(adapter2);

        /** Date & Time Picker **/

        dateFormatter = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
        fromDateEtxt = (EditText) v.findViewById(R.id.reservation_etxt6);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromDateEtxt.requestFocus();

        timeFormatter = new SimpleDateFormat("HH:mm", Locale.US);
        fromTimeEtxt = (EditText) v.findViewById(R.id.reservation_etxt7);
        fromTimeEtxt.setInputType(InputType.TYPE_NULL);
        fromTimeEtxt.requestFocus();

        setDateField();
        setTimeField();

        editText1 = (EditText) v.findViewById(R.id.reservation_etxt1);
        editText2 = (EditText) v.findViewById(R.id.reservation_etxt2);
        editText3 = (EditText) v.findViewById(R.id.reservation_etxt3);
        editText4 = (EditText) v.findViewById(R.id.reservation_etxt4);

        editText1.addTextChangedListener(mTextWatcher);
        editText2.addTextChangedListener(mTextWatcher);
        editText3.addTextChangedListener(mTextWatcher);
        editText4.addTextChangedListener(mTextWatcher);
        fromDateEtxt.addTextChangedListener(mTextWatcher);
        fromTimeEtxt.addTextChangedListener(mTextWatcher);


        reservation_done = (Button) v.findViewById(R.id.reservation_done);
        reservation_done.setEnabled(false);

        reservation_done.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Thank You! See You soon :)", LENGTH_LONG).show();
            }
        });

        txtDetails = (TextView) v.findViewById(R.id.txt_customer);
        inputName = (EditText) v.findViewById(R.id.reservation_etxt1);
        inputLastname = (EditText) v.findViewById(R.id.reservation_etxt2);
        inputEmail = (EditText) v.findViewById(R.id.reservation_etxt3);
        inputPhone = (EditText) v.findViewById(R.id.reservation_etxt4);
        inputDate = (EditText) v.findViewById(R.id.reservation_etxt6);
        inputTime = (EditText) v.findViewById(R.id.reservation_etxt7);
        inputLocation = (Spinner) v.findViewById(R.id.spinner1);
        inputSeats = (Spinner) v.findViewById(R.id.spinner2);

        btnSave = (Button) v.findViewById(R.id.reservation_done);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'CustomerReseravtion' node
        mFirebaseDatabase = mFirebaseInstance.getReference("CustomerReseravtion");

        // Save / update the user
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputName.getText().toString();
                String lastname = inputLastname.getText().toString();
                String email = inputEmail.getText().toString();
                String phone = inputPhone.getText().toString();
                String date = inputDate.getText().toString();
                String time = inputTime.getText().toString();
                String location = inputLocation.getSelectedItem().toString();
                String seats = inputSeats.getSelectedItem().toString();

                // Check for already existed customerId
                if (TextUtils.isEmpty(customerId)) {
                    createUser(name, lastname, email, phone, date, time, location, seats);
                } else {
                    updateUser(name, lastname, email, phone, date, time, location, seats);
                }
            }
        });

        toggleButton();

        return v;
    }

    /* Changing button text */
    private void toggleButton() {
        if (TextUtils.isEmpty(customerId)) {
            btnSave.setText("Done");
        } else {
            btnSave.setText("Update");
        }
    }

    /* Creating new customer node under 'customers' */
    private void createUser(String name, String lastname, String email, String phone, String date, String time, String location, String seats) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(customerId)) {
            customerId = mFirebaseDatabase.push().getKey();
        }

        CustomerReservation customer = new CustomerReservation(name, lastname, email, phone, date, time, location, seats);

        mFirebaseDatabase.child(customerId).setValue(customer);

        addCustomerChangeListener();
    }

    /* Customer data change listener */
    private void addCustomerChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(customerId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CustomerReservation customer = dataSnapshot.getValue(CustomerReservation.class);

                /* Check for null */
                if (customer == null) {
                    Log.e(TAG, "Customer data is null!");
                    return;
                }

                Log.e(TAG, "Customer data is changed!" + customer.name + ", " + customer.lastname + ", " + customer.email + ", "
                        + customer.phone + ", " + customer.date + ", " + customer.time + ", " + customer.location + ", " + customer.seats);

                /* Display newly updated name and email */
                txtDetails.setText(customer.name + ", " + customer.lastname + ", " + customer.email + ", "
                        + customer.phone + ", " + customer.date + ", " + customer.time + ", " + customer.location + ", " + customer.seats);

                /* Clear edit text */
                inputName.setText("");
                inputLastname.setText("");
                inputEmail.setText("");
                inputPhone.setText("");
                inputDate.setText("");
                inputTime.setText("");

                toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                /* Failed to read value */
                Log.e(TAG, "Failed to read customer.", error.toException());
            }
        });
    }

    /* Update user */
    private void updateUser(String name, String lastname, String email, String phone, String date, String time, String location, String seats) {
        /* Updating the user via child nodes */
        if (!TextUtils.isEmpty(name))
            mFirebaseDatabase.child(customerId).child("name").setValue(name);

        if (!TextUtils.isEmpty(lastname))
            mFirebaseDatabase.child(customerId).child("lastname").setValue(lastname);

        if (!TextUtils.isEmpty(email))
            mFirebaseDatabase.child(customerId).child("email").setValue(email);

        if (!TextUtils.isEmpty(phone))
            mFirebaseDatabase.child(customerId).child("phone").setValue(phone);

        if (!TextUtils.isEmpty(date))
            mFirebaseDatabase.child(customerId).child("date").setValue(date);

        if (!TextUtils.isEmpty(time))
            mFirebaseDatabase.child(customerId).child("time").setValue(time);

        if (!TextUtils.isEmpty(location))
            mFirebaseDatabase.child(customerId).child("location").setValue(location);

        if (!TextUtils.isEmpty(seats))
            mFirebaseDatabase.child(customerId).child("seats").setValue(seats);
    }

    /* Set Date */
    private void setDateField() {

        fromDateEtxt.setOnClickListener(this);

        final java.util.Calendar newCalendar = java.util.Calendar.getInstance();

        fromDatePickerDialog = new DatePickerDialog(getActivity(), R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                java.util.Calendar newDate = java.util.Calendar.getInstance();
                newDate.set(Calendar.YEAR, year);
                newDate.set(Calendar.MONTH, monthOfYear);
                newDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));

                Toast.makeText(getActivity(), "You choose: " + dayOfMonth + "." + (monthOfYear + 1) + "." + year, Toast.LENGTH_LONG).show();
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(java.util.Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    /* Set Time */
    private void setTimeField(){

        fromTimeEtxt.setOnClickListener(this);

        java.util.Calendar newCalendar = java.util.Calendar.getInstance();

        fromTimePickerDialog = new TimePickerDialog(getActivity(), R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener(){

            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                java.util.Calendar newTime = java.util.Calendar.getInstance();
                newTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                newTime.set(Calendar.MINUTE, minute);
                fromTimeEtxt.setText(timeFormatter.format(newTime.getTime()));

                Toast.makeText(getActivity(), "You choose: " + hourOfDay + ":" + minute, Toast.LENGTH_LONG).show();

            }

        },newCalendar.get(java.util.Calendar.HOUR_OF_DAY), newCalendar.get(java.util.Calendar.MINUTE), false);

    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {

            String s1 = editText1.getText().toString();
            String s2 = editText2.getText().toString();
            String s3 = editText3.getText().toString();
            String s4 = editText4.getText().toString();
            String s5 = fromDateEtxt.getText().toString();
            String s6 = fromTimeEtxt.getText().toString();

            if(s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") || s5.equals("") || s6.equals("")){
                reservation_done.setEnabled(false);
            } else {
                reservation_done.setEnabled(true);
            }
        }
    };

    @Override
    public void onClick(View v) {
        if(v == fromDateEtxt) {
            fromDatePickerDialog.show();
        }
        else if(v == fromTimeEtxt) {
            fromTimePickerDialog.show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(), "Location: " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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