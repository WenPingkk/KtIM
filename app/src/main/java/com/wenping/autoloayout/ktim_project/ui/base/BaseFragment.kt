package com.wenping.autoloayout.ktim_project.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Author WenPing
 * CreateTime 2018/4/7.
 * Description:
 */
abstract class BaseFragment : Fragment() {

    /**
     * kotlin 语法
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(getLayoutId(), null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) =
        init()

    //初始化公共的功能，子类可以完成自己的初始化
    open fun init() {

    }

    //布局id
    abstract fun getLayoutId(): Int


}