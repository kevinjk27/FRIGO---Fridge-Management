package com.zybooks.frigo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class view_item extends AppCompatActivity {
    private Button retrieve, reset;
    private TextView itemName, quantity, dateOfPurchase, keepDays, notes;
    private EditText mQuery;


    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference databaseReference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_item);

        itemName = (TextView) findViewById(R.id.textView9);
        quantity = (TextView) findViewById(R.id.textView10);
        dateOfPurchase = (TextView) findViewById(R.id.textView11);
        keepDays = (TextView) findViewById(R.id.textView14);
        notes = (TextView) findViewById(R.id.textView13);
        retrieve = (Button) findViewById(R.id.button5);
        reset = (Button) findViewById(R.id.button6);
        mQuery = (EditText) findViewById(R.id.editText2);


        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();


        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) throws NullPointerException {

                if ((mQuery.getText().toString()).equals("")) {
                    Toast.makeText(view_item.this, "Insert an item!", Toast.LENGTH_LONG).show();
                } else if (!(mQuery.getText().toString()).contains("-")) {
                    Toast.makeText(view_item.this, "No item found", Toast.LENGTH_LONG).show();
                } else {
                    String query = mQuery.getText().toString();
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Items").child(query.toString());
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                            showData(dataSnapshot);
                            ArrayList<String> listName = new ArrayList<>();

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                listName.add(snapshot.getValue().toString());
                            }
                            String name = dataSnapshot.child("itemName").getValue().toString();
                            String qty = dataSnapshot.child("quantity").getValue().toString();
                            String dop = dataSnapshot.child("dateOfPurchase").getValue().toString();
                            String keepDy = dataSnapshot.child("keepDays").getValue().toString() + " days";
                            String nt = dataSnapshot.child("notes").getValue().toString();
                            itemName.setText(name);
                            quantity.setText(qty);
                            dateOfPurchase.setText(dop);
                            keepDays.setText(keepDy);
                            notes.setText(nt);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            }

//            private void showData(DataSnapshot dataSnapshot) {
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    Items dItem = new Items();

//                    dItem.setItemName(ds.child(query).getValue(Items.class).getItemName());
//                    dItem.setQuantity(ds.child(query).getValue(Items.class).getQuantity());
//                    dItem.setDateOfPurchase(ds.child(query).getValue(Items.class).getDateOfPurchase());
//                    dItem.setKeepDays(ds.child(query).getValue(Items.class).getKeepDays());
//                    dItem.setNotes(ds.child(query).getValue(Items.class).getNotes());
//
//                    ArrayList<String> itemArray = new ArrayList<>();
//                    itemArray.add(dItem.getItemName());
//                    itemArray.add(dItem.getQuantity());
//                    itemArray.add(dItem.getDateOfPurchase());
//                    itemArray.add(dItem.getKeepDays());
//                    itemArray.add(dItem.getNotes());
////                    ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,itemArray);
////                    mListView.setAdapater(adapter);
//
//                }
//            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemName.setText("");
                quantity.setText("");
                dateOfPurchase.setText("");
                keepDays.setText("");
                notes.setText("");
            }
        });
    }
}
