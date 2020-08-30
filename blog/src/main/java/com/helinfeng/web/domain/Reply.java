package com.helinfeng.web.domain;

import lombok.Data;

@Data
public class Reply {
    private String id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private String create_time;
    private String comment_id;
    private String blog_id;
    /**
     *
     * @param id 回复id
     * @param nickname 昵称
     * @param email 游戏
     * @param content 回复内容
     * @param avatar 头像
     * @param create_time 创建时间
     * @param comment_id 评论id
     */
    public Reply(String id, String nickname, String email, String content, String avatar, String create_time, String comment_id,String blog_id) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.content = content;
        this.avatar = avatar;
        this.create_time = create_time;
        this.comment_id = comment_id;
        this.blog_id = blog_id;
    }
}
