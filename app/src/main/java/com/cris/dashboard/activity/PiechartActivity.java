package com.cris.dashboard.activity;

// Import the required libraries
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.cris.dashboard.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class PiechartActivity extends AppCompatActivity {
    TextView tvR, tvPython, tvCPP;
    PieChart pieChart;
    String journey, season, platform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);

        // Link those objects with their
        // respective id's that
        // we have given in .XML file
        tvR = findViewById(R.id.tvR);
        tvPython = findViewById(R.id.tvPython);
        tvCPP = findViewById(R.id.tvCPP);
        pieChart = findViewById(R.id.piechart);

        setTitle("Pie Chart Activity");

        // Creating a method setData()
        // to set the text in text view and pie chart
        setData();
    }

    @SuppressLint("SetTextI18n")
    private void setData()
    {

        Intent intent = getIntent();
        journey = intent.getStringExtra("journey");
        season = intent.getStringExtra("season");
        platform = intent.getStringExtra("platform");

        Log.e("TAG", "onCreate: "+journey);
        Log.e("TAG", "season: "+season);
        Log.e("TAG", "platform: "+platform);


        // Set the percentage of language used
        tvR.setText(journey);
        tvPython.setText(season);
        tvCPP.setText(platform);

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Journey",
                        Integer.parseInt(tvR.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Season",
                        Integer.parseInt(tvPython.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Platform",
                        Integer.parseInt(tvCPP.getText().toString()),
                        Color.parseColor("#EF5350")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
}