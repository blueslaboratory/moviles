package com.example.e014_apidog;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DogsAPIService {

    String url = "images";
    @GET(url)
    Call<DogRespuesta> getDogs();

}