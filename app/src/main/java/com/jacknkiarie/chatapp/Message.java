package com.jacknkiarie.chatapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "message_table")
public class Message {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String sender;
    public String content;
    public Long timeStamp;

    @TypeConverters(MessageTypeConverter.class)
    public Type type;

    public Message(String sender, String content, Long timeStamp) {
        this.sender = sender;
        this.content = content;
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public enum Type {
        SENT(0), RECEIVED(1);

        private int code;

        Type(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }
}
