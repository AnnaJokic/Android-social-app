package com.example.project2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.project2.R;
import com.example.project2.Util.RasporedDiffUtilCallback;
import com.example.project2.Util.StudentDiffUtilCallback;
import com.example.project2.model.Student;
import com.example.project2.repository.db.entity.RasporedEntity;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {

    private List<Student> mDataSet;
    private OnItemClickedListener mOnItemClickedListener;


    public StudentAdapter() {
        mDataSet = new ArrayList<>();
    }

    @NonNull
    @Override
    public StudentAdapter.StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_user, parent, false);
        return new StudentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.StudentHolder holder, int position) {
        Student student = mDataSet.get(position);
        holder.ime.setText(student.getName());

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void setData(List<Student> students){
        StudentDiffUtilCallback callback = new StudentDiffUtilCallback(mDataSet, students);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        mDataSet.clear();
        mDataSet.addAll(students);
        result.dispatchUpdatesTo(this);
    }

    public class StudentHolder extends RecyclerView.ViewHolder {

        TextView ime;

        public StudentHolder(@NonNull View itemView) {
            super(itemView);
            ime = itemView.findViewById(R.id.username);


            ime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (mOnItemClickedListener != null && position != RecyclerView.NO_POSITION) {
                        Student student = mDataSet.get(position);
                        mOnItemClickedListener.onItemClicked(student);
                    }
                }
            });

        }
    }

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        mOnItemClickedListener = onItemClickedListener;
    }

    public interface OnItemClickedListener {
        void onItemClicked(Student student);
    }


}