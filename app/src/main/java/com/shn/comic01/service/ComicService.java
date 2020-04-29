package com.shn.comic01.service;

import com.shn.comic01.model.ComicModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ComicService {

    // annotation
    @GET("{id}/info.0.json")

    // retorna um CEPModel
    Call<ComicModel> buscarComic( @Path("id") String id);
}
