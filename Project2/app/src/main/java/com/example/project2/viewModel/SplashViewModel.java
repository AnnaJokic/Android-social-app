package com.example.project2.viewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.project2.model.Student;
import com.example.project2.model.StudentResponse;
import com.example.project2.repository.AuthRepository;

public class SplashViewModel extends AndroidViewModel {

    private AuthRepository mAuthRepository;

    public SplashViewModel(Application application) {
        super(application);
        mAuthRepository = new AuthRepository(application);
    }

    public LiveData<StudentResponse> getLoggedInUserLiveData() {
        return mAuthRepository.getUser();
    }

    public Student getUserObican(){
        return mAuthRepository.getUserObican();
    }
}
