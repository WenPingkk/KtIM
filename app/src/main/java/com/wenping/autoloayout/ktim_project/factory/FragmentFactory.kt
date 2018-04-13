package com.wenping.autoloayout.ktim_project.factory

import android.support.v4.app.Fragment
import com.wenping.autoloayout.ktim_project.R
import com.wenping.autoloayout.ktim_project.ui.fragment.ContactFragment
import com.wenping.autoloayout.ktim_project.ui.fragment.ConversationFragment
import com.wenping.autoloayout.ktim_project.ui.fragment.DynamicFragment

/**
 * Author WenPing
 * CreateTime 2018/4/13.
 * Description:FragmentFactory
 */
class FragmentFactory private constructor(){

    companion object {
        val instance = FragmentFactory()
    }

    val conversation by lazy { ConversationFragment() }

    val contact by lazy { ContactFragment() }

    val dynamic by lazy { DynamicFragment() }

    fun getFragment(tabId:Int): Fragment? {
        when (tabId) {
            R.id.tab_conversation ->return conversation
            R.id.tab_contacts->return contact
            R.id.tab_dynamic->return dynamic
        }
        return null
    }

}