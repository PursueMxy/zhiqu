package com.icarexm.zhiquwang.socket;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.ChatMessageBean;
import com.icarexm.zhiquwang.utils.IConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.IllegalFormatCodePointException;

import io.socket.client.Ack;
import io.socket.emitter.Emitter;

/**
 * @author silencezwm on 2017/8/25 上午11:12
 * @email silencezwm@gmail.com
 * @description AppSocket
 */
public class AppSocket extends BaseSocket {

    private static volatile AppSocket INSTANCE = null;

    public static AppSocket getInstance() {
        if (INSTANCE == null) {
            throw new NullPointerException("must first call the build() method");
        }
        return INSTANCE;
    }

    public static AppSocket init(Builder builder) {
        return new AppSocket(builder);
    }

    private AppSocket(Builder builder) {
        super(builder);
        INSTANCE = this;
    }

    /**
     * 增加用户
     *
     * @param username
     */
    public void addUser(String username) {
        mSocket.emit(IConstants.LOGIN, username, new Ack() {
            @Override
            public void call(Object... args) {
            }
        });
    }



    /**
     * 发送消息
     *
     * @param content
     */
    public void sendMessage(String content) {
        mSocket.emit(IConstants.NEW_MESSAGE, content, new Ack() {
            @Override
            public void call(Object... args) {

            }
        });
    }

    /**
     * 返回监听
     */
    public void ChatBackcall(){
        mSocket.on(IConstants.USER_JOINED, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject newMessage = (JSONObject) args[0];
                String s = new GsonBuilder().create().toJson(newMessage);
                ChatMessageBean.NameValuePairsBean nameValuePairsBean = new GsonBuilder().create().fromJson(s, ChatMessageBean.NameValuePairsBean.class);
                if (mSendOnClick!=null){
                    mSendOnClick.updateMessage(nameValuePairsBean);
                }
            }
        });
    }

    /**
     * 输入中
     */
    public void typing() {
        mSocket.emit(IConstants.TYPING);
    }

    /**
     * 停止输入
     */
    public void stopTyping() {
        mSocket.emit(IConstants.STOP_TYPING);
    }

    /**
     * 更新消息
     */


    public SendOnItemClick mSendOnClick;

    public SendOnItemClick getSendOnClickListtener() {
        return mSendOnClick;
    }

    public void setOnItemClickListener(SendOnItemClick sendOnClick) {
        this.mSendOnClick = sendOnClick;
    }

    public  interface  SendOnItemClick{
       void updateMessage(ChatMessageBean.NameValuePairsBean nameValuePairsBean);
    }

}
