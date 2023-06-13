package com.example.aahaarapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

public class places extends AppCompatActivity {

    CardView school,office,parks,theater,hotel,atm,groceries,clothing,
                railway,busstop;
    Button Sos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        school=findViewById(R.id.school);
       office =findViewById(R.id.Offices);
        parks=findViewById(R.id.Parks);
        theater=findViewById(R.id.theater);
        hotel=findViewById(R.id.hotels);
        atm=findViewById(R.id.atm);
        groceries=findViewById(R.id.groceries);
        clothing=findViewById(R.id.clothing);
        railway=findViewById(R.id.railway);
        busstop=findViewById(R.id.busstops);
        Sos=findViewById(R.id.Sos);


        school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Search for school nearby
                Uri gmmIntentUri = Uri.parse("geo:0,0?z=10&q=Schools");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });


        office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Search for Offices nearby
                Uri gmmIntentUri = Uri.parse("geo:0,0?z=10&q=Government Offices");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        Sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number="8428883238";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+number));
                if(ActivityCompat.checkSelfPermission(places.this, Manifest.permission.CALL_PHONE )!= PackageManager.PERMISSION_GRANTED){
                    return;
                }
                startActivity(intent);
            }
        });
        parks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Search for parks nearby
                Uri gmmIntentUri = Uri.parse("geo:0,0?z=10&q=parks");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });


        theater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Search for Theater nearby
                Uri gmmIntentUri = Uri.parse("geo:0,0?z=10&q=Theater");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });


        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Search for Hotel nearby
                Uri gmmIntentUri = Uri.parse("geo:0,0?z=10&q=Hotels");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });


        atm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Search for ATM nearby
                Uri gmmIntentUri = Uri.parse("geo:0,0?z=10&q=ATMs");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });


        groceries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Search for Groceries nearby
                Uri gmmIntentUri = Uri.parse("geo:0,0?z=10&q=Groceries");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });


        clothing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Search for Clothing nearby
                Uri gmmIntentUri = Uri.parse("geo:0,0?z=10&q=Clothing");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        busstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Search for Clothing nearby
                Uri gmmIntentUri = Uri.parse("geo:0,0?z=10&q=Bus Stops");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        railway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Search for Clothing nearby
                Uri gmmIntentUri = Uri.parse("geo:0,0?z=10&q=Railway Station");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }

}