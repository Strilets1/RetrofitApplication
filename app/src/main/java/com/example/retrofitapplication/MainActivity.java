package com.example.retrofitapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.retrofitapplication.Adapter.RecyclerViewAdapter;
import com.example.retrofitapplication.Api.MesageApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rawgit.com/startandroid/data/master/messages/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MesageApi mesageApi = retrofit.create(MesageApi.class);

        final Call<List<Message>> message = mesageApi.message();
        message.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if(response.isSuccessful()){
                    Log.d("debug", "onResponse: " + response.body().size());
                    Log.d("gay", "onResponse: " + response.body().get(0).getText());
                    //***********************************************
                    //List<Message> messages = response.body();
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
                    RecyclerView recyclerView = findViewById(R.id.recycler);
                    recyclerView.setLayoutManager(layoutManager);
                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(response.body(),getBaseContext());
                    recyclerView.setAdapter(adapter);

                } else {
                    Log.d("debug", "Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Log.d("debug", "onFailure: " + t);
            }
        });


    }
}
