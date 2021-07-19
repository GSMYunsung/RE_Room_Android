package com.example.simpleroomdatabase.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Entity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id : Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "highPriority") val highPriority: Boolean
)