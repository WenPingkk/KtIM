package com.wenping.autoloayout.ktim_project.presenter

import com.wenping.autoloayout.ktim_project.contract.RegisterContract
import com.wenping.autoloayout.ktim_project.extentions.isValidPassword
import com.wenping.autoloayout.ktim_project.extentions.isValidUserName

/**
 * Author WenPing
 * CreateTime 2018/4/12.
 * Description:
 */
class RegisterPresenter(val view:RegisterContract.View) :RegisterContract.Presenter{

    override fun register(userName: String, password: String,confirmPassword:String) {
        //校验
        if (userName.isValidUserName()) {
            if (password.isValidPassword()) {
                //检查确认密码
                if (confirmPassword.equals(password)) {
                    //密码和确认密码一支
                    view.onStartRegister()
                }else view.onConfirmPasswordError()
            }else view.onPasswodError()
        }else view.onUserNameError()
    }
}