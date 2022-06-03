package com.cris.dashboard.activity;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.cris.dashboard.R;
import com.cris.dashboard.custom.MyAxisValueFormatter;
import com.cris.dashboard.custom.MyValueFormatter;
import com.cris.dashboard.models.TicketTypeList;
import com.cris.dashboard.notimportant.DemoBase;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BarGraphActivity extends DemoBase implements OnSeekBarChangeListener, OnChartValueSelectedListener {

    private BarChart chart;
    private SeekBar seekBarX, seekBarY;
    private TextView tvX, tvY;
    JSONArray arraydata;

    String month;
    String journey;
    String season;
    String platform;
    String ticketsData;
    String totalPlatformtckt;
    String totalseasontckt;
    String totaljourneytckt;
    String totalTickets;
    TicketTypeList ticketItemTypeList;
    ArrayList<BarEntry> ticketTypeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bar_graph);




        ticketsData ="{\"ticketType\":[{\n" +
                "    \"month\": \"Jan\",\n" +
                "    \"journey\": 1200,\n" +
                "    \"season\": 112,\n" +
                "    \"platform\": 850\n" +
                "  },\n" +
                "  {\n" +
                "    \"month\": \"Feb\",\n" +
                "    \"journey\": 1300,\n" +
                "    \"season\": 124,\n" +
                "    \"platform\": 950\n" +
                "  },\n" +
                "  {\n" +
                "    \"month\": \"Mar\",\n" +
                "    \"journey\": 1100,\n" +
                "    \"season\": 156,\n" +
                "    \"platform\": 750\n" +
                "  },\n" +
                "  {\n" +
                "    \"month\": \"Apr\",\n" +
                "    \"journey\": 1000,\n" +
                "    \"season\": 190,\n" +
                "    \"platform\": 1000\n" +
                "  },\n" +
                "  {\n" +
                "    \"month\": \"May\",\n" +
                "    \"journey\": 1500,\n" +
                "    \"season\": 60,\n" +
                "    \"platform\": 850\n" +
                "  },\n" +
                "  {\n" +
                "    \"month\": \"Jun\",\n" +
                "    \"journey\": 1400,\n" +
                "    \"season\": 90,\n" +
                "    \"platform\": 600\n" +
                "  },\n" +
                "  {\n" +
                "    \"month\": \"Jul\",\n" +
                "    \"journey\": 2000,\n" +
                "    \"season\": 120,\n" +
                "    \"platform\": 500\n" +
                "  },\n" +
                "  {\n" +
                "    \"month\": \"Aug\",\n" +
                "    \"journey\": 1800,\n" +
                "    \"season\": 135,\n" +
                "    \"platform\": 950\n" +
                "  },\n" +
                "  {\n" +
                "    \"month\": \"Sep\",\n" +
                "    \"journey\": 2100,\n" +
                "    \"season\": 201,\n" +
                "    \"platform\": 1050\n" +
                "  },\n" +
                "  {\n" +
                "    \"month\": \"Oct\",\n" +
                "    \"journey\": 1800,\n" +
                "    \"season\": 210,\n" +
                "    \"platform\": 600\n" +
                "  },\n" +
                "  {\n" +
                "    \"month\": \"Nov\",\n" +
                "    \"journey\": 1900,\n" +
                "    \"season\": 250,\n" +
                "    \"platform\": 1100\n" +
                "  },\n" +
                "  {\n" +
                "    \"month\": \"Dec\",\n" +
                "    \"journey\": 1700,\n" +
                "    \"season\": 123,\n" +
                "    \"platform\": 750\n" +
                "  }\n" +
                "  ]\n" +
                "}";

        setTitle("Stacked Bar Activity");

        tvX = findViewById(R.id.tvXMax);
        tvY = findViewById(R.id.tvYMax);

        seekBarX = findViewById(R.id.seekBar1);
        seekBarX.setOnSeekBarChangeListener(this);

        seekBarY = findViewById(R.id.seekBar2);
        seekBarY.setOnSeekBarChangeListener(this);

        chart = findViewById(R.id.chart1);
        chart.setOnChartValueSelectedListener(this);

        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                    Intent intent = new Intent(BarGraphActivity.this, PieGraphMonthActivity.class);
                    intent.putExtra("journey", journey);
                    intent.putExtra("season", season);
                    intent.putExtra("platform", platform);
                    startActivity(intent);
                    Log.e(TAG, "journeyOne onProgressChanged: "+journey);
                    Log.e(TAG, "seasonTwo onProgressChanged: "+season);
                    Log.e(TAG, "platformThree onProgressChanged: "+platform);

            }

            @Override
            public void onNothingSelected() {

            }
        });

        chart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(40);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);

        chart.setDrawValueAboveBar(false);
        chart.setHighlightFullBarEnabled(false);

        // change the position of the y-labels
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setValueFormatter(new MyAxisValueFormatter());
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        chart.getAxisRight().setEnabled(false);

//        XAxis xLabels = chart.getXAxis();
//        xLabels.setPosition(XAxis.XAxisPosition.TOP);

        // chart.setDrawXLabels(false);
        // chart.setDrawYLabels(false);

        // setting data
        seekBarX.setProgress(12);
        seekBarY.setProgress(10);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        final String[] monthName = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"}; // Your List / array with String Values For X-axis Labels

// Set the value formatter
       xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(monthName));

        Log.e(TAG, "onCreate: "+monthName);


    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        tvX.setText(String.valueOf(seekBarX.getProgress()));
        tvY.setText(String.valueOf(seekBarY.getProgress()));

        try {
            JSONObject parsedata = new JSONObject(ticketsData);
            System.out.println(parsedata);
            Log.e(TAG, "onCreate: "+parsedata);

            arraydata =parsedata.getJSONArray("ticketType");


            ArrayList<BarEntry> values = new ArrayList<>();

            for (int i=0; i<arraydata.length(); i++){
                JSONObject jsonObject = arraydata.getJSONObject(i);
                month = jsonObject.getString("month");
                journey = jsonObject.getString("journey");
                season = jsonObject.getString("season");
                platform = jsonObject.getString("platform");

                Log.e(TAG, "jsonObject : "+jsonObject);

                if (month.equals("Jan")){
//                    Toast.makeText(BarGraphActivity.this, "You are selecting January Month", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(BarGraphActivity.this, PieGraphMonthActivity.class);
//                    startActivity(intent);
                    Log.e(TAG, "journey onProgressChanged: "+journey);
                    Log.e(TAG, "season onProgressChanged: "+season);
                    Log.e(TAG, "platform onProgressChanged: "+platform);
                }

                else{
//                    Toast.makeText(BarGraphActivity.this, "You are selecting any Month", Toast.LENGTH_LONG).show();
                }

                values.add(new BarEntry(
                        i,
                        new float[]{Float.parseFloat(journey), Float.parseFloat(season), Float.parseFloat(platform)},
                        getResources().getDrawable(R.drawable.star)));


            }

            totalTickets = platform+season+journey;

            Log.e(TAG, "totalPlatformtckt : "+totalPlatformtckt);
            Log.e(TAG, "totalseasontckt : "+totalseasontckt);
            Log.e(TAG, "totaljourneytckt : "+totaljourneytckt);
            Log.e(TAG, "totalTickets : "+totalTickets);
            Log.e(TAG, "arraydata : "+arraydata);


            BarDataSet set1;


            if (chart.getData() != null &&
                    chart.getData().getDataSetCount() > 0) {
                set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
                set1.setValues(values);
                chart.getData().notifyDataChanged();
                chart.notifyDataSetChanged();

            } else {

                set1 = new BarDataSet(values, "Tickets of 2022");
                set1.setDrawIcons(false);
                set1.setColors(getColors());
                set1.setStackLabels(new String[]{"Journey", "Season", "Platform"});

                ArrayList<IBarDataSet> dataSets = new ArrayList<>();
                dataSets.add(set1);

                BarData data = new BarData(dataSets);
                data.setValueFormatter(new MyValueFormatter());
                data.setValueTextColor(Color.WHITE);


                chart.setData(data);

                chart.setFitBars(true);
                chart.invalidate();

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.viewGithub: {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/com/xxmassdeveloper/mpchartexample/StackedBarActivity.java"));
                startActivity(i);
                break;
            }
            case R.id.actionToggleValues: {
                List<IBarDataSet> sets = chart.getData()
                        .getDataSets();

                for (IBarDataSet iSet : sets) {

                    BarDataSet set = (BarDataSet) iSet;
                    set.setDrawValues(!set.isDrawValuesEnabled());
                }

                chart.invalidate();
                break;
            }
            case R.id.actionToggleIcons: {
                List<IBarDataSet> sets = chart.getData()
                        .getDataSets();

                for (IBarDataSet iSet : sets) {

                    BarDataSet set = (BarDataSet) iSet;
                    set.setDrawIcons(!set.isDrawIconsEnabled());
                }

                chart.invalidate();
                break;
            }
            case R.id.actionToggleHighlight: {
                if (chart.getData() != null) {
                    chart.getData().setHighlightEnabled(!chart.getData().isHighlightEnabled());
                    chart.invalidate();
                }
                break;
            }
            case R.id.actionTogglePinch: {
                if (chart.isPinchZoomEnabled())
                    chart.setPinchZoom(false);
                else
                    chart.setPinchZoom(true);

                chart.invalidate();
                break;
            }
            case R.id.actionToggleAutoScaleMinMax: {
                chart.setAutoScaleMinMaxEnabled(!chart.isAutoScaleMinMaxEnabled());
                chart.notifyDataSetChanged();
                break;
            }
            case R.id.actionToggleBarBorders: {
                for (IBarDataSet set : chart.getData().getDataSets())
                    ((BarDataSet) set).setBarBorderWidth(set.getBarBorderWidth() == 1.f ? 0.f : 1.f);

                chart.invalidate();
                break;
            }
            case R.id.animateX: {
                chart.animateX(2000);
                break;
            }
            case R.id.animateY: {
                chart.animateY(2000);
                break;
            }
            case R.id.animateXY: {

                chart.animateXY(2000, 2000);
                break;
            }
            case R.id.actionSave: {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    saveToGallery();
                } else {
                    requestStoragePermission(chart);
                }
                break;
            }
        }
        return true;
    }

    @Override
    protected void saveToGallery() {
        saveToGallery(chart, "StackedBarActivity");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onValueSelected(Entry e, Highlight h) {

        BarEntry entry = (BarEntry) e;

        if (entry.getYVals() != null)
            Log.i("VAL SELECTED", "Value: " + entry.getYVals()[h.getStackIndex()]);
        else
            Log.i("VAL SELECTED", "Value: " + entry.getY());
    }

    @Override
    public void onNothingSelected() {}

    private int[] getColors() {

        // have as many colors as stack-values per entry
        int[] colors = new int[3];

        System.arraycopy(ColorTemplate.MATERIAL_COLORS, 0, colors, 0, 3);

        return colors;
    }
}