package com.example.uthsmarttasks.api

import com.example.uthsmarttasks.model.Task
import com.example.uthsmarttasks.response.TaskResponse
import com.example.uthsmarttasks.response.TaskSingleResponse
import retrofit2.Response
import retrofit2.http.*

interface TaskApiService {
    @GET("researchUTH/tasks")
    suspend fun getTasks(): Response<TaskResponse>

    @GET("researchUTH/task/{id}")
    suspend fun getTask(@Path("id") id: Int): Response<TaskSingleResponse>

    @DELETE("researchUTH/task/{id}")
    suspend fun deleteTask(@Path("id") id: Int): Response<Unit>
}