package com.example.greenhouse.ui.mushroom;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.greenhouse.R;

import java.util.ArrayList;
import java.util.List;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MushroomFragment extends Fragment {

    Context thiscontext;
    public ApiInterface apiInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layoutView = inflater.inflate(R.layout.fragment_mushroom, container, false);
        com.github.mikephil.charting.charts.LineChart chart = (com.github.mikephil.charting.charts.LineChart) layoutView.findViewById(R.id.mushroomchart);
        chart.setDescription("Mushroom Growth");
        chart.setDescriptionTextSize(12f);
        chart.animateXY(2000, 2000);
        chart.invalidate();
        // set legend
        Legend legend = chart.getLegend();
        legend.setTextSize(12f);
        // set xAxis
        XAxis xAxis = chart.getXAxis();
        xAxis.setTextSize(12f);
        // set yAxis
        YAxis yAxis = chart.getAxisLeft();
        yAxis.setTextSize(12f);
        thiscontext = container.getContext();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        getData();

        LineData data = new LineData();
        chart.setData(data);

        return layoutView;
    }


    private void getData() {
        Call<Chart> piChartCall = apiInterface.init();
        piChartCall.enqueue(new Callback<Chart>() {
            @Override
            public void onResponse(Call<Chart> call, Response<Chart> response) {
                Log.d("CHART_RESPONSE", "" + response.body().getTimestamps().toString());
                setData(response.body());
            }

            @Override
            public void onFailure(Call<Chart> call, Throwable t) {

            }
        });
    }

    private void setData(Chart Chart) {

        ArrayList<LineDataSet> dataSets = null;
        List<Entry> Temp = new ArrayList<>();

        for (int i = 0; i < Chart.getTemperature().size(); i++) {
            Entry value = new Entry(Chart.getTemperature().get(i), i);
            Temp.add(value);
        }


        /**
         *  Add Mushroom 1 data into the line  chart
         */

        LineDataSet TempData = new LineDataSet(Temp, "Mushroom 1 Height (cm)");
        TempData.setValueTextSize(12f);
        TempData.setColor(Color.rgb(80, 2, 88));
        TempData.setCircleColor(Color.rgb(80, 2, 88));

        /**
         *  Getting Pending data
         */

        List<Entry> humidity = new ArrayList<>();
        for (int i = 0; i < Chart.getHumidity().size(); i++) {
            Entry value = new Entry(Chart.getHumidity().get(i), i);
            humidity.add(value);
        }

        /**
         *  Add Mushroom 2 data into the bar chart
         */

        LineDataSet HumidityData = new LineDataSet(humidity, "Mushroom 2 Height (cm)");
        HumidityData.setValueTextSize(12f);
        HumidityData.setColor(Color.rgb(200, 129, 100));
        HumidityData.setCircleColor(Color.rgb(200, 129, 100));

        dataSets = new ArrayList<>();
        dataSets.add(TempData);
        dataSets.add(HumidityData);
        List<String> entries = new ArrayList<>();
        for (String months : Chart.getTimestamps()) {
            Log.d("CHART_RESPONSE", "month: " + months.toString());
            entries.add(months);
        }
        com.github.mikephil.charting.charts.LineChart chart = (com.github.mikephil.charting.charts.LineChart) getView().findViewById(R.id.mushroomchart);
        LineData data = new LineData(entries, dataSets);
        chart.setData(data);
    }
}

