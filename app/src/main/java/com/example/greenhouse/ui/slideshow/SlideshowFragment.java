package com.example.greenhouse.ui.slideshow;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.greenhouse.R;
import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;

import com.example.greenhouse.ui.home.Api;
import com.example.greenhouse.ui.home.Details;
import com.example.greenhouse.ui.mushroom.ApiClient;
import com.example.greenhouse.ui.mushroom.ApiInterface;
import com.example.greenhouse.ui.mushroom.Chart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class SlideshowFragment extends Fragment {
    Context thiscontext;
    private ListViewAdapter adapter;
    private ListView mListView;
    TextView imageTime;
    Button retrieveBtn2;
    public ApiInterface apiInterface;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.fragment_slideshow, container, false);
        thiscontext = container.getContext();
        mListView = layoutView.findViewById(R.id.mListView);
        imageTime = layoutView.findViewById(R.id.imageTime);
        //retrieveBtn2 = (Button) layoutView.findViewById(R.id.retrievebtn2);
        //retrieveBtn2.setOnClickListener(this);

//        com.github.mikephil.charting.charts.LineChart chart = (com.github.mikephil.charting.charts.LineChart) layoutView.findViewById(R.id.mushroomchart2);
//        chart.setDescription("Mushroom Growth");
//        chart.setDescriptionTextSize(12f);
//        chart.animateXY(2000, 2000);
//        chart.invalidate();
//        // set legend
//        Legend legend = chart.getLegend();
//        legend.setTextSize(12f);
//        // set xAxis
//        XAxis xAxis = chart.getXAxis();
//        xAxis.setTextSize(12f);
//        // set yAxis
//        YAxis yAxis = chart.getAxisLeft();
//        yAxis.setTextSize(12f);
//        thiscontext = container.getContext();
//        apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        getData();
//
//        LineData data = new LineData();
//        chart.setData(data);


        fetchImage();
        fetchData();
        return layoutView;
    }

    private static final String BASE_URL = "http://10.0.2.2";  // change this
    //private static final String BASE_URL = "http://192.168.1.73";  // change this
    private static final String FULL_URL = BASE_URL+"/~andrewchao/phpMyAdmin-5.0.4/"; //change
    class Image {
        @SerializedName("image_url")  //change this
        private String imageURL;
        public Image(String imageURL) {
            this.imageURL = imageURL;
        }
        public String getImageURL() {
            return imageURL;
        }
    }
    interface MyAPIService {
        @GET("/~andrewchao/phpMyAdmin-5.0.4/image3.php")  //change this
        Call<List<Image>> getSpacecrafts();
    }

    static class RetrofitClientInstance {
        private static Retrofit retrofit;
        public static Retrofit getRetrofitInstance() {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }

    class ListViewAdapter extends BaseAdapter{
        private List<Image> image_data;
        private Context context;

        public ListViewAdapter(Context context,List<Image> spacecrafts){
            this.context = context;
            this.image_data = spacecrafts;
       }

        @Override
        public int getCount() {
            return image_data.size();
        }

        @Override
        public Object getItem(int pos) {
            return image_data.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            return pos;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if(view==null)
            {
                view=LayoutInflater.from(context).inflate(R.layout.model,viewGroup,false);
            }

            ImageView spacecraftImageView = view.findViewById(R.id.ImageView);

            Image thisSpacecraft= image_data.get(position);

            if(thisSpacecraft.getImageURL() != null && thisSpacecraft.getImageURL().length()>0)
            {
                //Picasso.get().load(FULL_URL+"/images/").networkPolicy(NetworkPolicy.NO_STORE).memoryPolicy(MemoryPolicy.NO_STORE).placeholder(R.drawable.placeholder).into(spacecraftImageView);
                Picasso.get().load(FULL_URL+"/images/"+thisSpacecraft.getImageURL()).networkPolicy(NetworkPolicy.NO_STORE).memoryPolicy(MemoryPolicy.NO_STORE).placeholder(R.drawable.placeholder).into(spacecraftImageView);
            }else {
                Toast.makeText(context, "Empty Image URL", Toast.LENGTH_LONG).show();
                Picasso.get().load(R.drawable.placeholder).into(spacecraftImageView);
            }
            return view;
        }
    }

    private void populateListView(List<Image> spacecraftList) {
        adapter = new ListViewAdapter(thiscontext,spacecraftList);
        mListView.setAdapter(adapter);
    }


//    private void getData() {
//        Call<Chart> piChartCall = apiInterface.init();
//        piChartCall.enqueue(new Callback<Chart>() {
//            @Override
//            public void onResponse(Call<Chart> call, Response<Chart> response) {
//                Log.d("CHART_RESPONSE", "" + response.body().getTimestamps().toString());
//                setData(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<Chart> call, Throwable t) {
//
//            }
//        });
//    }

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
        //com.github.mikephil.charting.charts.LineChart chart = (com.github.mikephil.charting.charts.LineChart) getView().findViewById(R.id.mushroomchart2);
        LineData data = new LineData(entries, dataSets);
        //chart.setData(data);
    }


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        /*Create handle for the RetrofitInstance interface*/
//        MyAPIService myAPIService = RetrofitClientInstance.getRetrofitInstance().create(MyAPIService.class);
//        Call<List<Image>> call = myAPIService.getSpacecrafts();
//        call.enqueue(new Callback<List<Image>>() {
//            @Override
//            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
//                populateListView(response.body());
////                 List<Image> imageList = response.body();
////                 ImageView view1 = getView().findViewById(R.id.ImageView);
////                Image finalimage= imageList.get(0);
////               Picasso.get().load(FULL_URL+"/images/"+finalimage.getImageURL()).placeholder(R.drawable.placeholder).into(view1);
//
//            }
//            @Override
//            public void onFailure(Call<List<Image>> call, Throwable throwable) {
//                //myProgressBar.setVisibility(View.GONE);
//                //Toast.makeText(thiscontent, throwable.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }

//    @Override
//    public void onClick(View v) {
//        fetchImage();
//        fetchData();
//    }



//    public  void fetchImage(){
//        File F = new File (BASE_URL+"/images/"+"mushroom3.jpg");
//        ImageView view1 = (ImageView) getView().findViewById(R.id.ImageView);
//        Picasso.get().load(F).networkPolicy(NetworkPolicy.NO_STORE).memoryPolicy(MemoryPolicy.NO_STORE).into(view1);
//
////        MyAPIService myAPIService = RetrofitClientInstance.getRetrofitInstance().create(MyAPIService.class);
////        Call<List<Image>> call = myAPIService.getSpacecrafts();
////        call.enqueue(new Callback<List<Image>>() {
////            @Override
////            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
////                populateListView(response.body());
////            }
////            @Override
////            public void onFailure(Call<List<Image>> call, Throwable throwable) {
////                //Toast.makeText(thiscontent, throwable.getMessage(), Toast.LENGTH_LONG).show();
////            }
////        });
//    }

    public  void fetchImage(){

        MyAPIService myAPIService = RetrofitClientInstance.getRetrofitInstance().create(MyAPIService.class);
        Call<List<Image>> call = myAPIService.getSpacecrafts();
        call.enqueue(new Callback<List<Image>>() {
            @Override
            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                populateListView(response.body());
            }
            @Override
            public void onFailure(Call<List<Image>> call, Throwable throwable) {
                //Toast.makeText(thiscontent, throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    // For timestamps calling
    public interface Apii {
        String BASE_URL2 = "http://10.0.2.2/~andrewchao/phpMyAdmin-5.0.4/"; //change URL
        @GET("Apppi.php")
        Call<List<Details_2>> getstatus();
    }

    public class Details_2 {
        @SerializedName("timestamps")
        @Expose
        private String Timestamps;
        public String getTimestamps() {
            return Timestamps;
        }
        public void setTimestamps(String timestamps) {
            Timestamps = timestamps;
        }
    }

    private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Apii.BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Apii apii = retrofit.create(Apii.class);
        Call<List<Details_2>> call = apii.getstatus();
        call.enqueue(new Callback<List<Details_2>>() {
            @Override
            public void onResponse(Call<List<Details_2>> call, Response<List<Details_2>> response) {
                List<Details_2> adslist = response.body();
                String timestamps = adslist.get(0).getTimestamps();
                imageTime.setText(timestamps);
            }
            @Override
            public void onFailure(Call<List<Details_2>> call, Throwable t) {
                Toast.makeText(thiscontext, ""+t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}