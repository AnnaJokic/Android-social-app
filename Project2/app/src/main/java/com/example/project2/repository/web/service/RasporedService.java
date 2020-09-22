package com.example.project2.repository.web.service;

import androidx.recyclerview.widget.DiffUtil;
import com.example.project2.repository.web.model.RasporedApiModel;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;

import java.util.List;

public interface RasporedService {

    @GET("json.php")
    public Call<List<RasporedApiModel>> getRasporeds();

}
