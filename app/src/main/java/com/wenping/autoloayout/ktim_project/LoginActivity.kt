package com.wenping.autoloayout.ktim_project

import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
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

        newUser.setOnClickListener{
            startActivity<RegisterActivity>()
        }

        login.setOnClickListener{ login() }
        password.setOnEditorActionListener { v, actionId, event ->
            login()
            true
        }
    }

    fun login() {

        if (hasExternalWrittingPermission()) {
            //隐藏软键盘
            hideSoftKeyBoard()
            val userNameString = userName.text.trim().toString()
            val passwordString = password.text.trim().toString()
            presenter.login(userNameString, passwordString)
        } else {
            applyWriteExternalStoragePermission()
        }


    }

    private fun applyWriteExternalStoragePermission() {
        val permissions = arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        ActivityCompat.requestPermissions(
                this, permissions, 0)
    }

    //动态权限申请
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //用户用户同意权限
            login()
        } else {
            toast(R.string.permission_denied)
        }
    }

    private fun hasExternalWrittingPermission(): Boolean {
        val result = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
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