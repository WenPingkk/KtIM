package com.wenping.autoloayout.ktim_project.ui.fragment

import com.hyphenate.chat.EMClient
import com.wenping.autoloayout.ktim_project.R
import com.wenping.autoloayout.ktim_project.adapter.EMCallBackAdapter
import com.wenping.autoloayout.ktim_project.ui.activity.LoginActivity
import com.wenping.autoloayout.ktim_project.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_dynamic.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Author WenPing
 * CreateTime 2018/4/13.
 * Description:
 */
class DynamicFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_dynamic

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.dynamic)

        //格式化字符串；实现原理：http://kgd1120.iteye.com/blog/1293633
        val logoutString = String.format(getString(R.string.logout,EMClient.getInstance().currentUser))
        logout.text = logoutString

        //推出登陆
        logout.setOnClickListener{logout()}

    }

    /**
     *动态页面退出操作
     */
    private fun logout() {
        EMClient.getInstance().logout(true,object :EMCallBackAdapter(){
            override fun onSuccess() {
                super.onSuccess()
                context?.toast(R.string.logout_success)
                context?.runOnUiThread {
                context?.startActivity<LoginActivity>()
                }
            }

            override fun onError(p0: Int, p1: String?) {
                super.onError(p0, p1)
                context?.runOnUiThread {
                    toast(R.string.logout_failed)
                }
            }

        })
    }

}