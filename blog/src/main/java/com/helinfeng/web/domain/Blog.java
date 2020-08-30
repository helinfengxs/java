package com.helinfeng.web.domain;

import lombok.Data;

@Data
public class Blog {
    private String id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private long views;
    private String isAppreciation;
    private String isSharStatement;
    private String isCommentAbled;
    private String isPublished;
    private String recommend;
    private String create_time;
    private String update_time;
    private String type_id;
    private String user_id;
    private String comment_id;
    private String description;

    /**
     *
     * @param id 博客id
     * @param title 博客标题
     * @param content 博客内容
     * @param firstPicture 博客首图
     * @param flag 博客标记
     * @param views 博客浏览次数
     * @param isAppreciation 博客是否开启赞赏
     * @param isSharStatement 博客是否开启版权
     * @param isCommentAbled 博客是否开启评论
     * @param isPublished 博客是否发布
     * @param recommend 博客是否为推荐
     * @param create_time 创建时间
     * @param update_time 更新时间
     * @param type_id 分类id
     * @param user_id 用户id
     * @param comment_id 评论id
     */
    public Blog(String id, String title, String content, String firstPicture, String flag, long views, String isAppreciation, String isSharStatement,
                String isCommentAbled, String isPublished, String recommend, String create_time, String update_time, String type_id, String user_id, String comment_id,String description) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.firstPicture = firstPicture;
        this.flag = flag;
        this.views = views;
        this.isAppreciation = isAppreciation;
        this.isSharStatement = isSharStatement;
        this.isCommentAbled = isCommentAbled;
        this.isPublished = isPublished;
        this.recommend = recommend;
        this.create_time = create_time;
        this.update_time = update_time;
        this.type_id = type_id;
        this.user_id = user_id;
        this.comment_id = comment_id;
        this.description = description;
    }

    public Blog() {
    }
}
