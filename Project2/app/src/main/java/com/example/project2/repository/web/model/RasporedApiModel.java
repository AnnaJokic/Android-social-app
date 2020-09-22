package com.example.project2.repository.web.model;

import com.google.gson.annotations.SerializedName;

public class RasporedApiModel {


    @SerializedName("predmet")
    private String mPredmet;

    @SerializedName("tip")
    private String mTip;

    @SerializedName("nastavnik")
    private String mNastavnik;

    @SerializedName("grupe")
    private String mGrupe;

    @SerializedName("dan")
    private String mDan;

    @SerializedName("termin")
    private String mTermin;

    @SerializedName("ucionica")
    private String mUcionica;

    @SerializedName("id")
    private int mId;

    public String getPredmet() {
        return mPredmet;
    }

    public int getId() {
        return mId;
    }

    public String getTip() {
        return mTip;
    }

    public String getNastavnik() {
        return mNastavnik;
    }

    public String getGrupe() {
        return mGrupe;
    }

    public String getDan() {
        return mDan;
    }

    public String getTermin() {
        return mTermin;
    }

    public String getUcionica() {
        return mUcionica;
    }
}
