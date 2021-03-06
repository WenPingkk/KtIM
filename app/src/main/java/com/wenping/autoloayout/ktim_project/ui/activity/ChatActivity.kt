package com.wenping.autoloayout.ktim_project.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.wenping.autoloayout.ktim_project.R
import com.wenping.autoloayout.ktim_project.adapter.EMMessageListenerAdapter
import com.wenping.autoloayout.ktim_project.adapter.MessageListAdapter
import com.wenping.autoloayout.ktim_project.contract.ChatContract
import com.wenping.autoloayout.ktim_project.presenter.ChatPresenter
import com.wenping.autoloayout.ktim_project.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 * Author WenPing
 * CreateTime 2018/4/14.
 * Description:
 */
class ChatActivity : BaseActivity() ,ChatContract.View{

    override fun getLayoutId(): Int = R.layout.activity_chat

    lateinit var userName :String

    val presenter by lazy { ChatPresenter(this) }

    val messageListener = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            presenter.addMessage(userName,p0)
            runOnUiThread {
                recyclerView.adapter.notifyDataSetChanged()
                scrollToBottom()
            }
        }

    }

    override fun init() {
        super.init()
        initHeader()

        initEditText()

        initRecyclerView()
        EMClient.getInstance().chatManager().addMessageListener(messageListener)
        send.setOnClickListener{send()}

        presenter.loadMessages(userName)
    }



    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MessageListAdapter(context,presenter.messages)
            addOnScrollListener(object :RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                    //当recyclerview是一个空闲的状态
                    //检查是否滑动到顶部，要加载更多数据
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val linearLayoutManager = layoutManager as LinearLayoutManager
                        if (linearLayoutManager.findFirstVisibleItemPosition() == 0) {
                            presenter.loadMoreMessages(userName)
                        }
                    }
                }
            })
        }
    }

    private fun initEditText() {
        edit.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                send.isEnabled = !s.isNullOrEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        edit.setOnEditorActionListener { v, actionId, event ->
            send()
            true
        }
    }

    fun send() {
        hideSoftKeyBoard()
        val message = edit.text.trim().toString()
        presenter.sendMessage(userName,message)
    }

    private fun initHeader() {
       back.visibility = View.VISIBLE
        back.setOnClickListener{finish()}

        //获取聊天用户名
        userName = intent.getStringExtra("username")

        val titleString = String.format(getString(R.string.chat_title),userName)

        headerTitle.text = titleString

    }

    override fun onStartSendMessage() {
        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun onSendMessageSuccess() {
        recyclerView.adapter.notifyDataSetChanged()
        toast(R.string.send_message_success)
        //清空 编辑框
        edit.text.clear()
        scrollToBottom()
    }

    private fun scrollToBottom() {
        recyclerView.scrollToPosition(presenter.messages.size-1)
    }

    override fun onSendMessageFailed() {
        toast(R.string.send_message_failed)
        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }

    override fun onMessageLoaded() {
        recyclerView.adapter.notifyDataSetChanged()
        scrollToBottom()
    }

    override fun onMoreMessageLoaded(size: Int) {
        recyclerView.adapter.notifyDataSetChanged()
        recyclerView.scrollToPosition(size)
    }


}