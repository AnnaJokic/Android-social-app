package com.example.project2.Util;

import androidx.recyclerview.widget.DiffUtil;
import com.example.project2.model.Raspored;
import com.example.project2.repository.db.entity.RasporedEntity;

import java.util.List;

public class RasporedDiffUtilCallback extends DiffUtil.Callback {

    private List<RasporedEntity> mOldList;
    private List<RasporedEntity> mNewList;

    public RasporedDiffUtilCallback(List<RasporedEntity> oldList, List<RasporedEntity> newList){
        mOldList = oldList;
        mNewList = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        //Raspored oldRaspored = mOldList.get(oldItemPosition);
        //Raspored newRaspored = mNewList.get(newItemPosition);
        //return oldRaspored.getId() == newRaspored.getId();
        return false;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        //Raspored oldRaspored = mOldList.get(oldItemPosition);
        //Raspored newRaspored = mNewList.get(newItemPosition);
        return false;
    }



}
