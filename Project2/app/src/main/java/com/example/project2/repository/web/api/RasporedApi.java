package com.example.project2.repository.web.api;

import com.example.project2.repository.web.model.RasporedApiModel;
import com.example.project2.repository.web.service.RasporedService;
import com.example.project2.repository.web.service.ServiceGenerator;
import retrofit2.Call;

import java.util.List;

public class RasporedApi {

    private RasporedService mRasporedService;

    public RasporedApi() {
        mRasporedService = ServiceGenerator.createService(RasporedService.class);
    }


    public Call<List<RasporedApiModel>> getRasporeds() {
        return mRasporedService.getRasporeds();
    }


}
