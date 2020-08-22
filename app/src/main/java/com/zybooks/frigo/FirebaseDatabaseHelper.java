package com.zybooks.frigo;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceItems;
    private List<Items> itemz = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded (List<Items> itemz, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }
    public FirebaseDatabaseHelper(){
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceItems = mDatabase.getReference("Items");
    }

    public void readItems(final DataStatus dataStatus){
        mReferenceItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemz.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keynode : dataSnapshot.getChildren()){
                    keys.add(keynode.getKey());
                    Items item = keynode.getValue(Items.class);
                    itemz.add(item);
                }
                dataStatus.DataIsLoaded(itemz, keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
