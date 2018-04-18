package com.wenping.autoloayout.ktim_project.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.wenping.autoloayout.ktim_project.R
import com.wenping.autoloayout.ktim_project.data.AddFriendItem
import kotlinx.android.synthetic.main.view_add_friend_item.view.*
import org.jetbrains.anko.toast

/**
 * @author WenPing
 * @date 2018/4/18
 * @decription:
 *<p>
 */
class AddFriendListItemView(context: Context?, attrs: AttributeSet?=null) : RelativeLayout(context, attrs) {
    fun bindView(addFriendItem: AddFriendItem) {

        userName.text = addFriendItem.userName
        timestamp.text = addFriendItem.timeStamp

        add.setOnClickListener{
            context.toast("xxx")
        }

    }

    init {

        View.inflate(context, R.layout.view_add_friend_item,this)

    }

}