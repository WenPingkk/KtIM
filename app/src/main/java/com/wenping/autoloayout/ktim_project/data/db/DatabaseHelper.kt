package com.wenping.autoloayout.ktim_project.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.wenping.autoloayout.ktim_project.app.GlobalApplication
import org.jetbrains.anko.db.*

/**
 * @author WenPing
 * @date 2018/4/18
 * @decription:
 *<p>
 */
class DatabaseHelper(ctx: Context = GlobalApplication.instance)
    : ManagedSQLiteOpenHelper(ctx, NAME, null, VERSION) {

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(ContactTable.NAME,true)
        onCreate(db)
    }

    //创建数据库
    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(ContactTable.NAME, true,
                ContactTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                ContactTable.CONTACT to TEXT)
    }

    companion object {
        val NAME = "im.db"
        val VERSION = 1
    }
}