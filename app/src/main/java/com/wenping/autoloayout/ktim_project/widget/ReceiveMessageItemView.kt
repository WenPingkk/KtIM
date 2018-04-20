package com.wenping.autoloayout.ktim_project.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMMessage
import com.wenping.autoloayout.ktim_project.R
import kotlinx.android.synthetic.main.view_send_message_item.view.*

/**
 * @author WenPing
 * @date 2018/4/18
 * @decription:
 *<p>
 */
class ReceiveMessageItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    fun bindView(emMessage: EMMessage, showTimeStamp: Boolean) {
        sendMessage.text = emMessage.userName
    }

    init {

        View.inflate(context,R.layout.view_receive_message_item,this)

    }

}