package com.wenping.autoloayout.ktim_project.ui.activity

import com.wenping.autoloayout.ktim_project.R
import com.wenping.autoloayout.ktim_project.ui.base.BaseActivity
import kotlinx.android.synthetic.main.header.*

/**
 * @author WenPing
 * @date 2018/4/18
 * @decription:
 *<p>
 */
class AddFriendsActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_add_friend

    override fun init() {

        headerTitle.text = getString(R.string.add_friend)

    }


}