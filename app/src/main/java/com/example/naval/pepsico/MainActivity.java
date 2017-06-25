package com.example.naval.pepsico;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    RatingBar ratingBar;
    Button submitRateButton;
    TextView rateDisplay;
    String ratingValue ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // for hiding title

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        submitRateButton = (Button) findViewById(R.id.ratingSubmitButton);
        rateDisplay = (TextView) findViewById(R.id.editText);

        submitRateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateNoteOnSD(getApplicationContext(),"Pepsi",ratingBar.toString() );
            }
        });

    }

    void alertDialog ()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Thank you for your feedback!");
        alertDialog.setMessage("Thanks for being part of pepsiCo virtual reality tour");
        // Alert dialog button
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Alert dialog action goes here
                        // onClick button code here
                        dialog.dismiss();// use dismiss to cancel alert dialog
                    }
                });
        alertDialog.show();
    }

    public void rateSubmit(View view) {

    }

    public void generateNoteOnSD(Context context, String sFileName, String sBody) {
        try {

            ratingValue = String.valueOf(ratingBar.getRating());
           // rateDisplay.setText("Rate: " + ratingValue);
           // Toast.makeText(getApplicationContext(), "Rate: " + ratingValue, Toast.LENGTH_LONG).show();

            File gpxfile = new File(Environment.getExternalStorageDirectory(), "pepsi.txt");

            Log.d("pepsi","path of file :" +gpxfile.toString());
            Log.d("pepsi","rating star:" +ratingValue.toString());
            Log.d("pepsi","name saved :" +rateDisplay.getText().toString());
            try {
                FileWriter writer = new FileWriter(gpxfile,true);
                writer.append("\n Name :" +rateDisplay.getText().toString() + " Star rating :" +ratingValue.toString());
                writer.flush();
                writer.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            //Toast.makeText(context, "Thank you for your feedback!", Toast.LENGTH_LONG).show();
            alertDialog();
            ratingBar.setRating(0);
            rateDisplay.setText("");

        }finally {

        }
    }
}