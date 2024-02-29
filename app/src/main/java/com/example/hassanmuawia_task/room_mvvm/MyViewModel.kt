package com.example.hassanmuawia_task.room_mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val dao: MyDao
    private val repository: Repository

    val allTextData: LiveData<List<MyData>>

    init {
        val database = MyDatabase.getDatabase(application)
        dao = database.myDao()
        repository = Repository(dao)
        allTextData = repository.getAll_TextData
    }

    fun deleteText(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(id)
    }

    fun updateText(data: MyData) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(data)
    }

    fun addText(data: MyData) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(data)
    }
}
