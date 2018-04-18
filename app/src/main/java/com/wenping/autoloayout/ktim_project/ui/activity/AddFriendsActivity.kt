package com.wenping.autoloayout.ktim_project.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.wenping.autoloayout.ktim_project.R
import com.wenping.autoloayout.ktim_project.adapter.AddFriendListFriendAdapter
import com.wenping.autoloayout.ktim_project.contract.AddFriendContract
import com.wenping.autoloayout.ktim_project.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 * @author WenPing
 * @date 2018/4/18
 * @decription:
 *<p>
 */
class AddFriendsActivity : BaseActivity() ,AddFriendContract.View{

    override fun getLayoutId(): Int = R.layout.activity_add_friend

    override fun init() {

        //title
        headerTitle.text = getString(R.string.add_friend)
        recyclerView.apply {

            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListFriendAdapter(context)

        }
    }

    override fun onSeachSuccess() {
        dissProgressDialog()
        toast(R.string.search_success)
        //通知recyclerview刷新界面
        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun onSeachFailed() {
        dissProgressDialog()
        toast(R.string.search_failed)
    }

}