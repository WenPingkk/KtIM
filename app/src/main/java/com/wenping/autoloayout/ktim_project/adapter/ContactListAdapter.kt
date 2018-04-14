package com.wenping.autoloayout.ktim_project.adapter

import android.app.AlertDialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.wenping.autoloayout.ktim_project.R
import com.wenping.autoloayout.ktim_project.data.ContactListItem
import com.wenping.autoloayout.ktim_project.ui.activity.ChatActivity
import com.wenping.autoloayout.ktim_project.widget.ContactListItemView
import org.jetbrains.anko.startActivity

/**
 * Author WenPing
 * CreateTime 2018/4/14.
 * Description:
 */
class ContactListAdapter(val context: Context, val contactListItems: MutableList<ContactListItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**
     * 创建viewholder对应的itemview
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContactListItemViewHolder(ContactListItemView(context))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val contactListItemView = holder.itemView as ContactListItemView
        contactListItemView.bindView(contactListItems.get(position))
        //数组的方式获取联系人
        val userName = contactListItems[position].userName
        contactListItemView.setOnClickListener {
            context.startActivity<ChatActivity>("userName" to userName)
        }
        contactListItemView.setOnLongClickListener {
            val message = String.format(context.getString(R.string.delete_friend_message),userName)
            AlertDialog.Builder(context)
                    .setTitle(R.string.delete_friend_title)
                    .setMessage(message)
                    .setNegativeButton(R.string.cancel,null)
                    .setPositiveButton(R.string.confirm) { dialog, which ->
                        deleteFriend(userName)
                    }.create()
            true
        }
    }

    private fun deleteFriend(userName: String) {

    }

    override fun getItemCount(): Int = contactListItems.size

    //创建viewholder
    class ContactListItemViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

    }
}