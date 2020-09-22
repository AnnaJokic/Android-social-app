package com.example.project2.model;

import java.util.ArrayList;

public class Raspored {

    private String predmet;
    private String profesor;
    private String ucionica;
    private String grupa;
    private String dan;
    private String tip;
    private String termin;
    private int mId;


    public Raspored(String predmet, String profesor, String ucionica, String grupa, String dan, String tip, String termin, int mId) {
        this.predmet = predmet;
        this.profesor = profesor;
        this.ucionica = ucionica;
        this.grupa = grupa;
        this.dan = dan;
        this.tip = tip;
        this.termin = termin;
        this.mId = mId;
    }

    public Raspored(String predmet,  String dan, String grupa, String profesor) {
        this.predmet = predmet;
        this.dan = dan;
        this.grupa = grupa;
        this.profesor = profesor;

    }


    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getUcionica() {
        return ucionica;
    }

    public void setUcionica(String ucionica) {
        this.ucionica = ucionica;
    }


    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public String getDan() {
        return dan;
    }

    public void setDan(String dan) {
        this.dan = dan;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getTermin() {
        return termin;
    }

    public void setTermin(String termin) {
        this.termin = termin;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }
}
