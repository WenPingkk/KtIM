package com.wenping.autoloayout.ktim_project.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMMessage
import com.wenping.autoloayout.ktim_project.R

/**
 * @author WenPing
 * @date 2018/4/18
 * @decription:
 *<p>
 */
class SendMessageItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    fun bindView(emMessage: EMMessage, showTimeStamp: Boolean) {

    }

    init {

        View.inflate(context,R.layout.view_send_message_item,this)

    }

}