package com.wenping.autoloayout.ktim_project.widget

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import com.wenping.autoloayout.ktim_project.R
import kotlinx.android.synthetic.main.view_send_message_item.view.*
import java.util.*

/**
 * @author WenPing
 * @date 2018/4/18
 * @decription:
 *<p>
 */
class SendMessageItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    fun bindView(emMessage: EMMessage, showTimeStamp: Boolean) {
        updateTimeStamp(emMessage)
    }

    private fun updateTimeStamp(emMessage: EMMessage) {

        timestamp.text = DateUtils.getTimestampString(
                Date(emMessage.msgTime)
        )
        updateMessage(emMessage)
        updateProgress(emMessage)
    }

    private fun updateProgress(emMessage: EMMessage) {
        emMessage.status().let {
            when (it) {
                EMMessage.Status.INPROGRESS->{
                    sendMessageProgress.visibility = View.VISIBLE
                    sendMessageProgress.setImageResource(R.drawable.send_message_progress)
                    val animationDrawable = sendMessageProgress.drawable as AnimationDrawable
                    animationDrawable.start()
                }
                EMMessage.Status.SUCCESS->{
                    sendMessageProgress.visibility = View.GONE
                }
                EMMessage.Status.FAIL->{
                    sendMessageProgress.visibility = View.VISIBLE
                    sendMessageProgress.setImageResource(R.mipmap.msg_error)

                }
            }
        }
    }

    private fun updateMessage(emMess: EMMessage) {
        if (emMess.type == EMMessage.Type.TXT) {
            sendMessage.text = (emMess.body as EMTextMessageBody).message
        } else {
            sendMessage.text = context.getString(R.string.no_text_message)
        }
    }


    init {

        View.inflate(context,R.layout.view_send_message_item,this)

    }

}