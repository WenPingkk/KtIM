package com.wenping.autoloayout.ktim_project.presenter

import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.wenping.autoloayout.ktim_project.contract.RegisterContract
import com.wenping.autoloayout.ktim_project.extentions.isValidPassword
import com.wenping.autoloayout.ktim_project.extentions.isValidUserName
import org.jetbrains.anko.doAsync

/**
 * Author WenPing
 * CreateTime 2018/4/12.
 * Description:
 */
class RegisterPresenter(val view: RegisterContract.View) : RegisterContract.Presenter {

    override fun register(userName: String, password: String, confirmPassword: String) {
        //校验
        if (userName.isValidUserName()) {
            if (password.isValidPassword()) {
                //检查确认密码
                if (confirmPassword.equals(password)) {
                    //密码和确认密码一支
                    view.onStartRegister()

                    //开始注册
                    registerBmob(userName, password)

                } else view.onConfirmPasswordError()
            } else view.onPasswodError()
        } else view.onUserNameError()
    }

    private fun registerBmob(userName: String, password: String) {

        val bu = BmobUser()
        bu.username = userName
        bu.setPassword(password)

        //注意 不能用save方法进行注册
        bu.signUp<BmobUser>(object : SaveListener<BmobUser>() {
            override fun done(p0: BmobUser?, e: BmobException?) {
                if (e == null) {
                    //注册成功
                    //注册到环信
                    registereEaseMob(userName, password)
                } else {
                    //注册失败
                    if (e.errorCode == 202) view.onUserExist()
                        else view.onRegisterFailed()
                }
            }
        })

    }

    private fun registereEaseMob(userName: String, password: String) {
        //Anko库中提供的api
        doAsync {
            try {
                //注册成功，这是同步方法；doAsync->子线程中执行
                EMClient.getInstance().createAccount(userName, password)
                //注册成功
                uiThread {
                    view.onRegisterSuccess()
                }
            } catch (e: HyphenateException) {

            }
        }
    }
}