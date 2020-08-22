package com.zybooks.frigo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class add_item extends AppCompatActivity {
    private Button save, reset;
    private EditText itemName, quantity, dateOfPurchase, keepDays, notes;
    private DatabaseReference databaseReference;
//    private long itemID = 0;
    Items item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);

        itemName = (EditText) findViewById(R.id.editText);
        quantity = (EditText) findViewById(R.id.editText5);
        dateOfPurchase = (EditText) findViewById(R.id.editText4);
        keepDays = (EditText) findViewById(R.id.editText6);
        notes = (EditText) findViewById(R.id.editText3);
        save = (Button) findViewById(R.id.button2);
        reset = (Button) findViewById(R.id.button4);
        item = new Items();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Items");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    itemID = (dataSnapshot.getChildrenCount());
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = String.format(itemName.getText().toString().trim());
                String qty = String.format(quantity.getText().toString().trim());
                String dop = String.format(dateOfPurchase.getText().toString().trim());
                String keepDy = String.format(keepDays.getText().toString().trim());
                String nt = String.format(notes.getText().toString().trim());
                item.setItemName(name);
                item.setQuantity(qty);
                item.setDateOfPurchase(dop);
                item.setKeepDays(keepDy);
                item.setNotes(nt);
                databaseReference.push().setValue(item);
//                databaseReference.child(String.valueOf(itemID + 1)).setValue(item);
                Toast.makeText(add_item.this, "Data Inserted Successfully!", Toast.LENGTH_LONG).show();
            }
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
