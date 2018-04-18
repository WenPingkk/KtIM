package com.wenping.autoloayout.ktim_project.app

import android.app.Application
import cn.bmob.v3.Bmob
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions
import com.wenping.autoloayout.ktim_project.BuildConfig

/**
 * Author WenPing
 * CreateTime 2018/4/9.
 * Description:
 */
class GlobalApplication : Application() {

    companion object {
        lateinit var instance:GlobalApplication
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        //初始化
        EMClient.getInstance().init(applicationContext, EMOptions())
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)

        Bmob.initialize(applicationContext, "df5a02bd4c7875549784ceb033407cc4")
//        Bmob.initialize(applicationContext, "ef808b3b36ee22248d19f04287ce5b37")
    }

}