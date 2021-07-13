package com.joelson.contactapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joelson.contactapp.ContactDAO
import com.joelson.contactapp.model.ContactModel

@Database(
    entities = arrayOf(ContactModel::class),
    version = 1
)
abstract class ContactDatabase: RoomDatabase() {
    abstract fun contactDao(): ContactDAO
}