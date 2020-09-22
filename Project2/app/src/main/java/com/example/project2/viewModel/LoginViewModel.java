package com.example.project2.viewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.project2.model.Student;
import com.example.project2.model.StudentResponse;
import com.example.project2.repository.AuthRepository;

public class LoginViewModel extends AndroidViewModel {



        private AuthRepository mAuthRepository;

        public LoginViewModel(@NonNull Application application) {
            super(application);
            mAuthRepository = new AuthRepository(application);
        }

        public void logInUser(String indexId, String name) {
            Student user = new Student(indexId, name);
            mAuthRepository.storeUser(user);
        }

        public LiveData<StudentResponse> getUserStoreLiveData() {
            return mAuthRepository.getUserStoreLiveData();
        }


}
