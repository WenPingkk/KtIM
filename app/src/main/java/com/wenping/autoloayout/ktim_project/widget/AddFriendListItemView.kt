package com.wenping.autoloayout.ktim_project.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient
import com.wenping.autoloayout.ktim_project.R
import com.wenping.autoloayout.ktim_project.adapter.EMCallBackAdapter
import com.wenping.autoloayout.ktim_project.data.AddFriendItem
import kotlinx.android.synthetic.main.view_add_friend_item.view.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

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

    fun bindView(addFriendItem: AddFriendItem) {
        if (addFriendItem.isAdd) {
            add.isEnabled = false
            add.text = context.getString(R.string.already_added)
        } else {
            add.isEnabled = true
            add.text = context.getString(R.string.add)
        }
        userName.text = addFriendItem.userName
        timestamp.text = addFriendItem.timeStamp

        add.setOnClickListener{
            addFriend(addFriendItem.userName)
        }
    }

    private fun addFriend(userName: String) {

        EMClient.getInstance().contactManager()
                .aysncAddContact(
                        userName,null,object:EMCallBackAdapter(){
                    override fun onSuccess() {
                        super.onSuccess()
                        context.runOnUiThread { toast(R.string.send_add_friend_success) }
                    }

                    override fun onError(p0: Int, p1: String?) {
                        super.onError(p0, p1)
                        context.runOnUiThread { toast(R.string.send_add_friend_failed) }
                    }
                })

    }
}