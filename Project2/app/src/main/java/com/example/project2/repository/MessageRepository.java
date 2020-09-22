package com.example.project2.repository;

import androidx.lifecycle.LiveData;
import com.example.project2.Util.Utils;
import com.example.project2.livedata.MessagesLiveData;
import com.example.project2.model.Message;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MessageRepository {

    private DatabaseReference databaseReference;
    private MessagesLiveData messagesLiveData;


    public MessageRepository() {
        FirebaseDatabase firebaseDatabase = Utils.getDatabase();
        databaseReference = firebaseDatabase.getReference("messages/");
        messagesLiveData = new MessagesLiveData();
    }

    public MessagesLiveData getMessagesLiveData() {
        return messagesLiveData;
    }

    public void addMessage(Message message) {
        databaseReference.push().setValue(message);
    }

}
