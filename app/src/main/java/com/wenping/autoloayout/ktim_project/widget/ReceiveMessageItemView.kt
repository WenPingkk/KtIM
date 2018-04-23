package com.wenping.autoloayout.ktim_project.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import com.wenping.autoloayout.ktim_project.R
import kotlinx.android.synthetic.main.view_receive_message_item.view.*
import java.util.*

/**
 * @author WenPing
 * @date 2018/4/18
 * @decription:
 *<p>
 */
class ReceiveMessageItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {


    fun bindView(emMessage: EMMessage, showTimeStamp: Boolean) {
        updateMessage(emMessage)
        updateTimeStamp(emMessage, showTimeStamp)
    }

    private fun updateTimeStamp(emMessage: EMMessage, showTimeStamp: Boolean) {
        if (showTimeStamp) {
            timestamp.visibility = View.VISIBLE
            timestamp.text = DateUtils.getTimestampString(Date(emMessage.msgTime))
        } else timestamp.visibility = View.GONE
    }

    private fun updateMessage(emMessage: EMMessage) {
        if (emMessage.type == EMMessage.Type.TXT) {
            receiveMessage.text = (emMessage.body as EMTextMessageBody).message
        } else {
            receiveMessage.text = context.getString(R.string.no_text_message)
        }

    }

    init {

        View.inflate(context, R.layout.view_receive_message_item, this)

    }

}