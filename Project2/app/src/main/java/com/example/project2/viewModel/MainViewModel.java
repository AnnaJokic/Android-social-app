package com.example.project2.viewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.example.project2.model.Raspored;
import com.example.project2.repository.AuthRepository;
import com.example.project2.repository.RasporedRepository;
import com.example.project2.repository.db.entity.RasporedEntity;
import com.example.project2.repository.web.model.Resource;

import java.util.List;

public class MainViewModel extends AndroidViewModel {


    private RasporedRepository mRasporedRepository;
    private MutableLiveData<Raspored> mFilterLiveData;
    private MutableLiveData<String> mFilterLiveData1;

    private LiveData<List<RasporedEntity>> mFilteredRasporedsLiveData;
    private LiveData<List<RasporedEntity>> mFilteredRasporedsLiveData1;

    private AuthRepository mAuthRepository;


    public MainViewModel(@NonNull Application application) {
        super(application);
        mRasporedRepository = new RasporedRepository(application);
        mFilterLiveData = new MutableLiveData<>();
        mFilterLiveData1 = new MutableLiveData<>();
        mAuthRepository = new AuthRepository(application);

        mFilteredRasporedsLiveData = Transformations.switchMap(mFilterLiveData,
                new Function<Raspored, LiveData<List<RasporedEntity>>>() {
                    @Override
                    public LiveData<List<RasporedEntity>> apply(Raspored filter) {
                        return mRasporedRepository.getFilteredRasporeds(filter);
                    }
                });
        mFilteredRasporedsLiveData1 = Transformations.switchMap(mFilterLiveData1,
                new Function<String, LiveData<List<RasporedEntity>>>() {
                    @Override
                    public LiveData<List<RasporedEntity>> apply(String filter) {
                        return mRasporedRepository.getFilteredRasporeds1(filter);
                    }
                });

    }


    public LiveData<Resource<Void>> getRasporeds() {
        return  mRasporedRepository.getRasporeds();
    }


    public LiveData<List<RasporedEntity>> getFilteredRasporeds(){
        return mFilteredRasporedsLiveData;
    }
    public LiveData<List<RasporedEntity>> getFilteredRasporeds1(){
        return mFilteredRasporedsLiveData1;
    }
    public LiveData<List<RasporedEntity>> getAllRasporeds() {
        return mRasporedRepository.getAllRasporeds();
    }

    public void setFilter(Raspored filter) {
        mFilterLiveData.setValue(filter);
    }
    public void setFilter1(String filter) {
        mFilterLiveData1.setValue(filter);
    }

    public void logOut() {
        mAuthRepository.clearUser();
    }
}
