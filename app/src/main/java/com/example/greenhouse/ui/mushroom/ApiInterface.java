package com.example.greenhouse.ui.mushroom;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("graph2.php")  //change this
    Call<Chart> init();
}

