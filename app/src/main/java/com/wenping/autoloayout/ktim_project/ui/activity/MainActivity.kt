package com.wenping.autoloayout.ktim_project.ui.activity

import com.wenping.autoloayout.ktim_project.R
import com.wenping.autoloayout.ktim_project.factory.FragmentFactory
import com.wenping.autoloayout.ktim_project.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int  = R.layout.activity_main

    override fun init() {
        super.init()
        bottomBar.setOnTabSelectListener {
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.replace(R.id.fragment_frame,FragmentFactory.instance.getFragment(it))
            beginTransaction.commit()
        }
    }
}
