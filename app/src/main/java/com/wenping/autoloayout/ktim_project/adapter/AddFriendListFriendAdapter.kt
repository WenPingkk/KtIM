package com.wenping.autoloayout.ktim_project.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.wenping.autoloayout.ktim_project.widget.AddFriendListItemView

/**
 * @author WenPing
 * @date 2018/4/18
 * @decription:
 *<p>
 */
class AddFriendListFriendAdapter(var context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = AddFriendViewHolder(AddFriendListItemView(context))

    override fun getItemCount(): Int =30

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    class AddFriendViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

    }

}