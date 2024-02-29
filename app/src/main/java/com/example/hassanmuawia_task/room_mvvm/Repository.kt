package com.example.hassanmuawia_task.room_mvvm


import androidx.lifecycle.LiveData

class Repository(private val textDao: MyDao) {

    val getAll_TextData: LiveData<List<MyData>> = textDao.getAllData()

    suspend fun insert(data: MyData) {
        textDao.insert_TextData(data)
    }

    suspend fun delete(id: Int) {
        textDao.deleteData(id)
    }

    suspend fun update(data: MyData) {
        textDao.updateData(data)
    }
}
