package com.wenping.autoloayout.ktim_project.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.hyphenate.chat.EMClient
import com.wenping.autoloayout.ktim_project.R
import com.wenping.autoloayout.ktim_project.adapter.ContactListAdapter
import com.wenping.autoloayout.ktim_project.adapter.EMContactListenerAdapter
import com.wenping.autoloayout.ktim_project.contract.ContactContract
import com.wenping.autoloayout.ktim_project.presenter.ContactPresenter
import com.wenping.autoloayout.ktim_project.ui.activity.AddFriendsActivity
import com.wenping.autoloayout.ktim_project.ui.base.BaseFragment
import com.wenping.autoloayout.ktim_project.widget.Slidebar
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Author WenPing
 * CreateTime 2018/4/13.
 * Description:
 */
class ContactFragment : BaseFragment(), ContactContract.View {
    override fun getLayoutId(): Int = R.layout.fragment_contacts

    val presenter = ContactPresenter(this)

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE
        add.setOnClickListener{
            context?.startActivity<AddFriendsActivity>()
        }


        /**
         * apply的方法
         */
        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            isRefreshing = true
            //监听
            setOnRefreshListener {
                presenter.loadContacts()
            }
        }

        recyclerView.apply {
            //设置为true，recycerview内部会做一些优化
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)

            adapter = ContactListAdapter(context,presenter.contactListItems)

        }

        EMClient.getInstance().contactManager().setContactListener(object :EMContactListenerAdapter(){
            override fun onContactDeleted(p0: String?) {
                super.onContactDeleted(p0)
                //重新获取联系人的数据

                presenter.loadContacts()
            }
        })

        slideBar.onSectionChangeListener = object :Slidebar.OnSectionChangeListener{

            override fun onSectionChange(firstLetter: String) {
                //显示绿色的背景
                section.visibility = View.VISIBLE
                section.text = firstLetter

                recyclerView.smoothScrollToPosition(getPosition(firstLetter))
            }

            override fun onSlideFinish() {
                //隐藏绿色背景的textView
                section.visibility = View.GONE
            }
        }

        presenter.loadContacts()
    }

    private fun getPosition(firstLetter: String): Int =
        //二分查找
        presenter.contactListItems.binarySearch {
            contactListItem -> contactListItem.firstLetter.minus(firstLetter[0])
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