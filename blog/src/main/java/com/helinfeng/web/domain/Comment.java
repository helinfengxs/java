package com.helinfeng.web.domain;

import lombok.Data;

@Data
public class Comment {
    private String id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private String create_time;
    private String blog_id;
    private String parent_id;
    public Comment(String id, String nickname, String email, String content, String avatar, String create_time,String blog_id,String parent_id) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.content = content;
        this.avatar = avatar;
        this.create_time = create_time;
        this.blog_id = blog_id;
        this.parent_id = parent_id;
    }

    public Comment() {

    }
}
