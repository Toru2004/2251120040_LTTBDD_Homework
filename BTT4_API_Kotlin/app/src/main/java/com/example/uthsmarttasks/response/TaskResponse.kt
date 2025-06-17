package com.example.uthsmarttasks.response

import com.example.uthsmarttasks.model.Task

data class TaskResponse(
    val isSuccess: Boolean,
    val message: String,
    val data: List<Task>
)