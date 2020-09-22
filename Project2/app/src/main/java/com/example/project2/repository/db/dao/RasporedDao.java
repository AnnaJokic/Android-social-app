package com.example.project2.repository.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.project2.model.Raspored;
import com.example.project2.repository.db.entity.RasporedEntity;

import java.util.List;

@Dao
public interface RasporedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRasporeds(List<RasporedEntity> rasporeds);

    @Query("DELETE FROM raspored")
     void nukeTable();

    @Query("SELECT * FROM raspored")
    LiveData<List<RasporedEntity>> getAllRasporeds();

    @Query("SELECT * FROM raspored " +
            " WHERE dan LIKE :dan || '%' " +
            "AND grupe LIKE '%' || :grupa || '%' "+
            "AND (predmet LIKE :predmet  || '%'  OR nastavnik LIKE :predmet  || '%')")
    LiveData<List<RasporedEntity>> getFilteredRasporeds(String predmet, String dan, String grupa);


    @Query("SELECT * FROM raspored " +
            "WHERE predmet LIKE :filter || '%' " +
            "OR nastavnik LIKE :filter || '%' " +
            "OR ucionica LIKE :filter")
    LiveData<List<RasporedEntity>> getFilteredRasporeds1(String filter);
}
