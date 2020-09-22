package com.example.project2.model;

public class Student {

    private String mIndexId;

    private String mName;

    private Message mMessage;

    public Student() {

    }

    public Student(String indexId, String name) {
        mIndexId = indexId;
        mName = name;
    }

    public Student(String indexId, String name, Message message) {
        mIndexId = indexId;
        mName = name;
        mMessage = message;
    }

    public String getIndexId() {
        return mIndexId;
    }

    public void setIndexId(String indexId) {
        mIndexId = indexId;
    }

    public String getName() {
        return mName;
    }

    public void setMessage(Message message) {
        mMessage = message;
    }

    public Message getmMessage() {
        return mMessage;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "mIndexId='" + mIndexId + '\'' +
                ", mName='" + mName + '\'' +
                '}';
    }
}
