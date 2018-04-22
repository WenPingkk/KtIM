package com.wenping.autoloayout.ktim_project.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMConversation
import com.wenping.autoloayout.ktim_project.R
import com.wenping.autoloayout.ktim_project.adapter.ConversationListAdapter
import com.wenping.autoloayout.ktim_project.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_conversation.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Author WenPing
 * CreateTime 2018/4/13.
 * Description:
 */
class ConversationFragment : BaseFragment() {

    var convarsations = mutableListOf<EMConversation>()

    override fun getLayoutId(): Int = R.layout.fragment_conversation

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.message)

        recyclerView.apply {

            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ConversationListAdapter(context,convarsations)
        }
        loadConversations()
    }

    private fun loadConversations() {
        doAsync {
            convarsations.clear()
            val allConversations = EMClient.getInstance().chatManager().allConversations
            convarsations.addAll(allConversations.values)
            uiThread {
                recyclerView.adapter.notifyDataSetChanged()
            }

        }

    }


}