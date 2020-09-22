package com.example.project2.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.project2.R;
import com.example.project2.Util.Utils;
import com.example.project2.adapter.MessageAdapter;
import com.example.project2.model.Message;
import com.example.project2.model.Student;
import com.example.project2.viewModel.SplashViewModel;
import com.example.project2.viewModel.WallViewModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import retrofit2.http.Url;
import uk.co.senab.photoview.PhotoView;

import static android.app.Activity.RESULT_OK;

public class Fragment3 extends Fragment {


    private ImageButton send;
    private ImageButton sendPhoto;
    private EditText poruka;
    private SplashViewModel splashViewModel;
    private WallViewModel wallViewModel;
    private MessageAdapter messageAdapter;

    private static final int REQUEST_CAMERA_PERMISSION = 222;
    private static final int REQUEST_CAMERA_PHOTO = 333;
    private File mPhotoFile;
    private StorageReference storageReference;

    public static Fragment3 newInstance() {
        return new Fragment3();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        splashViewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
        wallViewModel = ViewModelProviders.of(this).get(WallViewModel.class);
        messageAdapter = new MessageAdapter();
        wallViewModel.getMessageLiveData().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                messageAdapter.setData(messages);
            }
        });
        View view = inflater.inflate(R.layout.fragment3, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_wall);
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(),1);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(messageAdapter);

        send = view.findViewById(R.id.image_btn_send_wall);
        poruka = view.findViewById(R.id.et_wall);
        Student sender = splashViewModel.getUserObican();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message newMessage = new Message();
                String content = poruka.getText().toString();
                DateFormat df = new SimpleDateFormat("EEE, MMM d, ''yy");
                DateFormat df2 = new SimpleDateFormat("h:mm a");
                String date = df.format(Calendar.getInstance().getTime());
                String time = df2.format(Calendar.getInstance().getTime());

                newMessage.setText(content);
                newMessage.setSender(sender);
                newMessage.setDateTime(date+" "+time);
                newMessage.setMessageType(3);

                if (content != "") {
                    handleAddButtonClick(newMessage);
                }
                poruka.setText("");
            }
        });

        sendPhoto = view.findViewById(R.id.image_btn_send_wall_slika);
        sendPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();

            }
        });

        initFirebase();
        return view;
    }

    private void initFirebase(){
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference().child("uploads");
    }

    @SuppressLint("MissingPermission")
    private void takePhoto() {


        if (!hasAnyFeature(PackageManager.FEATURE_CAMERA)) {
            showToast("No feature");
            return;
        }

        if (hasPermissions(Manifest.permission.CAMERA)) {


            try {
                mPhotoFile = createImageFile();


            } catch (IOException e) {
                e.printStackTrace();

            }

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            Uri photoURI = FileProvider.getUriForFile(getActivity(), getString(R.string.app_file_provider),
                    mPhotoFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

            startActivityForResult(intent, REQUEST_CAMERA_PHOTO);
        } else {

            requestPermissions(REQUEST_CAMERA_PERMISSION, Manifest.permission.CAMERA);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePhoto();
                } else {
                    showToast("Permission not granted");
                }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if (requestCode != REQUEST_CAMERA_PHOTO ) {
            return;
        }

        if (resultCode != RESULT_OK) {
            showToast("You didn't capture photo!");
            return;
        }


        Uri uri = Uri.fromFile(mPhotoFile);

        StorageReference photoReference = storageReference.child(mPhotoFile.getName());

        photoReference.putFile(uri)
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        //long progress = ((100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount());
                        //mStatusTv.setText(progress + "% uploaded");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            }
        }).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {


                return photoReference.getDownloadUrl();
            }
        }).addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Student uploader = splashViewModel.getUserObican();
                DateFormat df = new SimpleDateFormat("EEE, MMM d, ''yy");
                DateFormat df2 = new SimpleDateFormat("h:mm a");
                String date = df.format(Calendar.getInstance().getTime());
                String time = df2.format(Calendar.getInstance().getTime());

                Message message = new Message(uri.toString());
                message.setMessageType(2);
                message.setDateTime(date+" "+time);
                message.setSender(uploader);
                handleAddButtonClick(message);


            }
        });
    }



    protected boolean hasAnyFeature(String... features){
        for (String feature : features) {
            if (getActivity().getPackageManager().hasSystemFeature(feature)){
                return true;
            }
        }
        return false;
    }

    protected boolean hasPermissions(String... permissions){
        for (String permission : permissions) {
            boolean hasPermission = ContextCompat.checkSelfPermission(getActivity(), permission) == PackageManager.PERMISSION_GRANTED;
            if(!hasPermission) {
                return false;
            }
        }
        return true;
    }


    private void handleAddButtonClick(Message message) {

        wallViewModel.addMessage(message);


    }

    protected void requestPermissions(int requestCode, String... permissions){
        ActivityCompat.requestPermissions(getActivity(), permissions, requestCode);
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return image;
    }



    private void showToast(String message) {
        Toast.makeText(getActivity() ,message, Toast.LENGTH_LONG).show();

    }





}
