package com.example.aahaarapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

//import com.alan.alansdk.AlanCallback;
//import com.alan.alansdk.AlanConfig;
//import com.alan.alansdk.button.AlanButton;
//import com.alan.alansdk.events.EventCommand;
import com.alan.alansdk.AlanCallback;
import com.alan.alansdk.AlanConfig;
import com.alan.alansdk.button.AlanButton;
import com.alan.alansdk.events.EventCommand;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    CardView receive,ola,uber, logout, about, volunteer,  publicPlaces , emergency,restaurants;
    Button Sos;

    TextToSpeech tts;

    private ImageView iv_mic;
    private TextView tv_Speech_to_text;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;

    private static final int REQUEST_LOCATION = 1;
    //   Button btnGetLocation;
    //   TextView showLocation;
    LocationManager locationManager;
    double latitude, longitude;

    // Add AlanButton variable
    private AlanButton alanButton;
    private Location mLastKnownLocation;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                tts.setLanguage(Locale.US);
                tts.speak("Welcome to Dashboard",TextToSpeech.QUEUE_ADD,null);
            }

        });


        Toast.makeText(MainActivity.this, "Welcome to buddy guide",Toast.LENGTH_SHORT).show();

        restaurants=findViewById(R.id.restaurants);
        emergency=findViewById(R.id.emergency);
     //   donate = findViewById(R.id.cardDonate);
        ola=findViewById(R.id.ola);
        uber=findViewById(R.id.uber);
      //  receive = findViewById(R.id.cardReceive);
        logout = findViewById(R.id.cardLogout);

        publicPlaces= findViewById(R.id.publicPlaces);

        about = findViewById(R.id.cardAboutus);
        volunteer = findViewById(R.id.volunteer);
        Sos=findViewById(R.id.Sos);


        fAuth= FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() ==null){
            Intent intent = new Intent(MainActivity.this, landingpage.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        iv_mic = findViewById(R.id.iv_mic);
        //tv_Speech_to_text = findViewById(R.id.tv_speech_to_text);


        iv_mic.callOnClick();
        iv_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                tts.speak("Speak",TextToSpeech.QUEUE_ADD,null);
                Intent intent
                        = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                        Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

                try {
                    startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
                }
                catch (Exception e) {
                    Toast
                            .makeText(MainActivity.this, " " + e.getMessage(),
                                    Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

//        @Override
//                protected onActivityResult();

//


        // Define the project key
        AlanConfig config = AlanConfig.builder().setProjectId("bcdc38434a59145532faf990e1370f1f2e956eca572e1d8b807a3e2338fdd0dc/stage").build();
        alanButton = findViewById(R.id .alan_button);
        alanButton.initWithConfig(config);


        AlanCallback alanCallback = new AlanCallback() {
//            /// Handle commands from Alan Studio
            @Override
            public void onCommand(final EventCommand eventCommand) {
                try {
                    JSONObject command = eventCommand.getData();
                    String commandName = command.getJSONObject("data").getString("command");
                    Log.d("AlanButton", "onCommand: commandName: " + commandName);
                } catch (JSONException e) {
                    Log.e("AlanButton", e.getMessage());
                }
            }
        };



/// Register callbacks
      //  alanButton.registerCallback(alanCallback);

        restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.speak("restaurents",TextToSpeech.QUEUE_ADD,null);
                // Search for restaurants nearby
                Uri gmmIntentUri = Uri.parse("geo:0,0?z=10&q=Restrarents");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
              //  mapIntent.setPackage("");
                startActivity(mapIntent);
            }
        });

        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.speak("Hospitals",TextToSpeech.QUEUE_ADD,null);
                // Search for hospitals nearby
                Uri gmmIntentUri = Uri.parse("geo:0,0?z=10&q=hospitals , pharmacy");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.speak("restrooms",TextToSpeech.QUEUE_ADD,null);
                // Search for hospitals nearby
                Uri gmmIntentUri = Uri.parse("geo:0,0?z=10&q=restrooms , bathrooms");
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
                if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE )!= PackageManager.PERMISSION_GRANTED){
                    return;
                }
                startActivity(intent);
            }
        });

        uber.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("https://www.uber.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
            }
        });

        ola.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("https://www.olacabs.com/s?k=chennai");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
            }
        });
//        receive.setOnClickListener(new View.OnClickListener ()
//        {
//
//            @Override
//            public void onClick(View v) {
//                tts.speak("receive the food",TextToSpeech.QUEUE_ADD,null);
//                Intent intent = new Intent(getApplicationContext(), Receive.class);
//                startActivity(intent);
//            }
//        });


        about.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v) {
                tts.speak("about",TextToSpeech.QUEUE_ADD,null);
                Intent intent = new Intent(getApplicationContext(), About.class);
                startActivity(intent);
            }
        });

        publicPlaces.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v) {

//                    latitude=mLastKnownLocation.getLatitude();
//                    longitude=mLastKnownLocation.getLongitude();
//
//                    System.out.println(latitude + " " + longitude);
//
//                        //main thing..
//                        String strUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude + " (" + "Label which you want" + ")";
//                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
//
//                        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
//
//                        startActivity(intent);

//
//                // Search for restaurants nearby
//                Uri gmmIntentUri = Uri.parse("geo:0,0?z=10");
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                mapIntent.setPackage("com.google.android.apps.maps");
//                startActivity(mapIntent);
                tts.speak(" Public places",TextToSpeech.QUEUE_ADD, null);
                Intent intent = new Intent(getApplicationContext(), places.class);
                startActivity(intent);


                    }
                }
            );


        logout.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v) {
                tts.speak("Logged out Succesfully",TextToSpeech.QUEUE_ADD,null);
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, landingpage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
             String var=Objects.requireNonNull(result).get(0);
             System.out.println(var);
          //   Log.d(null,var);
           //   tv_Speech_to_text.setText(Objects.requireNonNull(result).get(0));
               tts.speak("thank you",TextToSpeech.QUEUE_ADD, null);
           // String    var="sos";
                //  ;var=="SOS"&&var=="sos
               //System.out.println(var);


                System.out.println(var);
                if(var.equals("SOS")){

                    String number="8124832438";
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:"+number));
                    if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE )!= PackageManager.PERMISSION_GRANTED){
                        return;
                    }
                    startActivity(intent);

                }

                else if (var.equals("OLA") || var.equals("Ola")){

                    tts.speak("Welcome to Cab Section",TextToSpeech.QUEUE_ADD, null);
                    Uri webpage = Uri.parse("https://www.olacabs.com/s?k=chennai");
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                    startActivity(webIntent);
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tts.speak(" Enter your Destination address ",TextToSpeech.QUEUE_ADD, null);



                }

                else if (var.equals("UBER") || var.equals("uber")){
                    tts.speak("Welcome to Cab Section",TextToSpeech.QUEUE_ADD, null);
                    Uri webpage = Uri.parse("https://www.uber.com");
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                    startActivity(webIntent);
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tts.speak(" Enter your Destination address ",TextToSpeech.QUEUE_ADD, null);



                }

              else if (var.equals("PUBLIC PLACES") || var.equals("public places")){

                    tts.speak(" Public places",TextToSpeech.QUEUE_ADD, null);
                    Intent intent = new Intent(getApplicationContext(), places.class);
                    startActivity(intent);

                }





                else if (var.equals("EMERGENCY")|| var.equals("emergency")){

                    tts.speak("Hospitals",TextToSpeech.QUEUE_ADD,null);
                    // Search for hospitals nearby
                    Uri gmmIntentUri = Uri.parse("geo:0,0?z=10&q=hospitals , pharmacy");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }

                else if (var.equals("RESTAURANTS")|| var.equals("restaurants")){

                    tts.speak("restaurents",TextToSpeech.QUEUE_ADD,null);
                    // Search for restaurants nearby
                    Uri gmmIntentUri = Uri.parse("geo:0,0?z=10&q=Restrarents");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    //  mapIntent.setPackage("");
                    startActivity(mapIntent);

                }

                else if (var.equals("LOGOUT")|| var.equals("log out")){

                    tts.speak("Logged out Succesfully",TextToSpeech.QUEUE_ADD,null);
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(MainActivity.this, landingpage.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }

               else if (var.equals("ABOUT US")|| var.equals("about us")){

                   tts.speak("Logged out Succesfully",TextToSpeech.QUEUE_ADD,null);
                   FirebaseAuth.getInstance().signOut();
                   Intent intent = new Intent(MainActivity.this, landingpage.class);
                   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                   startActivity(intent);

               }












//             //  Boolean status=true;
//                String str;
//                switch(var) {
//                    case str=var.equals("SOS"):
//                        String number="8124832438";
//                        Intent intent = new Intent(Intent.ACTION_CALL);
//                        intent.setData(Uri.parse("tel:"+number));
//                        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE )!= PackageManager.PERMISSION_GRANTED){
//                            return;
//                        }
//                        startActivity(intent);
//                        // code block
//                        break;
//
//                    case var.equals("PUBLIC PLACES"):
//                        tts.speak(" Public places",TextToSpeech.QUEUE_ADD, null);
//                         intent = new Intent(getApplicationContext(), places.class);
//                        startActivity(intent);
//                        startActivity(intent);
//                        // code block
//                        break;
//
//                    default:
//                        // code block
//                }


            }
        }
    }




    public void confirm(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure,do you want to make a Call");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                    //            Toast.makeText(MainActivity.this," Calling",Toast.LENGTH_LONG).show();

                            }
                        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
              //  finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed()
    {

        // Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(MainActivity.this);

        // Set the message show for the Alert time
        builder.setMessage("Do you want to exit ?");

        // Set Alert Title
        builder.setTitle("Alert !");

        // Set Cancelable false
        // for when the user clicks on the outside
        // the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name
        // OnClickListener method is use of
        // DialogInterface interface.

        builder
                .setPositiveButton(
                        "Yes",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {

                                // When the user click yes button
                                // then app will close
                                finish();
                            }
                        });

        // Set the Negative button with No name
        // OnClickListener method is use
        // of DialogInterface interface.
        builder
                .setNegativeButton(
                        "No",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {

                                // If user click no
                                // then dialog box is canceled.
                                dialog.cancel();
                            }
                        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();
    }




    //testing
//    public void openWebPage(String url) {
//        Uri webpage = Uri.parse(url);
//        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }


}
