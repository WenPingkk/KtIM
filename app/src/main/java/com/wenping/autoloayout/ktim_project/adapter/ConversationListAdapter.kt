package com.wenping.autoloayout.ktim_project.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hyphenate.chat.EMConversation
import com.wenping.autoloayout.ktim_project.ui.activity.ChatActivity
import com.wenping.autoloayout.ktim_project.widget.ConversationItemView
import org.jetbrains.anko.startActivity
/**
 * Author WenPing
 * CreateTime 2018/4/22.
 * Description:
 */
class ConversationListAdapter(val context: Context, val convarsations: MutableList<EMConversation>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ConversationListItemViewHolder(ConversationItemView(context))
    }

    override fun getItemCount(): Int = convarsations.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val conversationItemView = holder.itemView as ConversationItemView
        conversationItemView.bindView(convarsations[position])
        conversationItemView.setOnClickListener{
            context.startActivity<ChatActivity>(
                    "username" to convarsations[position].conversationId()
            )
        }
    }

    class ConversationListItemViewHolder (val view:View): RecyclerView.ViewHolder(view) {

    }

}