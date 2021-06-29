package com.example.simpleroomdatabase.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Entity(
    val title : String,
    val highPriority : Boolean
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}