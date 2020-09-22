package com.example.project2.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.project2.R;
import com.example.project2.Util.MessagediffUtilCallback;
import com.example.project2.holder.*;
import com.example.project2.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<AbstractMessageHolder> {

    private static final String TAG = "MessageAdapter";

    private List<Message> mDataSet;

    public MessageAdapter() {
        mDataSet = new ArrayList<>();
    }

    @NonNull
    @Override
    public AbstractMessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case Message.RECEIVED: {
                View view = inflater.inflate(R.layout.list_item_message_left, parent, false);
                return new ReceivedMessageHolder(view);
            }
            case Message.SENT: {
                View view = inflater.inflate(R.layout.list_item_message_right, parent, false);
                return new SentMessageHolder(view);
            }
            case Message.PHOTO:{
                View view = inflater.inflate(R.layout.wall_list_item_photo, parent, false);
                return new PhotoMessageHolder(view);

            }
            case Message.TEXT:{
                View view = inflater.inflate(R.layout.wall_list_item, parent, false);
                return new TextMessageHolder(view);

            }

        }
        Log.e(TAG, "onCreateViewHolder: Something went terribly wrong for type " + viewType);
        // This should never happen!
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AbstractMessageHolder holder, int position) {
        Message message = mDataSet.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void setData(List<Message> messageList){
        MessagediffUtilCallback callback = new MessagediffUtilCallback(mDataSet, messageList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        mDataSet.clear();
        mDataSet.addAll(messageList);
        result.dispatchUpdatesTo(this);
    }

    @Override
    public int getItemViewType(int position) {
        Message message = mDataSet.get(position);
        return message.getMessageType();
    }


}