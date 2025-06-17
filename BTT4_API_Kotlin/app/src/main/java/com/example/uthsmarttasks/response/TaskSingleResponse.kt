package com.example.uthsmarttasks.response

import com.example.uthsmarttasks.model.Task

data class TaskSingleResponse(
    val isSuccess: Boolean,
    val message: String,
    val data: Task
)
