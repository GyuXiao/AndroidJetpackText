package com.example.jetpacktest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/***
 * 向ViewModel传递参数
 */
class MainViewModelFactory(private val countReserved: Int) : ViewModelProvider.Factory{//重写create函数
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(countReserved) as T//将countReserved传进去， 返回
    }
}