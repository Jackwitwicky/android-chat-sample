package com.jacknkiarie.chatapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jacknkiarie.chatui.ChatView
import com.jacknkiarie.chatui.models.ChatMessage
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {


    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chatView = findViewById<View>(R.id.chat_view) as ChatView
//        chatView.addMessage(ChatMessage(UUID.randomUUID().toString(),"Message received", System.currentTimeMillis(), ChatMessage.Type.RECEIVED))
//        chatView.addMessage(ChatMessage(
//            UUID.randomUUID().toString(),"A message with a sender name",
//                System.currentTimeMillis(), ChatMessage.Type.RECEIVED, "Ryan Java"))
        chatView.setOnSentMessageListener {
            it.chatId = "chat1"
            mainViewModel.saveMessage(it)
            true
        }

        chatView.setTypingListener(object : ChatView.TypingListener {
            override fun userStartedTyping() {}
            override fun userStoppedTyping() {}
        })

        val factory = MainViewModelFactory(this.getApplication())
         mainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        mainViewModel.getChatMessages().observe(this, Observer { messages ->
            Log.d("TAG", "I AM CALLED WITH DATA: ${messages.snapshot()}")
            if (messages != null) chatView.chatViewListAdapter.submitList(messages)
            chatView.chatViewListAdapter.notifyDataSetChanged()
        })

        generateAndSaveMessages()
    }

    private fun generateAndSaveMessages() {
        val message1 = ChatMessage(UUID.randomUUID().toString(),"Hi", System.currentTimeMillis(), ChatMessage.Type.SENT, "John", 1, 2, "chat1", "0", true)

        val message2 = ChatMessage(UUID.randomUUID().toString(),"Jambo", System.currentTimeMillis(), ChatMessage.Type.RECEIVED, "Mercy",1, 2, "chat1", "0", true)

        val message3 = ChatMessage(UUID.randomUUID().toString(),"Guten", System.currentTimeMillis(), ChatMessage.Type.SENT, "John", 1, 2, "chat1", "0", true)

        val message4 = ChatMessage(UUID.randomUUID().toString(),"Morgen", System.currentTimeMillis(), ChatMessage.Type.RECEIVED, "Mercy", 1, 2, "chat1", "0", true)

        val message5 = ChatMessage(UUID.randomUUID().toString(),"How are you doing?", System.currentTimeMillis(), ChatMessage.Type.SENT, "John", 1, 2, "chat1", "0", true)

        val message6 = ChatMessage(UUID.randomUUID().toString(),"I am well", System.currentTimeMillis(), ChatMessage.Type.RECEIVED, "Mercy", 1, 2, "chat1", "0", true)

        val messages = listOf(message1, message2, message3, message4, message5, message6)
        mainViewModel.saveMessages(messages)
    }
}
