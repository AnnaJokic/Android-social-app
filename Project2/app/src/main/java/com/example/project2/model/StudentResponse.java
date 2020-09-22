package com.example.project2.model;

public class StudentResponse {

    private Student  mUser;

    private boolean isSuccessful;

    public StudentResponse(Student user, boolean isSuccessful) {
        mUser = user;
        this.isSuccessful = isSuccessful;
    }

    public Student getUser() {
        return mUser;
    }

    public void setUser(Student user) {
        mUser = user;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }
}
