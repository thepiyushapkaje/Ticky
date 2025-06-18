package com.nextbigthing.ticky.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AppDao {

    @Query("SELECT * FROM appmodel")
    fun getAll(): List<AppModel>

    @Insert
    fun insertUser(vararg users: AppModel)

    @Delete
    fun deleteUser(vararg users: AppModel)
}