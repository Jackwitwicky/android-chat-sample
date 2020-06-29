package com.jacknkiarie.chatapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jacknkiarie.chatui.models.ChatMessage
import com.jacknkiarie.chatui.models.MessageTypeConverter

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(ChatMessage::class), version = 1, exportSchema = false)
@TypeConverters(MessageTypeConverter::class)
public abstract class AppDatabase : RoomDatabase() {

    abstract fun messageDao(): MessageDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "chat_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}