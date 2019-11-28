package com.example.retrofitapplication.Api;

import com.example.retrofitapplication.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MesageApi {
    @GET("messages1.json")
    Call<List<Message>> message();
}
