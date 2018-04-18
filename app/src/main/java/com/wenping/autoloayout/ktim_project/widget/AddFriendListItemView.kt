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
class AddFriendListItemView(context: Context?, attrs: AttributeSet?=null) : RelativeLayout(context, attrs) {

    init {

        View.inflate(context, R.layout.view_add_friend_item,this)

    }

}