package com.example.ecomarket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SuccessfulPage extends AppCompatActivity {

    private Button idBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_page);
        idBackHome = findViewById(R.id.idBackHome);

        idBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SuccessfulPage.this, UserViewCategories.class);
                startActivity(i);
            }
        });
    }
}