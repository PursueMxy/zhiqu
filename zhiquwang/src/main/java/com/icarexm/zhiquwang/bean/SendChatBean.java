package com.icarexm.zhiquwang.bean;

public class SendChatBean {
    private String chat_id;
    private String user_id;
    private String admin_id;
    private String job_id;
    private String content;
    private String type;
    private String avatar;
    private String time;

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public SendChatBean(String chat_id, String user_id, String admin_id, String job_id, String content, String type, String avatar,String time) {
        this.chat_id = chat_id;
        this.user_id = user_id;
        this.admin_id = admin_id;
        this.job_id = job_id;
        this.content = content;
        this.type = type;
        this.avatar = avatar;
        this.time=time;
    }
}
