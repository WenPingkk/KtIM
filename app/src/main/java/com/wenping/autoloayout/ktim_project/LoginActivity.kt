package com.wenping.autoloayout.ktim_project

import com.wenping.autoloayout.ktim_project.contract.LoginContract
import com.wenping.autoloayout.ktim_project.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Author WenPing
 * CreateTime 2018/4/8.
 * Description:
 */
class LoginActivity :BaseActivity(),LoginContract.View{

    val presenter = LoginPresenter(this)

    override fun init() {
        super.init()
        login.setOnClickListener{ login() }
        password.setOnEditorActionListener { v, actionId, event ->
            login()
            true
        }
    }

    fun login() {
        val userNameString = userName.text.trim().toString()
        val passwordString = password.text.trim().toString()
        presenter.login(userNameString,passwordString)
    }

    override fun onUsserNameError() {

//        userName.setError(getString(R.string.user_name_error))
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun onStartLogin() {
        //弹出一个进度条
        showProgressDialog(getString(R.string.logging))
    }

    override fun onLoginedSuccess() {
        //隐藏进度条
        dissProgressDialog()
        //进入到主界面
        startActivity<MainActivity>()
        //退出当前页面
        finish()
    }

    override fun onLoginedFailed() {
        //隐藏进度条
        dissProgressDialog()
        ///弹出toast
        toast(getString(R.string.login_failed))
    }

    override fun getLayoutId(): Int = R.layout.activity_login
}