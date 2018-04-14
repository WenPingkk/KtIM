package com.wenping.autoloayout.ktim_project.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.wenping.autoloayout.ktim_project.R
import com.wenping.autoloayout.ktim_project.adapter.ContactListAdapter
import com.wenping.autoloayout.ktim_project.contract.ContactContract
import com.wenping.autoloayout.ktim_project.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 * Author WenPing
 * CreateTime 2018/4/13.
 * Description:
 */
class ContactFragment : BaseFragment(),ContactContract.View {
    override fun getLayoutId(): Int = R.layout.fragment_contacts

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE

        /**
         * apply的方法
         */
        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            isRefreshing = true
        }

        recyclerView.apply {
            //设置为true，recycerview内部会做一些优化
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context)
        }
    }

    override fun onLoadContactsSuccess() {
        swipeRefreshLayout.isRefreshing = false
        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun onLoadContactFailed() {
        super.onLoadContactFailed()
        swipeRefreshLayout.isRefreshing = false
        context?.toast(R.string.load_contacts_failed)
    }

}