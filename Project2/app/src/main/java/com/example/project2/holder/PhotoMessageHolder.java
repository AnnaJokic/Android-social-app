package com.example.project2.holder;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.project2.R;
import com.example.project2.model.Message;
import com.squareup.picasso.Picasso;

public class PhotoMessageHolder extends AbstractMessageHolder {

    private ImageView mContentIv;
    private TextView dateTime;
    private TextView name;

    public PhotoMessageHolder(@NonNull View itemView) {
        super(itemView);
        mContentIv = itemView.findViewById(R.id.iv_content_wall);
        name = itemView.findViewById(R.id.ime_prezime_slika);
        dateTime = itemView.findViewById(R.id.datum_vreme_slika);


    }

    @Override
    public void bind(Message message) {

        name.setText(message.getSender().getName());
        dateTime.setText(message.getDateTime());
        Uri imgUri=Uri.parse(message.getmUri());
        mContentIv.setImageURI(null);
        //mContentIv.setImageURI(imgUri);

        Picasso.get().load(imgUri).into(mContentIv);

        //mContentIv.setImageURI(Uri.parse(message.getmUri()));
       // mContentIv.setImageBitmap(message.getBitmap());
    }
}
