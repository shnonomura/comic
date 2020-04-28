package com.shn.comic01;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.shn.comic01.model.ComicModel;
import com.shn.comic01.service.ComicService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private String urlImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://xkcd.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        urlImg = acaoBuscaRetrofit("");
        Log.d( "Url Imagem: " , urlImg);

    } // fim onCreate


    public String acaoBuscaRetrofit( String num ) {

        String url="";

        ComicService comicService = retrofit.create(ComicService.class);
        Call<List<ComicModel>> call = comicService.buscarComic(num);

        call.enqueue(new Callback<List<ComicModel>>() {

            @Override
            public void onResponse(Call<List<ComicModel>> call, Response<List<ComicModel>> response) {
                if(response.isSuccessful()){
                    ArrayList<ComicModel> comicData = (ArrayList) response.body();
                    ComicModel url = (ComicModel) comicData.get(8);
                }
            }

            @Override
            public void onFailure(Call<List<ComicModel>> call, Throwable t) {

            }
        });

        return url;

    } // fim acaoPost


} // fim MainActivity
