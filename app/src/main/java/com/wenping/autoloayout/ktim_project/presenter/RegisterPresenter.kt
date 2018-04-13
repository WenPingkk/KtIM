package com.wenping.autoloayout.ktim_project.presenter

import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
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

                    //开始注册
                    registerBmob(userName,password)

                }else view.onConfirmPasswordError()
            }else view.onPasswodError()
        }else view.onUserNameError()
    }

    private fun registerBmob(userName: String, password: String) {

        val bu = BmobUser()
        bu.username = userName
        bu.setPassword(password)

        //注意 不能用save方法进行注册
        bu.signUp<BmobUser>(object:SaveListener<BmobUser>(){
            override fun done(p0: BmobUser?, e: BmobException?) {
                if (e == null) {
                    //注册成功

                    //注册到环信
                } else {
                    //注册失败
                    view.onRegisterFailedO()
                }
            }
        })

    }
}