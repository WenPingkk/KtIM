package com.wenping.autoloayout.ktim_project.presenter

import com.hyphenate.chat.EMClient
import com.wenping.autoloayout.ktim_project.adapter.EMCallBackAdapter
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
        EMClient.getInstance()
                .login(userName,password,object : EMCallBackAdapter() {
                    override fun onSuccess() {
                        EMClient.getInstance().groupManager().loadAllGroups()
                        EMClient.getInstance().chatManager().loadAllConversations()

                        //在子线程中通知view层
                        uiThread {
                            view.onLoginedSuccess()
                        }
                    }

                    override fun onError(p0: Int, p1: String?) {
                        super.onError(p0, p1)
                        uiThread {
                            view.onLoginedFailed()
                        }
                    }
                })
    }

}