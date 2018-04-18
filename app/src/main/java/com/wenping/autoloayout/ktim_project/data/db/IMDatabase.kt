package com.wenping.autoloayout.ktim_project.data.db

import com.wenping.autoloayout.ktim_project.extentions.toVarargArray
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * @author WenPing
 * @date 2018/4/18
 * @decription:
 *<p>
 */
class IMDatabase {

    companion object {
        val databaseHelper = DatabaseHelper()
        val instance = IMDatabase()
    }

    fun saveContact(contact: Contact) {
        databaseHelper.use {

            //SQLiteDatabase的拓展方法;
            //*contact.map.toVarargArray() 把toVarargArray 展开成可变的参数
            insert(ContactTable.NAME, *contact.map.toVarargArray())
        }
    }

    fun getAllContacts(): List<Contact> = databaseHelper.use {
        select(ContactTable.NAME)
                .parseList(object : MapRowParser<Contact> {
                    override fun parseRow(columns: Map<String, Any?>): Contact = Contact(columns.toMutableMap())
                })
    }

    fun deleteAllContacts() {
        databaseHelper.use {
            delete(ContactTable.NAME,null,null)
        }
    }

}
