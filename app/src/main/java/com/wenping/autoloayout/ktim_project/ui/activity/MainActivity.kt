package com.wenping.autoloayout.ktim_project.ui.activity

import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.wenping.autoloayout.ktim_project.R
import com.wenping.autoloayout.ktim_project.adapter.EMMessageListenerAdapter
import com.wenping.autoloayout.ktim_project.factory.FragmentFactory
import com.wenping.autoloayout.ktim_project.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

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
