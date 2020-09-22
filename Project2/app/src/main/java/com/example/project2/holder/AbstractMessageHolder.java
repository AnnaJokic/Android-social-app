package com.example.project2.holder;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.project2.model.Message;

public abstract class AbstractMessageHolder extends RecyclerView.ViewHolder {

    AbstractMessageHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bind(Message message);
}