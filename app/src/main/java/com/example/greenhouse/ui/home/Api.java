package com.example.greenhouse.ui.home;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface Api {
    String BASE_URL = "http://10.0.2.2/~andrewchao/phpMyAdmin-5.0.4/"; //change URL
    @GET("Apppi.php")
    Call<List<Details>> getstatus();
}
