package com.jacknkiarie.chatapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.jacknkiarie.chatui.models.ChatMessage
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {

    val messageDao = AppDatabase.getDatabase(application).messageDao()
    val messagesList: LiveData<PagedList<ChatMessage>>

    init {
        val factory: DataSource.Factory<Int, ChatMessage> =
            AppDatabase.getDatabase(application).messageDao().getMessages()

        val pagedListBuilder: LivePagedListBuilder<Int, ChatMessage>  = LivePagedListBuilder<Int, ChatMessage>(factory,
            10)
        messagesList = pagedListBuilder.build()
    }

    fun getChatMessages() = messagesList

    fun saveMessage(chatMessage: ChatMessage) {
        viewModelScope.launch {
            messageDao.save(chatMessage)
        }
    }

    fun saveMessages(chatMessages: List<ChatMessage>) {
        viewModelScope.launch {
            messageDao.saveMessages(chatMessages)
        }
    }
}