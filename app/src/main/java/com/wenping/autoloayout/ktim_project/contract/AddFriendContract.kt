package com.wenping.autoloayout.ktim_project.contract

/**
 * @author WenPing
 * @date 2018/4/18
 * @decription:
 *<p>
 */
interface AddFriendContract {

    interface Presenter:BasePresenter{
        fun search(key:String)
    }

    interface View{

        fun onSeachSuccess()

        fun onSeachFailed()

    }

}