package com.codetest.comicapp.common

sealed class Resource<out T>{
    data class Success<out R>(val data : R): Resource<R>()
    data class Error(val message : String): Resource<Nothing>()
}