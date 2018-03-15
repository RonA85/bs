//package com.bestfriend.data;
//
//import android.arch.persistence.room.Dao;
//import android.arch.persistence.room.Insert;
//import android.arch.persistence.room.Query;
//
//import com.bestfriend.model.Park;
//
//import java.util.List;
//
///**
// * Created by mac on 15/03/2018.
// */
//
//@Dao
//public interface ParkDao {
//
//    @Query("SELECT * FROM park")
//    List<Park> getAll();
//
//    @Query("SELECT * FROM park WHERE id = :id")
//    Park loadParkById(int id);
//
//    @Query("SELECT * FROM park WHERE first_name LIKE :first AND "
//            + "last_name LIKE :last LIMIT 1")
//    Park findByName(String first, String last);
//
//    @Insert
//    void insertAll(Park... parks);
//}
