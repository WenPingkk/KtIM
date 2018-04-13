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

    override fun onCreate() {
        super.onCreate()
        initHuanXin()
    }

    private fun initHuanXin() {

        //初始化
        EMClient.getInstance().init(applicationContext, EMOptions());
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG);

        Bmob.initialize(applicationContext, "2e91983d054f9b525e9fd550052a3c56")
    }

}