package com.wenping.autoloayout.ktim_project.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.wenping.autoloayout.ktim_project.R

/**
 * @author WenPing
 * @date 2018/4/18
 * @decription:
 *<p>
 */
class ReceiveMessageItemView(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    init {

        View.inflate(context,R.layout.view_receive_message_item,this)

    }

}