package com.nextbigthing.ticky.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AppModel(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "task") val task: String?
)
