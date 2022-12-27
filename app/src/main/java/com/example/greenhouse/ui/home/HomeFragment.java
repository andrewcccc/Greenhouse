package com.example.greenhouse.ui.home;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.greenhouse.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment implements View.OnClickListener {

    TextView temperaturetxt, humiditytxt, timestampstxt, currenttxt, heatertxt, humidifiertxt;
    Button retrieveBtn;
    Context thiscontext;
    ProgressBar progressBar1, progressBar2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layoutView = inflater.inflate(R.layout.fragment_home, container, false);
        thiscontext = container.getContext();
        temperaturetxt = (TextView) layoutView.findViewById(R.id.temperaturetxt);
        humiditytxt = (TextView) layoutView.findViewById(R.id.humiditytxt);
        timestampstxt = (TextView) layoutView.findViewById(R.id.timestampstxt);
        currenttxt = (TextView) layoutView.findViewById(R.id.currenttxt);
        heatertxt = (TextView) layoutView.findViewById(R.id.heatertxt);
        humidifiertxt =  (TextView) layoutView.findViewById(R.id.humidifiertxt);
        retrieveBtn = (Button) layoutView.findViewById(R.id.retrieveBtn);
        retrieveBtn.setOnClickListener(this);


        progressBar1 = (ProgressBar) layoutView.findViewById(R.id.progressBar);
        progressBar1.setSecondaryProgress(45);

        progressBar2 = (ProgressBar) layoutView.findViewById(R.id.progressBar2);
        progressBar2.setSecondaryProgress(100);
        return layoutView;
    }

    @Override
    public void onClick(View v) {
                fetchData();
    }

    private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Details>> call = api.getstatus();
        call.enqueue(new Callback<List<Details>>() {
            @Override
            public void onResponse(Call<List<Details>> call, Response<List<Details>> response) {
                List<Details> adslist = response.body();

                String temperature = adslist.get(0).getTemperature();
                String humidity = adslist.get(0).getHumidity();
                String timestamps = adslist.get(0).getTimestamps();
                Float current = adslist.get(0).getCurrent();
                String heater = adslist.get(0).getHeater();
                String humidifier = adslist.get(0).getHumidifier();

                float power = current * 120;

                temperaturetxt.setText(temperature);
                humiditytxt.setText(humidity);
                timestampstxt.setText(timestamps);
                currenttxt.setText(""+power);
                heatertxt.setText(heater);
                humiditytxt.setText(humidifier);

            }
            @Override
            public void onFailure(Call<List<Details>> call, Throwable t) {
                Toast.makeText(thiscontext, ""+t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}