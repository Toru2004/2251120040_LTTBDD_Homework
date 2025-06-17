package com.example.uthsmarttasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uthsmarttasks.model.Task
import com.example.uthsmarttasks.response.TaskSingleResponse
import com.example.uthsmarttasks.retrofitInstance.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {

    private val _taskList = MutableStateFlow<List<Task>>(emptyList())
    val taskList: StateFlow<List<Task>> = _taskList

    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask: StateFlow<Task?> = _selectedTask

    fun loadTasks() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTasks()
                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        if (body.isSuccess) {
                            _taskList.value = body.data
                        } else {
                            println("API success=false: ${body.message}")
                        }
                    }
                } else {
                    println("loadTasks failed: ${response.code()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getTaskById(id: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTask(id)
                if (response.isSuccessful) {
                    response.body()?.let { body: TaskSingleResponse ->  // 👈 thêm kiểu rõ ràng ở đây nếu cần
                        if (body.isSuccess) {
                            _selectedTask.value = body.data
                        }
                    }
                } else {
                    println("getTaskById failed: ${response.code()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteTask(id: Int, onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.deleteTask(id)
                if (response.isSuccessful) {
                    loadTasks()
                    onSuccess()
                } else {
                    println("deleteTask failed: ${response.code()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
