package com.example.project2.viewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.example.project2.livedata.MessagesLiveData;
import com.example.project2.livedata.PostsLiveData;
import com.example.project2.model.Message;
import com.example.project2.repository.AuthRepository;
import com.example.project2.repository.MessageRepository;
import com.example.project2.repository.WallRepository;

public class WallViewModel extends AndroidViewModel {


    private WallRepository wallRepository;
    private AuthRepository mAuthRepository;

    public WallViewModel(Application application) {
        super(application);
        wallRepository = new WallRepository();
        mAuthRepository = new AuthRepository(application);

    }

    public PostsLiveData getMessageLiveData() {
        return wallRepository.getPostsLiveData();
    }

    public void addMessage(Message message) {
        wallRepository.addPost(message);
    }
}
