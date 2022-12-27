package com.example.greenhouse.ui.gallery;

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

public class GalleryFragment extends Fragment {

    Context thiscontext;
    public ApiInterface apiInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layoutView = inflater.inflate(R.layout.fragment_gallery, container, false);
        com.github.mikephil.charting.charts.LineChart chart = (com.github.mikephil.charting.charts.LineChart) layoutView.findViewById(R.id.barchart);
        com.github.mikephil.charting.charts.LineChart chart2 = (com.github.mikephil.charting.charts.LineChart) layoutView.findViewById(R.id.barchart2);
        com.github.mikephil.charting.charts.LineChart chart3 = (com.github.mikephil.charting.charts.LineChart) layoutView.findViewById(R.id.barchart3);
        chart.setDescription("Temperature in °C");
        chart.setDescriptionTextSize(14f);
        chart.animateXY(2000, 2000);
        chart.getLegend().setEnabled(false);
        //chart.invalidate();
        // set legend
//      Legend legend = chart.getLegend();
//      legend.setTextSize(12f);
        // set xAxis
        XAxis xAxis = chart.getXAxis();
        xAxis.setTextSize(12f);
        // set yAxis
        YAxis yAxis = chart.getAxisLeft();
        YAxis rightYAxis = chart.getAxisRight();
        rightYAxis.setEnabled(false);
        //chart.getAxisLeft().setDrawGridLines(false);
//        chart.getXAxis().setDrawGridLines(false);
        xAxis.setDrawGridLines(false);
        //yAxis.setDrawGridLines(false);
        yAxis.setStartAtZero(false);
        yAxis.setAxisMaxValue(80);
        yAxis.setAxisMinValue(20);
        yAxis.setTextSize(12f);


        //Set chart 2
        chart2.setDescription("Humidity in %");
        chart2.setDescriptionTextSize(14f);
        chart2.animateXY(2000, 2000);
        chart2.getLegend().setEnabled(false);
        //chart.invalidate();
        // set legend
//        Legend legend2 = chart.getLegend();
//        legend2.setTextSize(12f);
        // set xAxis
        XAxis xAxis2 = chart2.getXAxis();
        xAxis2.setTextSize(12f);
        xAxis2.setDrawGridLines(false);
        // set yAxis
        YAxis yAxis2 = chart.getAxisLeft();
        YAxis rightYAxis2 = chart2.getAxisRight();
        rightYAxis2.setEnabled(false);
        yAxis2.setTextSize(12f);
        yAxis2.setStartAtZero(false);
        yAxis2.setAxisMaxValue(80);
        yAxis2.setAxisMinValue(20);

        //Set chart 3
        chart3.setDescription("Power in W");
        chart3.setDescriptionTextSize(14f);
        chart3.animateXY(2000, 2000);
        chart3.getLegend().setEnabled(false);

        //chart.invalidate();
        // set legend
//        Legend legend3 = chart.getLegend();
//        legend3.setTextSize(12f);
        // set xAxis
        XAxis xAxis3 = chart3.getXAxis();
        xAxis3.setTextSize(12f);
        xAxis3.setDrawGridLines(false);
        // set yAxis
        YAxis yAxis3 = chart3.getAxisLeft();
        YAxis rightYAxis3 = chart3.getAxisRight();
        rightYAxis3.setEnabled(false);
        yAxis3.setTextSize(12f);

        thiscontext = container.getContext();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        LineData data = new LineData();
        chart.setData(data);
        chart2.setData(data);
        chart3.setData(data);
        getData();
        return layoutView;
    }


    private void getData() {
        Call<Chart> piChartCall = apiInterface.init();
        piChartCall.enqueue(new Callback<Chart>() {
            @Override
            public void onResponse(Call<Chart> call, Response<Chart> response) {
                Log.d("CHART_RESPONSE", "" + response.body().getTimestamps().toString());
                setDatatemp(response.body());
                setDatahum(response.body());
                setDataCur(response.body());
                //setData(response.body());
            }

            @Override
            public void onFailure(Call<Chart> call, Throwable t) {

            }
        });
    }

//    private void setData(Chart Chart) {
//
//        ArrayList<LineDataSet> dataSets = null;
//
//
//        List<Entry> Temp = new ArrayList<>();
//
//        for (int i = 0; i < Chart.getTemperature().size(); i++) {
//            Entry value = new Entry(Chart.getTemperature().get(i), i);
//            Temp.add(value);
//        }
//
//
//        /**
//         *  Add Tempterture data into the line  chart
//         */
//
//        LineDataSet TempData = new LineDataSet(Temp, "Temperature (°C)");
//        TempData.setValueTextSize(12f);
//        TempData.setColor(Color.rgb(0, 155, 0));
//        TempData.setCircleColor(Color.rgb(0, 155, 0));
//
//
//        /**/
//        List<Entry> Cur = new ArrayList<>();
//
//        for (int i = 0; i < Chart.getCurrent().size(); i++) {
//            Entry value = new Entry(Chart.getCurrent().get(i), i);
//            Cur.add(value);
//        }
//
//
//        /**
//         *  Add Current data into the line  chart
//         */
//
//        LineDataSet CurData = new LineDataSet(Cur, "Power (W)");
//        CurData.setValueTextSize(12f);
//        CurData.setColor(Color.rgb(200, 50, 200));
//        CurData.setCircleColor(Color.rgb(200, 50, 200));
//
//
//
//        /**
//         *  Getting Pending data
//         */
//
//        List<Entry> humidity = new ArrayList<>();
//        for (int i = 0; i < Chart.getHumidity().size(); i++) {
//            Entry value = new Entry(Chart.getHumidity().get(i), i);
//            humidity.add(value);
//        }
//
//
//        /**
//         *  Add Humidity data into the bar chart
//         */
//
//        LineDataSet HumidityData = new LineDataSet(humidity, "Relative Humidity (%) ");
//        HumidityData.setValueTextSize(12f);
//        HumidityData.setColor(Color.rgb(253, 129, 0));
//        HumidityData.setCircleColor(Color.rgb(253, 129, 0));
//
//        dataSets = new ArrayList<>();
//        dataSets.add(TempData);
//        dataSets.add(HumidityData);
//        dataSets.add(CurData);
//
//        List<String> entries = new ArrayList<>();
//        for (String months : Chart.getTimestamps()) {
//            Log.d("CHART_RESPONSE", "month: " + months.toString());
//            entries.add(months);
//        }
//        com.github.mikephil.charting.charts.LineChart chart = (com.github.mikephil.charting.charts.LineChart) getView().findViewById(R.id.barchart);
//        //com.github.mikephil.charting.charts.LineChart chart2 = (com.github.mikephil.charting.charts.LineChart) getView().findViewById(R.id.barchart2);
//        //com.github.mikephil.charting.charts.LineChart chart3 = (com.github.mikephil.charting.charts.LineChart) getView().findViewById(R.id.barchart3);
//        LineData data = new LineData(entries, dataSets);
//        chart.setData(data);
////        chart2.setData(data);
////        chart3.setData(data);
//    }

private void setDatatemp(Chart Chart) {

    ArrayList<LineDataSet> dataSets = null;


    List<Entry> Temp = new ArrayList<>();

    for (int i = 0; i < Chart.getTemperature().size(); i++) {
        Entry value = new Entry(Chart.getTemperature().get(i), i);
        Temp.add(value);
    }

    LineDataSet TempData = new LineDataSet(Temp, "Temperature (°C)");
    TempData.setValueTextSize(12f);
    TempData.setColor(Color.rgb(0, 155, 0));
    TempData.setCircleColor(Color.rgb(0, 155, 0));

      dataSets = new ArrayList<>();
      dataSets.add(TempData);

    List<String> entries = new ArrayList<>();
    for (String months : Chart.getTimestamps()) {
        Log.d("CHART_RESPONSE", "month: " + months.toString());
        entries.add(months);
    }
    com.github.mikephil.charting.charts.LineChart chart = (com.github.mikephil.charting.charts.LineChart) getView().findViewById(R.id.barchart);
    LineData data = new LineData(entries, dataSets);
    chart.setData(data);
}



    private void setDatahum(Chart Chart) {

        ArrayList<LineDataSet> dataSets = null;

    List<Entry> humidity = new ArrayList<>();
    for (int i = 0; i < Chart.getHumidity().size(); i++) {
        Entry value = new Entry(Chart.getHumidity().get(i), i);
        humidity.add(value);
    }


    /**
     *  Add Humidity data into the bar chart
     */

    LineDataSet HumidityData = new LineDataSet(humidity, "Relative Humidity (%) ");
    HumidityData.setValueTextSize(12f);
    HumidityData.setColor(Color.rgb(253, 129, 0));
    HumidityData.setCircleColor(Color.rgb(253, 129, 0));
//
        dataSets = new ArrayList<>();
        dataSets.add(HumidityData);

        List<String> entries = new ArrayList<>();

        for (String months : Chart.getTimestamps()) {
            Log.d("CHART_RESPONSE", "month: " + months.toString());
            entries.add(months);
        }

        com.github.mikephil.charting.charts.LineChart chart2 = (com.github.mikephil.charting.charts.LineChart) getView().findViewById(R.id.barchart2);
        LineData data2 = new LineData(entries, dataSets);
        chart2.setData(data2);
    }

    private void setDataCur(Chart Chart) {

        ArrayList<LineDataSet> dataSets = null;

        List<Entry> Cur = new ArrayList<>();

        for (int i = 0; i < Chart.getCurrent().size(); i++) {
            Entry value = new Entry(Chart.getCurrent().get(i), i);
            Cur.add(value);
        }


        /**
         *  Add Current data into the line  chart
         */

        LineDataSet CurData = new LineDataSet(Cur, "Power (W)");
        CurData.setValueTextSize(12f);
        CurData.setColor(Color.rgb(200, 50, 200));
        CurData.setCircleColor(Color.rgb(200, 50, 200));

        dataSets = new ArrayList<>();
        dataSets.add(CurData);

        List<String> entries = new ArrayList<>();

        for (String months : Chart.getTimestamps()) {
            Log.d("CHART_RESPONSE", "month: " + months.toString());
            entries.add(months);
        }

        com.github.mikephil.charting.charts.LineChart chart3 = (com.github.mikephil.charting.charts.LineChart) getView().findViewById(R.id.barchart3);
        LineData data3 = new LineData(entries, dataSets);
        chart3.setData(data3);
    }




}
