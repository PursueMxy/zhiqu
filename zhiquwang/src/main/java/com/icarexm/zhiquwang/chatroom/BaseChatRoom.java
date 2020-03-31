package com.icarexm.zhiquwang.chatroom;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.listener.IChatRoom;
import com.icarexm.zhiquwang.socket.AppSocket;
import com.icarexm.zhiquwang.utils.IConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Observable;

import io.socket.client.Manager;
import io.socket.client.Socket;

/**
 * @author silencezwm on 2017/8/25 上午11:18
 * @email silencezwm@gmail.com
 * @description 基类聊天室
 */
public class BaseChatRoom extends Observable implements IChatRoom {

    private String TAG = BaseChatRoom.class.getSimpleName();

    BaseChatRoom() {

    }

    private void login(int numUsers) {
        setChanged();
    }

    private void newMessage(String username, String messsage) {
        setChanged();
    }

    private void userJoined(String username, int numUsers) {
        setChanged();
    }

    private void userLeft(String username, int numUsers) {
    }

    private void typing(String username) {
        setChanged();
    }

    private void stopTyping(String username) {
        setChanged();
    }

    @Override
    public void emitterListenerResut(String key, Object... args) {
        Log.e("key",key);
        switch (key) {
            case Manager.EVENT_TRANSPORT:

                break;
            case Socket.EVENT_CONNECT_ERROR:
                Log.e(TAG, "EVENT_CONNECT_ERROR");
                break;

            case Socket.EVENT_CONNECT_TIMEOUT:
                Log.e(TAG, "EVENT_CONNECT_TIMEOUT");
                break;

            // Socket连接成功
            case Socket.EVENT_CONNECT:

                break;

            // Socket断开连接
            case Socket.EVENT_DISCONNECT:
                break;

            // Socket连接错误
            case Socket.EVENT_ERROR:
                Log.e(TAG, "EVENT_ERROR");
                break;

            // Socket重新连接
            case Socket.EVENT_RECONNECT:
                Log.d(TAG, "EVENT_RECONNECT");
                break;

            case Socket.EVENT_RECONNECT_ATTEMPT:
                Log.e(TAG, "EVENT_RECONNECT_ATTEMPT");
                break;

            case Socket.EVENT_RECONNECT_ERROR:
                Log.e(TAG, "EVENT_RECONNECT_ERROR");
                break;

            case Socket.EVENT_RECONNECT_FAILED:
                Log.e(TAG, "EVENT_RECONNECT_FAILED");
                break;

            case Socket.EVENT_RECONNECTING:
                Log.e(TAG, "EVENT_RECONNECTING");
                break;

            case IConstants.LOGIN:
                Log.e(TAG, "LOGIN");
                break;

            case IConstants.USER_LEFT:
                JSONObject userLeft = (JSONObject) args[0];
                String userLeftUsername;
                int userLeftNumUsers;
                try {
                    userLeftUsername = userLeft.getString("username");
                    userLeftNumUsers = userLeft.getInt("numUsers");
                } catch (JSONException e) {
                    Log.e(TAG, e.getMessage());
                    return;
                }

                userLeft(userLeftUsername, userLeftNumUsers);
                break;

            case IConstants.TYPING:
                JSONObject typing = (JSONObject) args[0];
                String typingUsername;
                try {
                    typingUsername = typing.getString("username");
                } catch (JSONException e) {
                    Log.e(TAG, e.getMessage());
                    return;
                }

                typing(typingUsername);
                break;

            case IConstants.STOP_TYPING:
                JSONObject stopTyping = (JSONObject) args[0];
                String stopTypingUsername;
                try {
                    stopTypingUsername = stopTyping.getString("username");
                } catch (JSONException e) {
                    Log.e(TAG, e.getMessage());
                    return;
                }

                stopTyping(stopTypingUsername);
                break;
            case IConstants.USER_JOINED:
                AppSocket.getInstance();
                break;

        }
    }

    @Override
    public void requestSocketResult(String key, Object... args) {
        switch (key) {

        }
    }


}
