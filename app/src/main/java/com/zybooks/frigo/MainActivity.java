package com.zybooks.frigo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {
    private Button button, button3;
    private FirebaseAnalytics mFirebaseAnalytics;


    public static final String CONTENT_TYPE = null;

    Bundle bundle = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button); // add button
        button3 = (Button) findViewById(R.id.button3); // view button
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddItem();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewActivity();
            }
        });
    }

    public void openAddItem() {
        Intent intent = new Intent(this, add_item.class);
        startActivity(intent);
    }

    public void openViewActivity() {
        Intent intent2 = new Intent(MainActivity.this,view_item.class);
        startActivity(intent2);
    }


}

