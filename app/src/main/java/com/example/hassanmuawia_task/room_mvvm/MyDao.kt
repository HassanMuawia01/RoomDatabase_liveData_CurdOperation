package com.example.hassanmuawia_task.room_mvvm

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert_TextData(data: MyData)

    @Query("SELECT * FROM my_table ORDER BY id DESC")
    fun getAllData(): LiveData<List<MyData>>

    @Update
    suspend fun updateData(data: MyData)

    @Query("DELETE FROM my_table WHERE id = :id")
    suspend fun deleteData(id: Int)
}
