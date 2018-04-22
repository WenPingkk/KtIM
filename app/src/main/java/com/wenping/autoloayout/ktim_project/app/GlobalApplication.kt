package com.wenping.autoloayout.ktim_project.app

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioManager
import android.media.SoundPool
import cn.bmob.v3.Bmob
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMOptions
import com.hyphenate.chat.EMTextMessageBody
import com.wenping.autoloayout.ktim_project.BuildConfig
import com.wenping.autoloayout.ktim_project.R
import com.wenping.autoloayout.ktim_project.adapter.EMMessageListenerAdapter
import com.wenping.autoloayout.ktim_project.ui.activity.ChatActivity

/**
 * Author WenPing
 * CreateTime 2018/4/9.
 * Description:
 */
class GlobalApplication : Application() {

    companion object {
        lateinit var instance:GlobalApplication
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        //初始化
        EMClient.getInstance().init(applicationContext, EMOptions())
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)

//        Bmob.initialize(applicationContext, "df5a02bd4c7875549784ceb033407cc4")
        Bmob.initialize(applicationContext, "ef808b3b36ee22248d19f04287ce5b37")

        EMClient.getInstance().chatManager().addMessageListener(messageListener)
    }

    val messageListener = object : EMMessageListenerAdapter(){
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            //如果是在前台，则播放短的声音
            if (isForeground()){
                soundPool.play(duan, 1f, 1f, 0, 0, 1f)
            } else {
                //如果在后台，则播放长的声音
                soundPool.play(yulu, 1f, 1f, 0, 0, 1f)
                showNotification(p0)
            }
        }
    }

    private fun showNotification(p0: MutableList<EMMessage>?) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        p0?.forEach {
            var contentText = getString(R.string.no_text_message)
            if (it.type == EMMessage.Type.TXT) {
                contentText = (it.body as EMTextMessageBody).message
            }
            val intent = Intent(this,ChatActivity::class.java)
            intent.putExtra("username",it.conversationId())

            val taskStackBuilder = TaskStackBuilder.create(this).addParentStack(ChatActivity::class.java).addNextIntent(intent)
            val pendingIntent = taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)

            val notification = Notification.Builder(this)
                    .setContentTitle(getString(R.string.receive_new_message))
                    .setContentText(contentText)
                    .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.avatar1))
                    .setSmallIcon(R.mipmap.ic_contact)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .notification
            notificationManager.notify(1,notification)
        }
    }


    /**
     * 判断应用是否在前台
     */
    private fun isForeground(): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (runningAppProgress in activityManager.runningAppProcesses) {
            if (runningAppProgress.processName == packageName) {
                return runningAppProgress.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            }
        }
        return false
    }

    val soundPool = SoundPool(2, AudioManager.STREAM_MUSIC, 0)
    val duan by lazy { soundPool.load(instance, R.raw.duan,0) }
    val yulu by lazy { soundPool.load(instance, R.raw.yulu, 0) }


}