package com.wenping.autoloayout.ktim_project

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Author WenPing
 * CreateTime 2018/4/7.
 * Description:
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        init()

    }

    /**
     * 初始化公共的功能；子类可以复写这个方法
     */
    open fun init() {
    }

    //子类必须实现这个方法，并返回一个布局id
    abstract fun getLayoutId(): Int

}