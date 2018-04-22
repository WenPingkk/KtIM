package com.wenping.autoloayout.ktim_project.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMConversation
import com.wenping.autoloayout.ktim_project.R

/**
 * Author WenPing
 * CreateTime 2018/4/22.
 * Description:
 */
class ConversationItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.view_conversation_item,this)
    }

    fun bindView(emConversation: EMConversation) {



    }


}