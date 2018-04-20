package com.wenping.autoloayout.ktim_project.presenter

import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.wenping.autoloayout.ktim_project.adapter.EMCallBackAdapter
import com.wenping.autoloayout.ktim_project.contract.ChatContract

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
}