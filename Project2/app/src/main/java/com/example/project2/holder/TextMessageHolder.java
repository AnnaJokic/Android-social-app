package com.example.project2.holder;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.project2.R;
import com.example.project2.model.Message;

public class TextMessageHolder extends AbstractMessageHolder {

    private TextView name;
    private TextView dateTime;
    private TextView content;

    public TextMessageHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.frag3_ime_prezime);
        dateTime = itemView.findViewById(R.id.frag3_datum);
        content = itemView.findViewById(R.id.frag3_content);


    }

    @Override
    public void bind(Message message) {

        name.setText(message.getSender().getName());
        dateTime.setText(message.getDateTime());
        content.setText(message.getText());


    }
}
