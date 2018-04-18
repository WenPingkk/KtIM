package com.wenping.autoloayout.ktim_project.presenter

import cn.bmob.v3.Bmob
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.hyphenate.chat.EMClient
import com.wenping.autoloayout.ktim_project.contract.AddFriendContract

/**
 * @author WenPing
 * @date 2018/4/18
 * @decription:
 *<p>
 */
class AddFriendPresenter(var view: AddFriendContract.View) : AddFriendContract.Presenter {


    override fun search(key: String) {

        val query = BmobQuery<BmobUser>()
        query.addWhereContains("username", key)
                .addWhereNotEqualTo("username", EMClient.getInstance().currentUser)
        query.findObjects(object : FindListener<BmobUser>() {
            override fun done(p0: MutableList<BmobUser>?, p1: BmobException?) {
                if (p1 == null) view.onSeachSuccess()
                else view.onSeachFailed()
            }
        })


    }
}