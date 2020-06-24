package com.diabin.festec.uimake.sample;

import android.graphics.drawable.Drawable;

public class CommentItem {
    private Drawable user_img;
    private String name;
    private String content;
    private String time;
//    private int distributionNum;
//    private int commentNum;
//    private int likeNum;

    public CommentItem(Drawable user_img, String name, String content, String time) {
        this.user_img = user_img;
        this.name = name;
        this.content = content;
        this.time = time;
    }

    /**
     * 后台自动生成时间
     * @param user_img
     * @param name
     * @param content
    public CommentItem(Drawable user_img, String name, String content) {
        this.user_img = user_img;
        this.name = name;
        this.content = content;
    }*/

    public Drawable getUser_img() {
        return user_img;
    }

    public void setUser_img(Drawable user_img) {
        this.user_img = user_img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
