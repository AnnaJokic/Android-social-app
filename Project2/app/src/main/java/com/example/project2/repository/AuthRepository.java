package com.example.project2.repository;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.project2.Util.Utils;
import com.example.project2.model.Student;
import com.example.project2.model.StudentResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AuthRepository {

    private static final String USERNAME_KEY = "userNameKey";
    private static final String INDEX_ID_KEY = "indexIdKey";

    private MutableLiveData<StudentResponse> mUserLiveData;
    private MutableLiveData<StudentResponse> mUserStoreLiveData;
    private SharedPreferences mSharedPreferences;

    private DatabaseReference mUsersDatabaseReference;

    public AuthRepository(Application application) {
        mUserLiveData = new MutableLiveData<>();
        mUserStoreLiveData = new MutableLiveData<>();

        String packageName = application.getPackageName();
        mSharedPreferences = application.getSharedPreferences(packageName, Context.MODE_PRIVATE);

        FirebaseDatabase firebaseDatabase = Utils.getDatabase();
        mUsersDatabaseReference = firebaseDatabase.getReference().child("root");
    }

    public LiveData<StudentResponse> getUser() {
        String username = mSharedPreferences.getString(USERNAME_KEY, null);
        String indexId = mSharedPreferences.getString(INDEX_ID_KEY, null);

        StudentResponse userResponse;
        if (username == null || indexId == null) {
            Student user = new Student("", "");
            userResponse = new StudentResponse(user, false);
        } else {
            Student user = new Student(username, indexId);
            userResponse = new StudentResponse(user, true);
        }

        mUserLiveData.setValue(userResponse);

        return mUserLiveData;
    }

    public Student getUserObican(){

        String username = mSharedPreferences.getString(USERNAME_KEY, null);
        String indexId = mSharedPreferences.getString(INDEX_ID_KEY, null);
        Student student = new Student(indexId, username);

        StudentResponse userResponse;
        if (username == null || indexId == null) {
            Student user = new Student("", "");
            userResponse = new StudentResponse(user, false);
        } else {
            Student user = new Student(username, indexId);
            userResponse = new StudentResponse(user, true);
        }



        return student;

    }
    public void storeUser(Student user) {
        String username = user.getName();
        String indexId = user.getIndexId();

        mUsersDatabaseReference.child(indexId).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        SharedPreferences.Editor editor = mSharedPreferences.edit();
                        editor.putString(USERNAME_KEY, username);
                        editor.putString(INDEX_ID_KEY, indexId);
                        editor.commit();

                        StudentResponse userResponse = new StudentResponse(user, true);
                        mUserStoreLiveData.setValue(userResponse);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        StudentResponse userResponse = new StudentResponse(user, false);
                        mUserStoreLiveData.setValue(userResponse);
                    }
                });
    }

    public LiveData<StudentResponse> getUserStoreLiveData() {
        return mUserStoreLiveData;
    }

    public void clearUser() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

}