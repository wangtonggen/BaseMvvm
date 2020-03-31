package com.example.basemvvm.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * author: wtg
 * date:2020/3/31 0031
 * desc:
 */
@Entity
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String userName;

    public String password;


}
