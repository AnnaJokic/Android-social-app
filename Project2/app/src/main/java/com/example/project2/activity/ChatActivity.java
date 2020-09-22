package com.example.project2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.project2.R;
import com.example.project2.Util.Utils;
import com.example.project2.adapter.MessageAdapter;
import com.example.project2.model.Message;
import com.example.project2.model.Student;
import com.example.project2.model.StudentResponse;
import com.example.project2.viewModel.LoginViewModel;
import com.example.project2.viewModel.MessageViewModel;
import com.example.project2.viewModel.SplashViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    public static final String USER_NAME = "userName";
    public static final String USER_ID = "userId";

    private String mUserName;
    private String mUserId;

    private String mUserName2;
    private String mUserId2;


    private ImageButton send;
    private TextView header;
    private EditText et_poruka;

    private SplashViewModel splashViewModel;
    private MessageViewModel messageViewModel;

    private MessageAdapter messageAdapter;
    private LinearLayoutManager linearLayoutManager;

    private Message newMessage;
    private Message newMessage2;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        init();
    }

    private void init(){

        initViewModel();
        initUi();
    }

    private void initViewModel(){

        splashViewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
        messageViewModel = ViewModelProviders.of(this).get(MessageViewModel.class);
        messageViewModel.getMessageLiveData().observe(ChatActivity.this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {

                ArrayList<Message> newMessagesList = new ArrayList<>();

                Intent intent2 = getIntent();
                if (intent2 != null) {
                    mUserName2 = intent2.getStringExtra(USER_NAME);
                    mUserId2 = intent2.getStringExtra(USER_ID);

                }
                Student sender2 = splashViewModel.getUserObican();
                Student receiver2 = new Student(mUserId2, mUserName2);
                newMessage2 = new Message(sender2, receiver2);

                mUserId2 = mUserId.trim();


                for(Message m : messages){


                    if(m.getSender().getIndexId().equals(newMessage2.getSender().getIndexId()) && m.getReceiver().getIndexId().equals(newMessage2.getReceiver().getIndexId())){


                        m.setMessageType(0);
                        newMessagesList.add(m);


                    }else if(m.getSender().getIndexId().equals(mUserId2) && m.getReceiver().getIndexId().trim().equals(sender2.getIndexId())){

                        m.setMessageType(1);
                        newMessagesList.add(m);
                    }

                }
                 messageAdapter.setData(newMessagesList);
                newMessagesList.clear();
            }
        });
    }



    private void initUi() {

        RecyclerView recyclerView = findViewById(R.id.rv_chat);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        messageAdapter = new MessageAdapter();

        recyclerView.setAdapter(messageAdapter);

        send = findViewById(R.id.image_btn_send);
        header = findViewById(R.id.header);
        et_poruka = findViewById(R.id.et_message);

        Intent intent = getIntent();
        if (intent != null) {
            mUserName = intent.getStringExtra(USER_NAME);
            mUserId = intent.getStringExtra(USER_ID);

            header.setText(mUserName);
        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DateFormat df = new SimpleDateFormat("EEE, MMM d, ''yy");
                DateFormat df2 = new SimpleDateFormat("h:mm a");
                String date = df.format(Calendar.getInstance().getTime());
                String time = df2.format(Calendar.getInstance().getTime());
                String poruka = et_poruka.getText().toString();

                Student sender = splashViewModel.getUserObican();
                Student receiver = new Student(mUserId, mUserName);

                newMessage = new Message(0,poruka, time+" "+date, sender, receiver);

                if (poruka != "") {
                    handleAddButtonClick(newMessage);
                }
                et_poruka.setText("");


            }
        });
    }



    private void handleAddButtonClick(Message message) {

        messageViewModel.addMessage(message);


    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


}
