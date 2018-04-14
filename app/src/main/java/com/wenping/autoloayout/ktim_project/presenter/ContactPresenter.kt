package com.wenping.autoloayout.ktim_project.presenter

import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.wenping.autoloayout.ktim_project.contract.ContactContract
import com.wenping.autoloayout.ktim_project.data.ContactListItem
import org.jetbrains.anko.doAsync

/**
 * Author WenPing
 * CreateTime 2018/4/14.
 * Description:Presenter层
 */
class ContactPresenter(val view:ContactContract.View) : ContactContract.ContactPresenter {

    //mutableListOf,可修改，可变的list集合
    val contactListItems = mutableListOf<ContactListItem>()

    override fun loadContacts() {
        doAsync {
            try {
                val userName = EMClient.getInstance().contactManager().allContactsFromServer
                //根据首字符排序
                userName.sortBy { it[0] }
                userName.forEach {
                    val contactListItem = ContactListItem(it,it[0].toUpperCase())
                    contactListItems.add(contactListItem)
                }

                uiThread {
                    view.onLoadContactsSuccess()
                }
            } catch (e: HyphenateException) {
                view.onLoadContactFailed()
            }
        }

    }
}