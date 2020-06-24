package com.diabin.festec.uimake.sample;

import android.graphics.drawable.Drawable;

public class OtherNewsItem {
    private String id;
    private String time;
    private Drawable user_img;
//    private String text_name;
//    private String text_honor;
    private String text_content;
    private Drawable attached_img;
    private int distributionNum;
    private int commentNum;
    private int likeNum;

    public OtherNewsItem(String id,
                         String time,
                         Drawable user_img,
                         String text_content,
                         Drawable attached_img,
                         int distributionNum,
                         int commentNum,
                         int likeNum) {
        this.id = id;
        this.time = time;
        this.user_img = user_img;
        this.text_content = text_content;
        this.attached_img = attached_img;
        this.distributionNum = distributionNum;
        this.commentNum = commentNum;
        this.likeNum = likeNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Drawable getUser_img() {
        return user_img;
    }

    public void setUser_img(Drawable user_img) {
        this.user_img = user_img;
    }

    public String getText_content() {
        return text_content;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }

    public Drawable getAttached_img() {
        return attached_img;
    }

    public void setAttached_img(Drawable attached_img) {
        this.attached_img = attached_img;
    }

    public int getDistributionNum() {
        return distributionNum;
    }

    public void setDistributionNum(int distributionNum) {
        this.distributionNum = distributionNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }
}
