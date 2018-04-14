package com.wenping.autoloayout.ktim_project.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.wenping.autoloayout.ktim_project.widget.ContactListIgtemView

/**
 * Author WenPing
 * CreateTime 2018/4/14.
 * Description:
 */
class ContactListAdapter(val context:Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**
     * 创建viewholder对应的itemview
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContactListItemViewHolder(ContactListIgtemView(context))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }
    override fun getItemCount(): Int {
        return 30
    }


    //创建viewholder
    class ContactListItemViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

    }
}