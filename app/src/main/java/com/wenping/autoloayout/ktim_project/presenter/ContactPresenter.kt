package com.wenping.autoloayout.ktim_project.presenter

import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.wenping.autoloayout.ktim_project.contract.ContactContract
import org.jetbrains.anko.doAsync

/**
 * Author WenPing
 * CreateTime 2018/4/14.
 * Description:Presenter层
 */
class ContactPresenter(val view:ContactContract.View) : ContactContract.ContactPresenter {

    override fun loadContacts() {

        doAsync {
            try {
                val userName = EMClient.getInstance().contactManager().allContactsFromServer
                uiThread {
                    view.onLoadContactsSuccess()
                }
            } catch (e: HyphenateException) {
                view.onLoadContactFailed()
            }
        }

    }
}