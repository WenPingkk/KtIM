package com.wenping.autoloayout.ktim_project.contract

import com.hyphenate.chat.EMMessage

/**
 * @author WenPing
 * @date 2018/4/20
 * @decription:
 *<p>
 */
interface ChatContract {

    interface ChatPresenter : BasePresenter {
        fun sendMessage(contact:String,message:String)
        fun addMessage(userName: String, p0: MutableList<EMMessage>?)
        fun loadMessages(userName: String)
        fun loadMoreMessages(userName: String)
    }

    interface View {
        fun onStartSendMessage()
        fun onSendMessageSuccess()
        fun onSendMessageFailed()
        fun onMessageLoaded()
        fun onMoreMessageLoaded(size: Int)

    }
}