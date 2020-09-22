package com.example.project2.repository;

import com.example.project2.Util.Utils;
import com.example.project2.livedata.MessagesLiveData;
import com.example.project2.livedata.PostsLiveData;
import com.example.project2.model.Message;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WallRepository {

    private DatabaseReference databaseReference;
    private PostsLiveData postsLiveData;


    public WallRepository() {
        FirebaseDatabase firebaseDatabase = Utils.getDatabase();
        databaseReference = firebaseDatabase.getReference("wall/");
        postsLiveData = new PostsLiveData();
    }

    public PostsLiveData getPostsLiveData() {
        return postsLiveData;
    }

    public void addPost(Message message) {
        databaseReference.push().setValue(message);
    }
}
