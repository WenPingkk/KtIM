package com.wenping.autoloayout.ktim_project.contract

/**
 * @author WenPing
 * @date 2018/4/20
 * @decription:
 *<p>
 */
interface ChatContract {

    interface ChatPresenter : BasePresenter {
        fun sendMessage(contact:String,message:String)
    }

    interface View {
        fun onStartSendMessage()
        fun onSendMessageSuccess()
        fun onSendMessageFailed()

    }
}