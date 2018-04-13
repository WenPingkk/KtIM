package com.wenping.autoloayout.ktim_project.ui.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager

/**
 * Author WenPing
 * CreateTime 2018/4/7.
 * Description:
 */
abstract class BaseActivity : AppCompatActivity() {

    val progressDialog by lazy {
        ProgressDialog(this)
    }

    val inputMethdManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    fun hideSoftKeyBoard() {
        inputMethdManager.hideSoftInputFromWindow(
                currentFocus.windowToken,0
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        //初始化
        init()

    }

    /**
     * 初始化公共的功能；
     * 子类可以复写这个方法；用open来修饰
     */
    open fun init() {}

    //子类必须实现这个方法，并返回一个布局id
    abstract fun getLayoutId(): Int

    //显示进度条
    fun showProgressDialog(message: String) {
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    fun dissProgressDialog() {
        progressDialog.dismiss()
    }

}