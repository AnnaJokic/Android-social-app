package com.example.project2.holder;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.project2.R;
import com.example.project2.model.Message;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SentMessageHolder extends AbstractMessageHolder {

    private TextView mContentTv;
    private TextView mDateTime;

    public SentMessageHolder(@NonNull View itemView) {
        super(itemView);
        mContentTv = itemView.findViewById(R.id.chat_right_msg_text_view);
        mDateTime = itemView.findViewById(R.id.chat_right_date_time);

    }

    @Override
    public void bind(Message message) {
        mContentTv.setText(message.getText());
        mDateTime.setText(message.getDateTime());
    }
}
