package com.shn.comic01.service;

import com.shn.comic01.model.ComicModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ComicService {

    @GET("{num}/info.0.json")  // sufixo da url-base http://xkcd.com/ -> num = "" retorna o comic atual
    Call<List<ComicModel>> buscarComic(@Path("num") String num);
}
