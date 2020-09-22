package com.example.project2.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.project2.R;
import com.example.project2.Util.Utils;
import com.example.project2.activity.ChatActivity;
import com.example.project2.activity.LoginActivity;
import com.example.project2.activity.RasporedActivity;
import com.example.project2.adapter.StudentAdapter;
import com.example.project2.model.Student;
import com.example.project2.repository.AuthRepository;
import com.example.project2.viewModel.MainViewModel;
import com.example.project2.viewModel.SplashViewModel;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

public class Fragment2 extends Fragment {


    private StudentAdapter mAdapter;
    private DatabaseReference mRefrence;
    private ValueEventListener mValueEventListener;
    private ChildEventListener mChildEventListener;
    private SplashViewModel splashViewModel;



    public static Fragment2 newInstance() {
        return new Fragment2();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment2, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_students);
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(),1);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new StudentAdapter();
        recyclerView.setAdapter(mAdapter);



        Intent intent = new Intent(this.getActivity(), ChatActivity.class);
        mAdapter.setOnItemClickedListener(new StudentAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(Student student) {

                intent.putExtra( ChatActivity.USER_NAME, student.getName()+" ");
                intent.putExtra( ChatActivity.USER_ID, student.getIndexId()+" ");
                startActivity(intent);
            }
        });

        initFirebase();
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }


    private void initFirebase() {

        FirebaseDatabase firebaseDatabase = Utils.getDatabase();
        mRefrence = firebaseDatabase.getReference().child("root");
        createAndAddListeners();
    }

    private void createAndAddListeners() {
        splashViewModel =  ViewModelProviders.of(this).get(SplashViewModel.class);
        mValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Student> studentModels = new ArrayList<>();

                if (dataSnapshot.getValue() == null) {
                    return;
                }

                for (DataSnapshot childDataSnapshot:dataSnapshot.getChildren()) {
                    Student student = childDataSnapshot.getValue(Student.class);
                    String key = childDataSnapshot.getKey();
                    student.setIndexId(key);
                    studentModels.add(student);
                }

                Student student = splashViewModel.getUserObican();

                ArrayList<Student> listaZaPrikaz = new ArrayList<>();

                for(Student s : studentModels){


                    if(!(s.getIndexId().equals(student.getIndexId()))){
                        listaZaPrikaz.add(s);
                    }
                }
                mAdapter.setData(listaZaPrikaz);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        mRefrence.addValueEventListener(mValueEventListener);

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        mRefrence.addChildEventListener(mChildEventListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRefrence.removeEventListener(mValueEventListener);
    }


}
