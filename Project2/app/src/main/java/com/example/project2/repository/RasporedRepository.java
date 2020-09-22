package com.example.project2.repository;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.project2.model.Raspored;
import com.example.project2.repository.db.RasporedDatabase;
import com.example.project2.repository.db.dao.RasporedDao;
import com.example.project2.repository.db.entity.RasporedEntity;
import com.example.project2.repository.web.api.RasporedApi;
import com.example.project2.repository.web.model.RasporedApiModel;
import com.example.project2.repository.web.model.Resource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RasporedRepository {

    private static final String TAG = "RasporedRepository";

    private RasporedApi mRasporedApi;
    private RasporedDao mRasporedDao;
    private MutableLiveData<Resource<Void>> mResourceLiveData;
    private ExecutorService mExecutorService;

    public RasporedRepository(Application application) {
        mRasporedApi = new RasporedApi();
        mRasporedDao = RasporedDatabase.getDb(application).getRasporedDao();
        mResourceLiveData = new MutableLiveData<>();
        mExecutorService = Executors.newCachedThreadPool();
    }

    public LiveData<Resource<Void>> getRasporeds(){
        mRasporedApi.getRasporeds().enqueue(new Callback<List<RasporedApiModel>>() {
            @Override
            public void onResponse(Call<List<RasporedApiModel>> call, Response<List<RasporedApiModel>> response) {
                // Notify UI that data was fetched successfully
                notifyResult(true);
                // Insert response into db so all observers can get movie data
                insertRasporeds(response.body());
            }

            @Override
            public void onFailure(Call<List<RasporedApiModel>> call, Throwable t) {
                // Notify UI that data fetch failed
                notifyResult(false);
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });
        return mResourceLiveData;
    }




    private void insertRasporeds(List<RasporedApiModel> rasporedApiModelList) {
        // We need to transform api model to entity before storing data
        List<RasporedEntity> rasporedEntityList = transformApiModelToEntity(rasporedApiModelList);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                mRasporedDao.nukeTable();
                mRasporedDao.insertRasporeds(rasporedEntityList);
            }
        });
    }

    public LiveData<List<RasporedEntity>> getFilteredRasporeds(Raspored filter) {
        return mRasporedDao.getFilteredRasporeds(filter.getProfesor(), filter.getDan(), filter.getGrupa());
    }

    public LiveData<List<RasporedEntity>> getFilteredRasporeds1(String filter) {
        return mRasporedDao.getFilteredRasporeds1(filter);
    }

    public LiveData<List<RasporedEntity>> getAllRasporeds() {
        return mRasporedDao.getAllRasporeds();
    }

    private void notifyResult(boolean isSuccessful){
        // Since we are storing movies in a database, there is no need to send
        // the response we got from the server to the activity, we'll just wait
        // for the data to be inserted into db, and at that moment all observers observing
        // movie table will be notified. So we just send Void, and a flag if fetch was successful.
        Resource<Void> resource = new Resource<>(null, isSuccessful);
        mResourceLiveData.setValue(resource);
    }


    private List<RasporedEntity> transformApiModelToEntity(List<RasporedApiModel> rasporedApiModelList) {
        List<RasporedEntity> rasporedEntityList = new ArrayList<>();

        for (RasporedApiModel rasporedApiModel: rasporedApiModelList) {

            String predmet = rasporedApiModel.getPredmet();
            String tip = rasporedApiModel.getTip();
            String nastavnik = rasporedApiModel.getNastavnik();
            String grupe = rasporedApiModel.getGrupe();
            String  dan = rasporedApiModel.getDan();
            String termin = rasporedApiModel.getTermin();
            String ucionica = rasporedApiModel.getUcionica();
            int id = rasporedApiModel.getId();

            RasporedEntity rasporedEntity = new RasporedEntity(predmet, tip, nastavnik, grupe, dan, termin, ucionica, id);
            rasporedEntityList.add(rasporedEntity);
        }
        return rasporedEntityList;
    }
}
