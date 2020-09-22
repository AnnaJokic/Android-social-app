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
import com.example.project2.model.Raspored;
import com.example.project2.repository.db.entity.RasporedEntity;

import java.util.ArrayList;
import java.util.List;

public class RasporedAdapter extends RecyclerView.Adapter<RasporedAdapter.RasporedHolder> {

    private List<RasporedEntity> mDataSet;
    private OnItemClickedListener mOnItemClickedListener;
    private OnItemClickedListener1 mOnItemClickedListener1;
    private OnItemClickedListener2 mOnItemClickedListener2;

    public RasporedAdapter() {
        mDataSet = new ArrayList<>();
    }

    @NonNull
    @Override
    public RasporedAdapter.RasporedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_raspored, parent, false);
        return new RasporedHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RasporedAdapter.RasporedHolder holder, int position) {
        RasporedEntity raspored = mDataSet.get(position);
        holder.tv_profesor.setText(raspored.getNastavnik() +"-" + raspored.getTip() );
        holder.tv_predmet.setText(raspored.getPredmet());
        holder.tv_ucionica.setText(raspored.getUcionica());
        holder.tv_datum.setText(raspored.getDan()+"\n" +raspored.getTermin());
        holder.tv_grupa.setText(raspored.getGrupe());

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void setData(List<RasporedEntity> rasporeds){
        RasporedDiffUtilCallback callback = new RasporedDiffUtilCallback(mDataSet, rasporeds);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        mDataSet.clear();
        mDataSet.addAll(rasporeds);
        result.dispatchUpdatesTo(this);
    }

    public class RasporedHolder extends RecyclerView.ViewHolder {

        TextView tv_profesor;
        TextView tv_predmet;
        TextView tv_ucionica;
        TextView tv_grupa;
        TextView tv_datum;

        public RasporedHolder(@NonNull View itemView) {
            super(itemView);
            tv_profesor = itemView.findViewById(R.id.frag1_profesor);
            tv_predmet = itemView.findViewById(R.id.frag1_predmet);
            tv_ucionica = itemView.findViewById(R.id.frag1_ucionica);
            tv_grupa = itemView.findViewById(R.id.frag1_grupa);
            tv_datum = itemView.findViewById(R.id.frag1_datum);

            tv_predmet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (mOnItemClickedListener != null && position != RecyclerView.NO_POSITION) {
                        RasporedEntity rasporedEntity = mDataSet.get(position);
                        mOnItemClickedListener.onItemClicked(rasporedEntity);
                    }
                }
            });

            tv_profesor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (mOnItemClickedListener1 != null && position != RecyclerView.NO_POSITION) {
                        RasporedEntity rasporedEntity = mDataSet.get(position);
                        mOnItemClickedListener1.onItemClicked1(rasporedEntity);
                    }
                }
            });

            tv_ucionica.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (mOnItemClickedListener2 != null && position != RecyclerView.NO_POSITION) {
                        RasporedEntity rasporedEntity = mDataSet.get(position);
                        mOnItemClickedListener2.onItemClicked2(rasporedEntity);
                    }
                }
            });
        }
    }

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        mOnItemClickedListener = onItemClickedListener;
    }

    public interface OnItemClickedListener {
        void onItemClicked(RasporedEntity rasporedEntity);
    }

    public void setOnItemClickedListener1(OnItemClickedListener1 onItemClickedListener1) {
        mOnItemClickedListener1 = onItemClickedListener1;
    }

    public interface OnItemClickedListener1 {
        void onItemClicked1(RasporedEntity rasporedEntity);
    }

    public void setOnItemClickedListener2(OnItemClickedListener2 onItemClickedListener2) {
        mOnItemClickedListener2 = onItemClickedListener2;
    }

    public interface OnItemClickedListener2 {
        void onItemClicked2(RasporedEntity rasporedEntity);
    }
}