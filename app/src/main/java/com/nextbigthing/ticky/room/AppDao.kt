package com.nextbigthing.ticky.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AppDao {

    @Query("SELECT * FROM appmodel")
    fun getAll(): List<AppModel>

    @Insert
    fun insertUser(vararg users: AppModel)

    @Delete
    fun deleteUser(vararg users: AppModel)

    @Update
    fun updateUser(appModel: AppModel)

    @Query("UPDATE AppModel SET isChecked = :checked WHERE uid = :id")
    fun updateCheckState(id: Int, checked: Boolean)
}