package com.example.jetpacktest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel;
    lateinit var sp : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //先取出之前保存的值，没有的话默认为0，然后传入一个MainViewModelFactory参数(传给它的构造函数，才能最终传递给MainViewModel的构造函数)
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)

        //通过ViewModelProviders来获取viewModel实例
        viewModel = ViewModelProvider(this, MainViewModelFactory(countReserved)).get(MainViewModel::class.java)

        plusOneBtn.setOnClickListener{
            viewModel.plusOne()
        }

        clearBtn.setOnClickListener {
            viewModel.clear()
        }

        lifecycle.addObserver(MyObserver())

        refreshCounter()
    }

    private fun refreshCounter(){
        infoText.text = viewModel.counter.toString()
    }

    //不管程序是退出还是放在后台，都能保存当前值
    override fun onPause() {
        super.onPause()
        sp.edit { putInt("count_reserved", viewModel.counter.value ?: 0) }
    }
}