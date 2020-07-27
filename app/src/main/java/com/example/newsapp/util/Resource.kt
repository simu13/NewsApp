package com.example.newsapp.util

sealed class Resource<T>(
    val data:T? = null,
    val message:String?=null
) {
    class success<T>(data: T):Resource<T>(data)
    class error<T>(message: String,data: T?):Resource<T>(data,message)
    class loading<T>:Resource<T>()
}