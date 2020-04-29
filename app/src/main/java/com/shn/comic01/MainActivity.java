package com.shn.comic01;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.shn.comic01.model.ComicModel;
import com.shn.comic01.service.ComicService;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ImageView mimageView;
    private Button mbuttonComic;
    private Retrofit retrofit;
    private String urlImg;
    private Integer numberComic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Integer availableComics = 0;
        String imageDesired = "";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mimageView = findViewById(R.id.comicInicial);
        mbuttonComic = findViewById(R.id.buscaComic);

        acaoBuscaRetrofit("");

        mbuttonComic.setOnClickListener(new View.OnClickListener() {

            private Integer novoId;

            @Override
            public void onClick(View v) {

                novoId = createRandomNumber();
                acaoBuscaRetrofit(novoId.toString());

            }

        }); // fim do mbuttonComic

    } // fim onCreate


    public void acaoBuscaRetrofit(String snum) {

        retrofit = new Retrofit.Builder()
                .baseUrl("https://xkcd.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ComicService comicService = retrofit.create(ComicService.class);
        Call<ComicModel> call = comicService.buscarComic(snum);

        call.enqueue(new Callback<ComicModel>() {

            @Override
            public void onResponse(Call<ComicModel> call, Response<ComicModel> response) {

                if(response.isSuccessful()){

                    ComicModel comicData = response.body();
                    numberComic = comicData.getNum();
                    urlImg = comicData.getImg();
                    System.out.println("dentro response.isSuccessful - *** numberComic: ***"+numberComic);
                    System.out.println("dentro response.isSuccessful - *** url_img: ***"+urlImg);
                    Picasso.get().load(urlImg).into(mimageView);

                }

            } // fim onResponse

            @Override
            public void onFailure(Call<ComicModel> call, Throwable t) {

                System.out.println("FAILURE ***********");

            } // fim onFailure

        }); // fim enqueue

     } // fim acaoBuscaRetrofit

    public Integer createRandomNumber(){

        Integer number;

        number = (int)(Math.random() * numberComic);

        return number;
    } // fim createRandomNumber

    public void showComic(){

        Picasso.get().load(urlImg).into(mimageView);

    }

} // fim MainActivity
