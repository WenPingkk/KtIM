package com.wenping.autoloayout.ktim_project.presenter

import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.wenping.autoloayout.ktim_project.contract.ContactContract
import com.wenping.autoloayout.ktim_project.data.ContactListItem
import com.wenping.autoloayout.ktim_project.data.db.Contact
import com.wenping.autoloayout.ktim_project.data.db.IMDatabase
import org.jetbrains.anko.doAsync

/**
 * Author WenPing
 * CreateTime 2018/4/14.
 * Description:Presenter层
 */
class ContactPresenter(val view: ContactContract.View) : ContactContract.ContactPresenter {

    //mutableListOf,可修改，可变的list集合
    val contactListItems = mutableListOf<ContactListItem>()

    override fun loadContacts() {
        doAsync {
            contactListItems.clear()
            //本地先删除数据中的所有数据
            IMDatabase.instance.deleteAllContacts()
            try {
                //先清空数据，再去获取，防止数据冗余
                val userName = EMClient.getInstance().contactManager().allContactsFromServer
                //根据首字符排序
                userName.sortBy { it[0] }
                userName.forEachIndexed { index, s ->
                    //这是一个boolean判断;1判断index是低0个的时候,这个item的首字母一定要显示;其二就是当前item的首字母和上一个item的首字母不一样则显示ta
                    val showFirstLetter :Boolean = index == 0 || s[0] != userName[index - 1][0]
                    val contactListItem = ContactListItem(s, s[0].toUpperCase(), showFirstLetter)
                    contactListItems.add(contactListItem)

                    val contact = Contact(mutableMapOf("name" to s))
                    IMDatabase.instance.saveContact(contact)
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