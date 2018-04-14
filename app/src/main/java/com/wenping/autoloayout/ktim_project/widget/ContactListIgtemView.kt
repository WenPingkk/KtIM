package com.wenping.autoloayout.ktim_project.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.wenping.autoloayout.ktim_project.R

/**
 * Author WenPing
 * CreateTime 2018/4/14.
 * Description:
 */
class ContactListIgtemView(context: Context?, attrs: AttributeSet?=null) : RelativeLayout(context, attrs) {

    init {
        //布局创建把这个布局添加到组合式自定义布局
        View.inflate(context, R.layout.view_contact_item,this)



    }

}