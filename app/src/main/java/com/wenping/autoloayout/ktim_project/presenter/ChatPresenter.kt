package com.wenping.autoloayout.ktim_project.presenter

import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.wenping.autoloayout.ktim_project.adapter.EMCallBackAdapter
import com.wenping.autoloayout.ktim_project.contract.ChatContract
import org.jetbrains.anko.doAsync

/**
 * @author WenPing
 * @date 2018/4/20
 * @decription:
 *<p>
 */
class ChatPresenter(val view:ChatContract.View) :ChatContract.ChatPresenter{

    val messages = mutableListOf<EMMessage>()

    override fun sendMessage(contact: String, message: String) {

        val emMessage = EMMessage.createTxtSendMessage(message,contact)
        emMessage.setMessageStatusCallback(object :EMCallBackAdapter(){
            override fun onSuccess() {
                //这是在子线程中
               uiThread {  view.onSendMessageSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                uiThread {  view.onSendMessageFailed() }
            }
        })
        messages.add(emMessage)
        view.onStartSendMessage()
        EMClient.getInstance().chatManager().sendMessage(emMessage)
    }

    override fun addMessage(userName: String, p0: MutableList<EMMessage>?) {
        //加入到当前的消息列表
        //let 做非空检查
        p0?.let {
            messages.addAll(p0)
        }
        //更新消息为已读;获取 和联系人的会话
        val conversation = EMClient.getInstance().chatManager().getConversation(userName)
        conversation.markAllMessagesAsRead()
    }

    override fun loadMessages(userName: String) {
        doAsync {
            val conversation = EMClient.getInstance()
                    .chatManager().getConversation(userName)
            messages.addAll(conversation.allMessages)
            uiThread {
                view.onMessageLoaded()
            }
        }
    }
}