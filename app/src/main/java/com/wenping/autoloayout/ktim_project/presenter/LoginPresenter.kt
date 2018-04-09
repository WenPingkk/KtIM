package com.wenping.autoloayout.ktim_project.presenter

import com.wenping.autoloayout.ktim_project.contract.LoginContract
import com.wenping.autoloayout.ktim_project.extentions.isValidPassword
import com.wenping.autoloayout.ktim_project.extentions.isValidUserName

/**
 * Author WenPing
 * CreateTime 2018/4/9.
 * Description:
 */
class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter {

    override fun login(userName: String, password: String) {

        //校验用户名
        if (userName.isValidUserName()) {
            //用户名满足条件，继续校验密码
            if (password.isValidPassword()) {
                view.onStartLogin()
                loginEaseMob(userName,password)
            } else view.onPasswordError()
        } else view.onUsserNameError()

        //校验密码


    }

    private fun loginEaseMob(userName: String, password: String) {

    }

}