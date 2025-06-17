package com.nextbigthing.ticky.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AppModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun appDao(): AppDao
}