package com.codacrafts.bmicalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class bmiActivity extends AppCompatActivity {

    TextView mbmidisplay, mbmicategory, mgender;
    Button mgotomain;
    ImageView mimageview;
    RelativeLayout mbackground;

    float intbmi;
    String mbmi;
    String height, weight;
    float intheight, intweight;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);

        // Set up the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
            ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#1E1D1D"));
            getSupportActionBar().setBackgroundDrawable(colorDrawable);
            getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\">Result</font>"));
        }

        // Initialize views
        mbmidisplay = findViewById(R.id.bmidisplay);
        mbmicategory = findViewById(R.id.bmicategorydispaly);
        mgotomain = findViewById(R.id.gotomain);
        mimageview = findViewById(R.id.imageview);
        mgender = findViewById(R.id.genderdisplay);
        mbackground = findViewById(R.id.contentlayout);

        // Retrieve data from the intent
        Intent intent = getIntent();
        height = intent.getStringExtra("height");
        weight = intent.getStringExtra("weight");

        // Calculate BMI
        intheight = Float.parseFloat(height) / 100; // Convert height to meters
        intweight = Float.parseFloat(weight);
        intbmi = intweight / (intheight * intheight);

        // Display BMI value
        mbmi = String.format("%.2f", intbmi); // Format BMI to two decimal places
        mbmidisplay.setText(mbmi);

        // Determine BMI category and update UI accordingly
        updateBMICategory(intbmi);

        // Display gender
        mgender.setText(intent.getStringExtra("gender"));

        // Set up button click listener
        mgotomain.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent1);
        });
    }

    // Method to update the BMI category and UI based on BMI value
    private void updateBMICategory(float bmi) {
        if (bmi < 16) {
            mbmicategory.setText("Severe Thinness");
            mbackground.setBackgroundColor(Color.RED);
            mimageview.setImageResource(R.drawable.crosss);
        } else if (bmi >= 16 && bmi < 17) {
            mbmicategory.setText("Moderate Thinness");
            mbackground.setBackgroundColor(getResources().getColor(R.color.halfwarn));
            mimageview.setImageResource(R.drawable.warning);
        } else if (bmi >= 17 && bmi < 18.5) {
            mbmicategory.setText("Mild Thinness");
            mbackground.setBackgroundColor(getResources().getColor(R.color.halfwarn));
            mimageview.setImageResource(R.drawable.warning);
        } else if (bmi >= 18.5 && bmi < 25) {
            mbmicategory.setText("Normal");
            mimageview.setImageResource(R.drawable.ok);
        } else if (bmi >= 25 && bmi < 30) {
            mbmicategory.setText("Overweight");
            mbackground.setBackgroundColor(getResources().getColor(R.color.halfwarn));
            mimageview.setImageResource(R.drawable.warning);
        } else if (bmi >= 30 && bmi < 35) {
            mbmicategory.setText("Obese Class I");
            mbackground.setBackgroundColor(getResources().getColor(R.color.halfwarn));
            mimageview.setImageResource(R.drawable.warning);
        } else {
            mbmicategory.setText("Obese Class II");
            mbackground.setBackgroundColor(getResources().getColor(R.color.warn));
            mimageview.setImageResource(R.drawable.crosss);
        }
    }
}
