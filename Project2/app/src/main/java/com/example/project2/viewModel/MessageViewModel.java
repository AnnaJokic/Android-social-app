package com.example.project2.viewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.project2.livedata.MessagesLiveData;
import com.example.project2.model.Message;
import com.example.project2.model.StudentResponse;
import com.example.project2.repository.AuthRepository;
import com.example.project2.repository.MessageRepository;

public class MessageViewModel extends AndroidViewModel {


        private MessageRepository messageRepository;
        private AuthRepository mAuthRepository;

        public MessageViewModel(Application application) {
            super(application);
            messageRepository = new MessageRepository();
            mAuthRepository = new AuthRepository(application);

        }

        public MessagesLiveData getMessageLiveData() {
            return messageRepository.getMessagesLiveData();
        }

        public void addMessage(Message message) {
            messageRepository.addMessage(message);
        }




    }


