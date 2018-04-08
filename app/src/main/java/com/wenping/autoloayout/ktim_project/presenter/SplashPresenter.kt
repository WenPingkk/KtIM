package com.wenping.autoloayout.ktim_project.presenter

import com.hyphenate.chat.EMClient
import com.wenping.autoloayout.ktim_project.contract.SplashContract

/**
 * Author WenPing
 * CreateTime 2018/4/8.
 * Description:
 */
class SplashPresenter(val view: SplashContract.View) : SplashContract.Presenter {

    override fun checkLoginStatus() {
        if (isLoginedIn()) view.onNotLoginedIn() else view.onNotLoginedIn()
    }

    //Moodel层 环信登场
    private fun isLoginedIn(): Boolean = EMClient.getInstance().isConnected&&EMClient.getInstance().isLoggedInBefore

}