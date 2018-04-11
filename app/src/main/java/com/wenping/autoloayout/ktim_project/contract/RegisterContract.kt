package com.wenping.autoloayout.ktim_project.contract

/**
 * Author WenPing
 * CreateTime 2018/4/11.
 * Description:注册页面的协议层
 */
interface RegisterContract {

    interface  Presenter :BasePresenter{

        fun register(userName:String,password:String,confirmPassword:String)

    }

    interface View{

        fun onUserNameError()

        fun onPasswodError()

        fun onConfirmPasswordError()

        fun onStartRegister()

        fun onRegisterSuccess()

        fun onRegisterFailedO()

    }

}