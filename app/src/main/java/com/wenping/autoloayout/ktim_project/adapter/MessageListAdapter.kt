package com.wenping.autoloayout.ktim_project.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hyphenate.chat.EMMessage
import com.hyphenate.util.DateUtils
import com.wenping.autoloayout.ktim_project.widget.ReceiveMessageItemView
import com.wenping.autoloayout.ktim_project.widget.SendMessageItemView

/**
 * @author WenPing
 * @date 2018/4/20
 * @decription:
 *<p>
 */
class MessageListAdapter (val context: Context,val messages:List<EMMessage>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        val ITEM_TYPE_SEND_MESSAGE = 0
        val ITEM_TYPE_RECEIVE_MESSAGE=1
    }

    override fun getItemViewType(position: Int): Int {
        if (messages[position].direct() == EMMessage.Direct.SEND) {
            return ITEM_TYPE_SEND_MESSAGE
        } else {
            return ITEM_TYPE_RECEIVE_MESSAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ITEM_TYPE_SEND_MESSAGE) {
            return SendMessageViewHolder(SendMessageItemView(context))
        } else {
            return ReceiveMessageViewHolder(ReceiveMessageItemView(context))
        }
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val showTimeStamp = isShowTimestamp(position)
        if (getItemViewType(position) == ITEM_TYPE_SEND_MESSAGE) {
            val sendMessageItemView = holder.itemView as SendMessageItemView
            sendMessageItemView.bindView(messages[position],showTimeStamp)
        } else {
            val receiveMessageItemView = holder.itemView as ReceiveMessageItemView
            receiveMessageItemView.bindView(messages[position],showTimeStamp)
        }
    }

    private fun isShowTimestamp(position: Int): Boolean {
        var showTimestamp = true
        if (position > 0) {
            showTimestamp = !DateUtils.isCloseEnough(messages[position].msgTime,
                    messages[position-1].msgTime)
        }
        return showTimestamp
    }

    class SendMessageViewHolder (itemView:View): RecyclerView.ViewHolder(itemView) {}

    class ReceiveMessageViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
}