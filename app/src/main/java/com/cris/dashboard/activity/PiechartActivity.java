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
    TextView tvjourney, tvseason, tvplatform;
    PieChart pieChart;
    String journey, season, platform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);

        // Link those objects with their
        // respective id's that
        // we have given in .XML file
        tvjourney = findViewById(R.id.tvjourney);
        tvseason = findViewById(R.id.tvseason);
        tvplatform = findViewById(R.id.tvplatform);
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
        tvjourney.setText(journey);
        tvseason.setText(season);
        tvplatform.setText(platform);

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Journey",
                        Integer.parseInt(tvjourney.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Season",
                        Integer.parseInt(tvseason.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Platform",
                        Integer.parseInt(tvplatform.getText().toString()),
                        Color.parseColor("#EF5350")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
}