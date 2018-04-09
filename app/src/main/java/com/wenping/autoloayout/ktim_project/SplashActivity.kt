package com.wenping.autoloayout.ktim_project

import android.os.Handler
import com.wenping.autoloayout.ktim_project.contract.SplashContract
import com.wenping.autoloayout.ktim_project.presenter.SplashPresenter
import org.jetbrains.anko.startActivity

/**
 * Author WenPing
 * CreateTime 2018/4/8.
 * Description:
 */
class SplashActivity : BaseActivity(), SplashContract.View {

    val presenter by lazy { SplashPresenter(this) }
//    val presenter = SplashPresenter(this)

    companion object {
        val DELAY = 2000L
    }

    //布局
    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun init() {
        super.init()
        presenter.checkLoginStatus()
    }

    val handler by lazy {
        Handler()
    }

    //延时两秒进入登陆界面;使用lambda表达式
    override fun onNotLoginedIn() {
        handler.postDelayed({
            //s使用Anko库
            startActivity<LoginActivity>()
            finish()
        }, DELAY)

    }

    /**
     * 跳转到主界面
     */
    override fun onLoginedIn() {
        startActivity<MainActivity>()
        finish()
    }
}