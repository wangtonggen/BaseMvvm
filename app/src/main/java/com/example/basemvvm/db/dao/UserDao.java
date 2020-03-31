package com.example.basemvvm.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.basemvvm.db.entity.UserEntity;

import java.util.List;

/**
 * author: wtg
 * date:2020/3/31 0031
 * desc:
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM userentity")
    List<UserEntity> getAll();
}
