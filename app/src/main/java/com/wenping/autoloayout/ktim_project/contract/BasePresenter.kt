package com.wenping.autoloayout.ktim_project.contract

import android.os.Handler
import android.os.Looper

/**
 * Author WenPing
 * CreateTime 2018/4/8.
 * Description:
 */
interface BasePresenter {

    companion object {
        val handler by lazy {
            Handler(Looper.getMainLooper())
        }
    }

    //函数
    fun uiThread(f:()->Unit){
        handler.post { f() }
    }

}