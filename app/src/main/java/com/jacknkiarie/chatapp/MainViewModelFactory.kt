package com.jacknkiarie.chatapp

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import android.app.Application
import androidx.lifecycle.ViewModelProvider


class MainViewModelFactory(private val mApplication: Application) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(mApplication) as T
    }
}