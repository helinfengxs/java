package com.helinfeng.web.domain;

import lombok.Data;

@Data
public class Blog_Tag {
    private String id;
    private String blog_id;
    private String tag_id;

    public Blog_Tag(String id, String blog_id, String tag_id) {
        this.id = id;
        this.blog_id = blog_id;
        this.tag_id = tag_id;
    }

    public Blog_Tag() {
    }
}
