package com.wenping.autoloayout.ktim_project.ui.fragment

import android.view.View
import com.wenping.autoloayout.ktim_project.R
import com.wenping.autoloayout.ktim_project.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*

/**
 * Author WenPing
 * CreateTime 2018/4/13.
 * Description:
 */
class ContactFragment : BaseFragment() {
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

    }
}