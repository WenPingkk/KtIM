package com.wenping.autoloayout.ktim_project.contract

/**
 * Author WenPing
 * CreateTime 2018/4/9.
 * Description:
 */
interface LoginContract {

    interface Presenter : BasePresenter {
        fun login(userName:String,password:String)
    }

    interface View{

        fun onUsserNameError()

        fun onPasswordError()

        fun onStartLogin()

        fun onLoginedSuccess()

        fun onLoginedFailed()

    }
}