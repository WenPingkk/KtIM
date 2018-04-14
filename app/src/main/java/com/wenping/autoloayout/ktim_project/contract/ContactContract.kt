package com.wenping.autoloayout.ktim_project.contract

/**
 * Author WenPing
 * CreateTime 2018/4/14.
 * Description:
 */
interface ContactContract{

    interface ContactPresenter:BasePresenter{
        fun loadContacts()
    }

    interface View{

        fun onLoadContactsSuccess(){

        }

        fun onLoadContactFailed(){

        }
    }
}