package com.eru.month6hw2.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding>: AppCompatActivity() {

    protected lateinit var binding: VB

    protected abstract fun inflateViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding()
        setContentView(binding.root)

        isConnected()
        initViews()
        initViewModel()
        initListener()
    }

    open fun initViews(){}
    open fun initListener(){}
    open fun initViewModel(){}
    open fun isConnected(){}
}