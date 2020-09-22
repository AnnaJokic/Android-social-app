package com.example.project2.livedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.example.project2.Util.Utils;
import com.example.project2.model.Message;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class PostsLiveData extends LiveData<List<Message>> {


    private ValueEventListener mValueEventListener;
    private DatabaseReference databaseReference;

    public PostsLiveData() {
        super();
        initFirebase();
    }

    private void initFirebase() {
        FirebaseDatabase firebaseDatabase = Utils.getDatabase();
        databaseReference = firebaseDatabase.getReference().child("wall/");
        mValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Message> messages = new ArrayList<>();

                if (dataSnapshot.getValue() == null) {
                    return;
                }

                for (DataSnapshot childDataSnapshot:dataSnapshot.getChildren()) {
                    Message message = childDataSnapshot.getValue(Message.class);
                    String key = childDataSnapshot.getKey();
                    message.setId(key);
                    messages.add(message);
                }

                setValue(messages);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
    }

    @Override
    protected void onActive() {
        super.onActive();
        databaseReference.addValueEventListener(mValueEventListener);

    }

    @Override
    protected void onInactive() {
        super.onInactive();
        databaseReference.removeEventListener(mValueEventListener);
    }
}
