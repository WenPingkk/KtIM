package com.wenping.autoloayout.ktim_project.ui.activity

import com.hyphenate.EMConnectionListener
import com.hyphenate.EMError
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.wenping.autoloayout.ktim_project.R
import com.wenping.autoloayout.ktim_project.adapter.EMMessageListenerAdapter
import com.wenping.autoloayout.ktim_project.factory.FragmentFactory
import com.wenping.autoloayout.ktim_project.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int  = R.layout.activity_main

    val messageListener = object : EMMessageListenerAdapter(){
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            runOnUiThread { updateBottomBarUnReadCount() }
        }
    }

    override fun init() {
        super.init()
        bottomBar.setOnTabSelectListener {
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.replace(R.id.fragment_frame,FragmentFactory.instance.getFragment(it))
            beginTransaction.commit()
        }

        EMClient.getInstance().chatManager().addMessageListener(messageListener)
        //网络断开->判断当前的设备是否多设备登陆
        EMClient.getInstance().addConnectionListener(object :EMConnectionListener{
            override fun onConnected() {
            }

            override fun onDisconnected(p0: Int) {
                if (p0 == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                    startActivity<LoginActivity>()
                    finish()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        updateBottomBarUnReadCount()
    }

    fun updateBottomBarUnReadCount(){

        val tab = bottomBar.getTabWithId(R.id.tab_conversation)
        tab.setBadgeCount(EMClient.getInstance().chatManager().unreadMessageCount)

    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)

    }
}
