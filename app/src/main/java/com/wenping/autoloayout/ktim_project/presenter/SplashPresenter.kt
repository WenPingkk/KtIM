package com.wenping.autoloayout.ktim_project.presenter

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

    private fun isLoginedIn(): Boolean {
        return false
    }

}