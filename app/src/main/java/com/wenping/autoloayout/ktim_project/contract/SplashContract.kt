package com.wenping.autoloayout.ktim_project.contract

/**
 * Author WenPing
 * CreateTime 2018/4/8.
 * Description:
 */
interface SplashContract {

    interface Presenter : BasePresenter {
        //检查是否登陆
        fun checkLoginStatus()

    }
    interface View{
        //没登录
        fun onNotLoginedIn()

        //已登陆
        fun onLoginedIn()

    }
}