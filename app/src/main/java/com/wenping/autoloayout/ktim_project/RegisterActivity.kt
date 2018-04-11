package com.wenping.autoloayout.ktim_project

import com.wenping.autoloayout.ktim_project.contract.RegisterContract
import com.wenping.autoloayout.ktim_project.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

/**
 * Author WenPing
 * CreateTime 2018/4/11.
 * Description:
 */
class RegisterActivity : BaseActivity() ,RegisterContract.View{

    val presenter = RegisterPresenter(this)

    override fun init() {
        super.init()
        register.setOnClickListener{
            register()
        }
        confirmPassword.setOnEditorActionListener { v, actionId, event ->
            register()
            true
        }
    }

    /**
     * 注册功能->presenter
     */
    fun register() {
        val userName = userName.text.trim().toString()
        val password = password.text.trim().toString()
        val confirmPassword = confirmPassword.text.trim().toString()
        presenter.register(userName,password,confirmPassword)
        hideSoftKeyBoard()
    }

    override fun onUserNameError() {

        userName.error = getString(R.string.user_name_error)

    }

    override fun onPasswodError() {
        password.error = getString(R.string.password_error)
    }

    override fun onConfirmPasswordError() {
        confirmPassword.error = getString(R.string.confirm_password_error)
    }

    override fun onStartRegister() {
        showProgressDialog(getString(R.string.registering))
    }

    override fun onRegisterSuccess() {
        dissProgressDialog()

        finish()
    }

    override fun onRegisterFailedO() {
        dissProgressDialog()
        toast(R.string.register_failed)
    }

    override fun getLayoutId(): Int  = R.layout.activity_register

}