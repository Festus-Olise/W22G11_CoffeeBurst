package com.example.rocketfuel.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.rocketfuel.model.LoginDetails;

import java.util.List;

@Dao
public interface LoginDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOneStudent(LoginDetails student);

    @Query("SELECT * from loginDetails")
    List<LoginDetails> GetAllLoginDetails();

}
