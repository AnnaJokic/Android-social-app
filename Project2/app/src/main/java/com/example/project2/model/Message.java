package com.example.project2.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

    public static final int SENT = 0;
    public static final int RECEIVED = 1;
    public static final int PHOTO = 2;
    public static final int TEXT = 3;

    private String mText;
    private int mMessageType;
    private String mId;
    private String mDateTime;
    private Student mSender;
    private Student mReceiver;
    private String mUri;
    private Uri mRealUri;



    public Message() {

    }


    public Message(int messageType, String uri, Student sender, String dateTime) {
        mMessageType = messageType;
        mUri = uri;
        mSender = sender;
        mDateTime = dateTime;
    }

    public Message(String uri) {
        mUri = uri;

    }


    public Message(int messageType, String text, String dateTime, Student sender, Student receiver) {
        mMessageType = messageType;
        mText = text;
        mDateTime = dateTime;
        mSender = sender;
        mReceiver = receiver;
    }

    public Message(Student sender, Student receiver) {

        mSender = sender;
        mReceiver = receiver;
    }

    public Uri getmRealUri() {
        return mRealUri;
    }

    public void setmRealUri(Uri mRealUri) {
        this.mRealUri = mRealUri;
    }


    public String getmUri() {
        return mUri;
    }

    public void setmUri(String mUri) {
        this.mUri = mUri;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public Student  getSender() {
        return mSender;
    }

    public void setSender(Student sender) {
        mSender = sender;
    }

    public Student getReceiver() {
        return mReceiver;
    }

    public void setReceiver(Student receiver) {
        mReceiver = receiver;
    }

    public String getDateTime() {
        return mDateTime;
    }

    public void setDateTime(String dateTime) {
        mDateTime = dateTime;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public int getMessageType() {
        return mMessageType;
    }

    public void setMessageType(int messageType) {
        mMessageType = messageType;
    }

}
