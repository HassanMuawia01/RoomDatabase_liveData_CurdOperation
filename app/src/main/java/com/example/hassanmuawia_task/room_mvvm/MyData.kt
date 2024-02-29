package com.example.hassanmuawia_task.room_mvvm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_table")
data class MyData (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String
)
